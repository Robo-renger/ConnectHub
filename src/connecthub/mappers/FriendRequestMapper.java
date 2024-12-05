package connecthub.mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.DataBaseManager;
import connecthub.entities.FriendRequest;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class FriendRequestMapper {

    // Static database file path
    private static final String DATABASE_FILE = "pending.json";
    
    // Set the database file for DataBaseManager during class loading
    static {DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);}
    
    // Create a new friend request
    public static void create(FriendRequest friendRequest) {
        try {
            DataBaseManager.getDBM().createEntityWithID(friendRequest);
        } catch (IOException e) {
            System.out.println("Error adding friend request to the database: " + e.getMessage());
        }
    }
    
    // Retrieve all friend requests
    public static List<FriendRequest> getAll() {
        try {
            return DataBaseManager.getDBM().readEntities(new TypeReference<List<FriendRequest>>() {});
        } catch (IOException e) {
            System.out.println("Error retrieving all friend requests: " + e.getMessage());
            return List.of(); 
        }    
    }
    
    // Retrieve a specific friend request by ID
    public static Optional<FriendRequest> get(int id) {
        try {
            List<FriendRequest> friendRequests = getAll();
            return friendRequests.stream().filter(friendRequest -> friendRequest.getID() == id).findFirst();
        } catch (Exception e) {
            System.out.println("Error retrieving friend request: " + e.getMessage());
            return Optional.empty();
        }
    }
    
    // Update a specific friend request
    public static boolean update(int id, FriendRequest updatedFriendRequest) {
        try {
            updatedFriendRequest.setID(id);

            return DataBaseManager.getDBM().updateEntity(
                    new TypeReference<List<FriendRequest>>() {},
                    updatedFriendRequest,
                    friendRequest -> friendRequest.getID() == id
            );
            
        } catch (IOException e) {
            System.out.println("Error updating friend request: " + e.getMessage());
            return false;
        }
    }
    
    // Delete a specific friend request
    public static boolean delete(int id) {
        try {
            return DataBaseManager.getDBM().deleteEntity(
                    new TypeReference<List<FriendRequest>>() {},
                    friendRequest -> friendRequest.getID() == id
            );
        } catch (IOException e) {
            System.out.println("Error deleting friend request: " + e.getMessage());
            return false;
        }
    }
}
