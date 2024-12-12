package connecthub;

import connecthub.entities.Group;
import connecthub.entities.MembershipRequest;
import connecthub.entities.PostGroup;
import connecthub.entities.UserGroup;
import connecthub.exceptions.InvalidDataException;
import connecthub.exceptions.UnauthorizedActionException;
import connecthub.mappers.GroupMapper;
import connecthub.mappers.MembershipRequestMapper;
import connecthub.mappers.PostGroupMapper;
import connecthub.mappers.UserGroupMapper;
import java.util.List;
import java.util.Optional;

// Facade Structural Design Pattern
public class GroupAuthorityManager {

    // Promotes a member to an admin if not an admin
    public static void promoteToAdmin(int groupID, int userID, int callerID) {
        
        // Validate the caller's role
        String callerRole = validateRole(groupID, callerID);
        if(!callerRole.equalsIgnoreCase("Creator")) 
            throw new UnauthorizedActionException("Only the creator can promote members to admin");
        
        // Validate the user's role
        String userRole = validateRole(groupID, userID);
        if(userRole.equalsIgnoreCase("Creator"))
            throw new UnauthorizedActionException("Cannot promote a creator");
        else if(userRole.equalsIgnoreCase("Admin"))
            throw new UnauthorizedActionException("The user is already an admin");
        
        // Retrieve the user-group and update the role
        UserGroup userGroup = UserGroupMapper.get(groupID, userID).get();
        userGroup.setRole("ADMIN");
        UserGroupMapper.update(userGroup.getID(), userGroup);
        
        System.out.println("User " + userID + " has been promoted to Admin in group " + groupID);
    }
    
    // Demotes an admin to a member if not a creator or member
    public static void demoteFromAdmin(int groupID, int userID, int callerID) {
        
        // Validate the caller's role
        String callerRole = validateRole(groupID, callerID);
        if (!callerRole.equalsIgnoreCase("Creator"))
            throw new UnauthorizedActionException("Only the creator can demote an admin");

        // Validate the user's role
        String userRole = validateRole(groupID, userID);
        if (userRole.equalsIgnoreCase("Creator"))
            throw new UnauthorizedActionException("Cannot demote a creator.");
        else if (userRole.equalsIgnoreCase("Member"))
            throw new UnauthorizedActionException("The user is not an admin and cannot be demoted.");

        // Retrieve the user-group and update the role
        UserGroup userGroup = UserGroupMapper.get(groupID, userID).get();
        userGroup.setRole("MEMBER");
        UserGroupMapper.update(userGroup.getID(), userGroup);

        System.out.println("User " + userID + " has been demoted to Member in group " + groupID);
    }

    public static void sendMembershipRequest(int groupID, int userID)
    {
        String callerRole = validateRole(groupID, userID);
        if (callerRole.equalsIgnoreCase("Creator"))
            throw new UnauthorizedActionException("User is the creator of this group");
        
        Optional<UserGroup> alreadyJoined = UserGroupMapper.get(groupID, userID);
        if (alreadyJoined.isPresent())
            throw new InvalidDataException("User already a member of this group");
        
        Optional<MembershipRequest> alreadyRequested = MembershipRequestMapper.get(groupID, userID);
        if (alreadyRequested.isPresent())
            throw new InvalidDataException("User already requested to join this group");
        else
            MembershipRequestMapper.create(new MembershipRequest(groupID, userID));
    }
    
    public static void acceptMembershipRequest(MembershipRequest request, int callerID) {
        // Validate the caller's role
        String callerRole = validateRole(request.getGroupID(), callerID);
        if (!callerRole.equalsIgnoreCase("Creator") && !callerRole.equalsIgnoreCase("Admin"))
            throw new UnauthorizedActionException("Only creators or admins can accept membership requests");

        // Ensure the request is valid
        if (request.getUserID() <= 0 || request.getGroupID() <= 0)
            throw new InvalidDataException("Invalid membership request data");

        // Add the user to the group with the "Member" role
        UserGroup userGroup = new UserGroup(request.getGroupID(), request.getUserID());
        UserGroupMapper.create(userGroup);
        
        // Remove the request from the database
        MembershipRequestMapper.delete(request.getID());
        
        System.out.println("Membership request for user " + request.getUserID() + " to group " + request.getGroupID() + " has been accepted");

    }

    public static void declineMembershipRequest(MembershipRequest request, int callerID) {
        // Validate the caller's role
        String callerRole = validateRole(request.getGroupID(), callerID);
        if (!callerRole.equalsIgnoreCase("Creator") && !callerRole.equalsIgnoreCase("Admin"))
            throw new UnauthorizedActionException("Only creators or admins can decline membership requests");

        // Ensure the request is valid
        if (request.getUserID() <= 0 || request.getGroupID() <= 0)
            throw new InvalidDataException("Invalid membership request data");

        // Remove the request from the database
        boolean isDeleted = MembershipRequestMapper.delete(request.getID());
        if (isDeleted)
            System.out.println("Membership request for user " + request.getUserID() + " to group " + request.getGroupID() + " has been declined");
        else 
            System.out.println("Failed to decline membership request for user " + request.getUserID() + " to group " + request.getGroupID());
    }

    public static void removeMember(int groupID, int userID, int callerID) {
        // Validate the caller's role
        String callerRole = validateRole(groupID, callerID);
        if (!callerRole.equalsIgnoreCase("Creator") && !callerRole.equalsIgnoreCase("Admin"))
            throw new UnauthorizedActionException("Only creators or admins can remove members from the group");
        
        // No one can remove the Creator
        String userRole = validateRole(groupID, userID);
        if (userRole.equalsIgnoreCase("Creator"))
            throw new UnauthorizedActionException("Cannot remove the creator from the group");

        // If caller is a creator, they can remove anyone (admins or members)
        // If caller is an admin, they can only remove members, not admins
        if (callerRole.equalsIgnoreCase("Admin") && userRole.equalsIgnoreCase("Admin"))
            throw new UnauthorizedActionException("Admins cannot remove other admins");
        
        UserGroup userGroup = UserGroupMapper.get(groupID, userID).get();
        UserGroupMapper.delete(userGroup.getID());

        System.out.println("User " + userID + " has been removed from group " + groupID);
    }

    public static void deleteGroup(int groupID, int callerID) {
        // Validate the caller's role - Only the Creator can delete the group
        String callerRole = validateRole(groupID, callerID);
        if (!callerRole.equalsIgnoreCase("Creator")) {
            throw new UnauthorizedActionException("Only the creator can delete the group");
        }

        // Remove pending requests
        List<MembershipRequest> pendingRequests = MembershipRequestMapper.getAllForGroup(groupID);
        for (MembershipRequest request : pendingRequests) {
            MembershipRequestMapper.delete(request.getID());
        }

        // Remove users from the group
        List<UserGroup> userGroups = UserGroupMapper.getAllMembers(groupID);
        for (UserGroup userGroup : userGroups) {
            UserGroupMapper.delete(userGroup.getID());
        }

        // Delete the group 
        Group group = GroupMapper.get(groupID).orElseThrow(() -> new InvalidDataException("Group not found"));
        GroupMapper.delete(groupID);
        
        System.out.println("Group " + groupID + " has been successfully deleted by creator " + callerID);

    }
    
    public static void addPost(PostGroup postGroup, int callerID)
    {
        Optional<UserGroup> optionalUserGroup = UserGroupMapper.get(postGroup.getGroupID(), postGroup.getAuthorId());
        if(optionalUserGroup.isPresent() || callerID == postGroup.getAuthorId())
            PostGroupMapper.create(postGroup);
        else
            throw new InvalidDataException("The user and the group are not related");
    }
    
    public static void editPost(PostGroup updatedPostGroup, int callerID)
    {
        // Validate that its the user's post
        if(updatedPostGroup.getAuthorId() != callerID)
        {
            // Validate the caller's role
            String callerRole = validateRole(updatedPostGroup.getGroupID(), callerID);
            if (!callerRole.equalsIgnoreCase("Creator") && !callerRole.equalsIgnoreCase("Admin"))
                throw new UnauthorizedActionException("Only creators or admins or the author can edit the post");
        }
        
        Optional<UserGroup> optionalUserGroup = UserGroupMapper.get(updatedPostGroup.getGroupID(), updatedPostGroup.getAuthorId());
        if(optionalUserGroup.isPresent() || callerID == updatedPostGroup.getAuthorId())
            PostGroupMapper.update(updatedPostGroup.getID(), updatedPostGroup);
        else
            throw new InvalidDataException("The user and the group are not related");
    }
    
    public static void deletePost(PostGroup postGroup, int callerID)
    {
        // Validate that its the user's post
        if(postGroup.getAuthorId() != callerID)
        {
            // Validate the caller's role
            String callerRole = validateRole(postGroup.getGroupID(), callerID);
            if (!callerRole.equalsIgnoreCase("Creator") && !callerRole.equalsIgnoreCase("Admin"))
                throw new UnauthorizedActionException("Only creators or admins or the author can delete the post");
        }
        
        Optional<UserGroup> optionalUserGroup = UserGroupMapper.get(postGroup.getGroupID(), postGroup.getAuthorId());
        if(optionalUserGroup.isPresent() || callerID == postGroup.getAuthorId())
            PostGroupMapper.delete(postGroup.getID());
        else
            throw new InvalidDataException("The user and the group are not related");
    }

    public static String validateRole(int groupID, int id) {
        Optional<Group> optionalGroup = GroupMapper.get(groupID);
        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            if(group.getCreatorID() == id)
                return "Creator";
        } else
            throw new InvalidDataException("Group not found");
        
        Optional<UserGroup> optionalUserGroup = UserGroupMapper.get(groupID, id);
        if (optionalUserGroup.isPresent()) {
            UserGroup userGroup = optionalUserGroup.get();
            String role = userGroup.getRole();
            if (role.equalsIgnoreCase("Admin")) {
                return "Admin";
            } else if (role.equalsIgnoreCase("Member")) {
                return "Member";
            } else {
                throw new InvalidDataException("Invalid role");
            }
        } else
            throw new InvalidDataException("User not found");
    }

}
