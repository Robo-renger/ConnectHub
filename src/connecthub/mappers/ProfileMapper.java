package connecthub.mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.DataBaseManager;
import connecthub.entities.Profile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ProfileMapper {

//    Static database file path
    private static final String DATABASE_FILE = "profiles.json";
    
//    Set the database file for DataBaseManager during class loading
    static {DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);}
    
//    Create a new profile
    public static void create(Profile profile) {
        try {
            DataBaseManager.getDBM().createEntityWithID(profile);
        } catch (IOException e) {
            System.out.println("Error creating profile: " + e.getMessage());
        }
    }
    
//    Retrieve all Profiles
    public static List<Profile> getAll() {
        try {
            return DataBaseManager.getDBM().readEntities(new TypeReference<List<Profile>>() {});
        } catch (IOException e) {
            System.out.println("Error retrieving all profiles: " + e.getMessage());
            return List.of(); 
        }    
    }
    
//    Retrieve a specific user's profile
    public static Optional<Profile> get(int userId) {
        try {
            List<Profile> profiles = getAll();
            return profiles.stream().filter(profile -> profile.getUserID() == userId).findFirst();
        } catch (Exception e) {
            System.out.println("Error retrieving user's profile: " + e.getMessage());
            return Optional.empty();
        }
    }
    
//    Update a specific user's profile
    public static boolean update(int id, Profile updatedProfile) {
        try {
            updatedProfile.setID(id);

            return DataBaseManager.getDBM().updateEntity(
                    new TypeReference<List<Profile>>() {},
                    updatedProfile,
                    profile -> profile.getID() == id
            );
            
        } catch (IOException e) {
            System.out.println("Error updating user's profile: " + e.getMessage());
            return false;
        }
    }
    
//    Delete a specific user's profile
    public static boolean delete(int id) {
        try {
            return DataBaseManager.getDBM().deleteEntity(new TypeReference<List<Profile>>() {
            }, profile -> profile.getID() == id);
        } catch (IOException e) {
            System.out.println("Error deleting user's profile: " + e.getMessage());
            return false;
        }
    }
}