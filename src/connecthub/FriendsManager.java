package connecthub;

import connecthub.entities.Blocked;
import connecthub.entities.Friend;
import connecthub.entities.FriendRequest;
import connecthub.exceptions.InvalidDataException;
import connecthub.mappers.BlockedMapper;
import connecthub.mappers.FriendMapper;
import connecthub.mappers.FriendRequestMapper;
import java.util.List;
import java.util.Optional;

public class FriendsManager {

    public static void sendFriendRequest(int senderId, int receiverId) {
        Optional<Friend> alreadyExist = FriendMapper.get(senderId, receiverId);
        if (alreadyExist.isPresent())
            throw new InvalidDataException("Friendship already exists");
        else
            FriendRequestMapper.create(new FriendRequest(senderId, receiverId, "PENDING"));
    }

    public static void acceptFriendRequest(FriendRequest friendRequest) {
        friendRequest.setStatus("ACCEPTED");
        FriendRequestMapper.delete(friendRequest.getID());
        Friend friend = new Friend(friendRequest.getSenderId(), friendRequest.getReceiverId());
        FriendMapper.create(friend);
    }

    public static void rejectFriendRequest(FriendRequest friendRequest) {
        friendRequest.setStatus("REJECTED");
        FriendRequestMapper.delete(friendRequest.getID());
    }

    public static void blockUser(int userId, int blockedId) {
        // Remove friendship if it exists
        getFriends().stream()
                .filter(friend
                        -> (friend.getUserId() == userId && friend.getFriendId() == blockedId)
                || (friend.getUserId() == blockedId && friend.getFriendId() == userId)
                ).findFirst().ifPresent(friend -> {

                    // Delete the friend from the database
//            System.out.println("Deleting Friendship with ID: " + friend.getID());
                    FriendMapper.delete(friend.getID());

                });

        if (getBlocks().stream().noneMatch(block
                -> block.getUserId() == userId && block.getBlockedId() == blockedId)) {
            Blocked blocked = new Blocked(userId, blockedId);
            BlockedMapper.create(blocked);
        }
    }

    // Getters
    public static List<Friend> getFriends() {
        return FriendMapper.getAll();
    }

    public static List<FriendRequest> getFriendRequests() {
        return FriendRequestMapper.getAll();
    }

    public static List<Blocked> getBlocks() {
        return BlockedMapper.getAll();
    }
}
