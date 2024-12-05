package connecthub.mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.DataBaseManager;
import connecthub.entities.Friend;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class FriendMapper {

//    Static database file path
    private static final String DATABASE_FILE = "friends.json";
    
//    Set the database file for DataBaseManager during class loading
    static {DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);}
    
//    Create a new profile
    public static void create(Friend friend) {
        try {
            DataBaseManager.getDBM().createEntityWithID(friend);
        } catch (IOException e) {
            System.out.println("Error adding friend to the data base: " + e.getMessage());
        }
    }
    
//    Retrieve all Profiles
    public static List<Friend> getAll() {
        try {
            return DataBaseManager.getDBM().readEntities(new TypeReference<List<Friend>>() {});
        } catch (IOException e) {
            System.out.println("Error retrieving all friends: " + e.getMessage());
            return List.of(); 
        }    
    }
    
//    Retrieve a specific user's profile
    public static Optional<Friend> get(int id) {
        try {
            List<Friend> friends = getAll();
            return friends.stream().filter(friend -> friend.getID() == id).findFirst();
        } catch (Exception e) {
            System.out.println("Error retrieving user's friend: " + e.getMessage());
            return Optional.empty();
        }
    }
    
//    Update a specific user's profile
    public static boolean update(int id, Friend updatedFriend) {
        try {
            updatedFriend.setID(id);

            return DataBaseManager.getDBM().updateEntity(
                    new TypeReference<List<Friend>>() {},
                    updatedFriend,
                    profile -> profile.getID() == id
            );
            
        } catch (IOException e) {
            System.out.println("Error updating user's friend: " + e.getMessage());
            return false;
        }
    }
    
//    Delete a specific user's profile
    public static boolean delete(int id) {
        try {
            return DataBaseManager.getDBM().deleteEntity(new TypeReference<List<Friend>>() {
            }, friend -> friend.getID() == id);
        } catch (IOException e) {
            System.out.println("Error deleting friend relationship: " + e.getMessage());
            return false;
        }
    }
}