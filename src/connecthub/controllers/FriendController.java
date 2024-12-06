package connecthub.controllers;

import connecthub.entities.Friend;
import connecthub.mappers.FriendMapper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FriendController {
    
    /***
     * @param userId 
     * @return All friends for a specific user 
     */
    public static List<Friend> getAllFriends(int userId)
    {
        List<Friend> friends = FriendMapper.getAll();
        if (friends == null) 
            return List.of();
        
        return friends.stream()
               .filter(content -> content.getUserId()== userId || content.getFriendId() == userId).collect(Collectors.toList());
    }
    
    /***
     * @param userId
     * @return A list of the user's friends'IDs
     */
    public static List<Integer> suggestFriends(int userId)
    {
        List<Friend> friends = FriendMapper.getAll();
        if (friends == null) 
            return List.of();
        
        // Getting direct friends
        List<Integer> userFriends = getAllFriends(userId).stream()
                .map(friend -> friend.getUserId() == userId ? friend.getFriendId() : friend.getUserId())
                .distinct().collect(Collectors.toList());
        
        Set<Integer> suggestedFriends = new HashSet<>();
        for(int userFriendId: userFriends)
        {
            friends.stream()
                    .filter(friend -> friend.getUserId() == userFriendId || friend.getFriendId() == userFriendId)
                    .map(friend -> friend.getUserId() == userFriendId ? friend.getFriendId() : friend.getUserId())
                    .forEach(suggestedFriends::add);
        }
        
        suggestedFriends.remove(userId);
        suggestedFriends.removeAll(userFriends);
        
        return new ArrayList<>(suggestedFriends);
    }
}
