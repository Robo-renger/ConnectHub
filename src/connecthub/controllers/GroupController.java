package connecthub.controllers;

import static connecthub.controllers.FriendController.getAllFriends;
import connecthub.entities.Group;
import connecthub.entities.User;
import connecthub.entities.UserGroup;
import connecthub.mappers.GroupMapper;
import connecthub.mappers.UserGroupMapper;
import connecthub.mappers.UserMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupController {

    public static List<Group> search(String searchQuery) {
        try {
            List<Group> groups = GroupMapper.getAll();

            return groups.stream()
                    .filter(group -> group.getName() != null && group.getName().toLowerCase().contains(searchQuery.toLowerCase()))
                    .collect(Collectors.toList()); // Collect all matching groups into a list
        } catch (Exception e) {
            System.out.println("Error searching for groups: " + e.getMessage());
            return List.of(); // Return an empty list in case of an error
        }
    }

    public static void leave(int groupID, int userID) {
        Optional<UserGroup> userGroup = UserGroupMapper.get(groupID, userID);
        UserGroup joinedGroup;
        if (userGroup.isPresent()) {
            joinedGroup = userGroup.get();
            joinedGroup.setStatus("left");
            UserGroupMapper.update(joinedGroup.getID(), joinedGroup);
        }

    }

    public static List<User> getJoinedMembers(int groupID) {
        List<UserGroup> userGroupList = UserGroupMapper.getAllMembers(groupID);
        List<User> users = userGroupList.stream()
                .map(userGroup -> UserMapper.get(userGroup.getUserID()).orElse(null)) // Fetch user by userID
                .filter(user -> user != null) // Filter out null users in case of missing data
                .collect(Collectors.toList());

        return users;
    }

    public static List<Group> getLeftGroups(int userId) {
        // Get all UserGroup entities for the given userId and status "left"
        List<UserGroup> userGroups = UserGroupMapper.getAll(userId, "left");

        // Map each UserGroup's groupID to the corresponding Group object
        return userGroups.stream()
                .map(userGroup -> GroupMapper.get(userGroup.getGroupID()).orElse(null)) // Retrieve Group by groupID
                .filter(group -> group != null) // Filter out null Groups (if any)
                .collect(Collectors.toList()); // Collect all valid Groups into a list
    }

    public static List<Group> suggestGroups(int userId) {
        List<Group> groups = GroupMapper.getAll();
        if (groups == null) {
            return List.of();
        }

        // Fetch groups the user has left or joined
        List<UserGroup> leftGroups = UserGroupMapper.getAll(userId, "left");
        List<UserGroup> joinedGroups = UserGroupMapper.getAll(userId, "joined");

        // Extract group IDs into sets
        Set<Integer> leftGroupIDs = leftGroups.stream()
                .map(UserGroup::getGroupID)
                .collect(Collectors.toSet());

        Set<Integer> joinedGroupIDs = joinedGroups.stream()
                .map(UserGroup::getGroupID)
                .collect(Collectors.toSet());

        // Get direct friends
        List<User> userFriends = getAllFriends(userId);
        System.out.println("FRIENDS");
        System.out.println(userFriends);

        // Fetch groups for all friends
        Set<Integer> friendGroupIDs = userFriends.stream()
                .flatMap(friend -> UserGroupMapper.getAll(friend.getID(), "joined").stream()
                .map(UserGroup::getGroupID))
                .collect(Collectors.toSet());
        System.out.println("FRIENDS GROUPS");
        System.out.println(friendGroupIDs);
        List<Group> friendsGroups = new ArrayList<>();
        for (int friendGroupId : friendGroupIDs) {
            Optional<Group> optGroup = GroupMapper.get(friendGroupId);
            if(optGroup.isPresent())
                friendsGroups.add(optGroup.get());
        }
        // Remove groups the user has left, already joined, or belong to friends
        List<Group> suggestedGroups = friendsGroups.stream()
                .filter(group -> !leftGroupIDs.contains(group.getID())) // Exclude groups the user has left
                .filter(group -> !joinedGroupIDs.contains(group.getID())) // Exclude groups the user is already part of
                .collect(Collectors.toList());
        System.out.println(suggestedGroups);
        return suggestedGroups;
    }

    public static List<Group> joinedGroups(int userId) {
        List<Group> joinedGroups = new ArrayList<>();
        List<UserGroup> userGroups = UserGroupMapper.getAll(userId, "joined");
        for (UserGroup userGroup : userGroups) {
            Optional<Group> optJoinedGroup = GroupMapper.get(userGroup.getGroupID());
            if (optJoinedGroup.isPresent()) {
                joinedGroups.add(optJoinedGroup.get());
            }
        }
        return joinedGroups;
    }

}
