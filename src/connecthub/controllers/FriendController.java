package connecthub.controllers;

import connecthub.entities.Blocked;
import connecthub.entities.Friend;
import connecthub.entities.FriendRequest;
import connecthub.entities.User;
import connecthub.mappers.FriendMapper;
import connecthub.mappers.FriendRequestMapper;
import connecthub.mappers.UserMapper;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FriendController {

    /**
     * *
     * @param userId
     * @return A list of users who are friends with the given userId
     */
    public static List<User> getAllFriends(int userId) {
        List<Friend> friends = FriendMapper.getAll();
        if (friends == null) {
            return List.of();
        }

        // Collect user IDs based on the logic provided
        List<Integer> friendIds = friends.stream()
                .filter(friend -> friend.getUserId() == userId || friend.getFriendId() == userId)
                .map(friend -> friend.getUserId() == userId ? friend.getFriendId() : friend.getUserId())
                .filter(friendId -> friendId != userId) // Exclude the user with the given userId
                .collect(Collectors.toList());

        return friendIds.stream()
                .map(friendId -> UserMapper.get(friendId).orElse(null)) // Get user by ID, returning null if not found
                .filter(user -> user != null) // Remove any null users (if any)
                .collect(Collectors.toList());
    }

    /**
     * *
     * @param userId
     * @param friendId
     */
    public static void removeFriend(int userId, int friendId) {
        // Get all friends
        List<Friend> friends = FriendMapper.getAll();

        if (friends == null || friends.isEmpty()) {
            System.out.println("No friends data available.");
            return;
        }

        // Find the relationship where either (userId, friendId) or (friendId, userId) exists
        Friend friendRelationship = friends.stream()
                .filter(friend
                        -> (friend.getUserId() == userId && friend.getFriendId() == friendId)
                || (friend.getUserId() == friendId && friend.getFriendId() == userId)
                )
                .findFirst()
                .orElse(null); // Returns null if no such relationship is found

        // If the relationship exists, delete it
        if (friendRelationship != null) {
            boolean deleted = FriendMapper.delete(friendRelationship.getID()); // Deleting by ID
            if (deleted) {
                System.out.println("Friendship removed between user " + userId + " and user " + friendId);
            } else {
                System.out.println("Failed to remove friendship.");
            }
        } else {
            System.out.println("No friendship relationship found between user " + userId + " and user " + friendId);
        }
    }

    /**
     * *
     * @param userId
     * @return A list of the user's suggested friends
     */
    public static List<User> suggestFriends(int userId) {
        List<Friend> friends = FriendMapper.getAll();
        if (friends == null) {
            return List.of();
        }

        List<Blocked> blockedUsers = BlockedController.getAllBlocks(userId);

        Set<Integer> blockedUserIds = blockedUsers.stream()
                .map(blocked -> blocked.getUserId() == userId ? blocked.getBlockedId() : blocked.getUserId())
                .collect(Collectors.toSet());

        // Getting direct friends
        List<User> userFriends = getAllFriends(userId);

        Set<Integer> suggestedFriends = new HashSet<>();
        for (User userFriend : userFriends) {
            int userFriendId = userFriend.getID();

            friends.stream()
                    .filter(friend -> friend.getUserId() == userFriendId || friend.getFriendId() == userFriendId)
                    .map(friend -> friend.getUserId() == userFriendId ? friend.getFriendId() : friend.getUserId())
                    .forEach(suggestedFriends::add);
        }
        List<Integer> userFriendIds = userFriends.stream()
                .map(User::getID) // Map each User object to its ID
                .collect(Collectors.toList());
        suggestedFriends.remove(userId); // Don't suggest the user himself
        System.out.println(suggestedFriends);
        suggestedFriends.removeAll(blockedUserIds); // Don't suggest user's blockedships
        return suggestedFriends.stream()
                .map(friendId -> UserMapper.get(friendId).orElse(null)) // Get user by ID, return null if not found
                .filter(user -> user != null) // Filter out null users (in case of missing users)
                .collect(Collectors.toList());
    }

    /**
     * *
     * @param username
     * @return A list of users that match the input parameter
     */
    public static List<User> searchUsers(String username) {

        // Filter for matching usernames case-insensitively
        Predicate<User> usernameFilter = user -> user.getUsername().toLowerCase().contains(username.toLowerCase());

        //Filter list with the username filter
        List<Predicate<User>> filters = List.of(usernameFilter);

        return UserMapper.get(filters)
                .stream()
                .collect(Collectors.toList());
    }

    /**
     * *
     * @param userId
     * @return A list of the friend requests received by the user
     */
    public static List<FriendRequest> getFriendRequests(int userId) {
        List<FriendRequest> friendRequests = FriendRequestMapper.getAll();
        if (friendRequests == null) {
            return List.of();
        }

        return friendRequests.stream()
                .filter(request -> request.getReceiverId() == userId && "PENDING".equalsIgnoreCase(request.getStatus()))
                .collect(Collectors.toList());
    }
}
