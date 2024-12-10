package connecthub.mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.DataBaseManager;
import connecthub.entities.Group;
import connecthub.entities.Profile;
import connecthub.entities.UserGroup;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserGroupMapper {

//    Static database file path
    private static final String DATABASE_FILE = "usergroups.json";

//    Set the database file for DataBaseManager during class loading
    static {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
    }

//    Create a new profile
    public static void create(UserGroup userGroup) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            DataBaseManager.getDBM().createEntityWithID(userGroup);
        } catch (IOException e) {
            System.out.println("Error creating user group: " + e.getMessage());
        }
    }

//    Retrieve all Profiles
    public static List<UserGroup> getAll() {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().readEntities(new TypeReference<List<UserGroup>>() {
            });
        } catch (IOException e) {
            System.out.println("Error retrieving all user groups: " + e.getMessage());
            return List.of();
        }
    }
    // Retrieve all UserGroup entities by groupID

    public static List<UserGroup> getAllMembers(int UsergroupID) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<UserGroup> userGroups = getAll();

            // Filter the UserGroup entities by groupID
            return userGroups.stream()
                    .filter(userGroup -> userGroup.getGroupID() == UsergroupID)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error retrieving user groups by groupID: " + e.getMessage());
            return List.of(); // Return an empty list in case of an error
        }
    }
    public static List<UserGroup> getAll(int userId,String status) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<UserGroup> userGroups = getAll();

            // Filter the UserGroup entities by groupID
            return userGroups.stream()
                    .filter(userGroup -> userGroup.getUserID() == userId && userGroup.getStatus().equals(status))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error retrieving user groups by groupID: " + e.getMessage());
            return List.of(); // Return an empty list in case of an error
        }
    }

//    Retrieve a specific user groups
    public static Optional<UserGroup> get(int id) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<UserGroup> userGroups = getAll();
            return userGroups.stream().filter(userGroup -> userGroup.getID() == id).findFirst();
        } catch (Exception e) {
            System.out.println("Error retrieving user group: " + e.getMessage());
            return Optional.empty();
        }
    }

    public static Optional<UserGroup> get(int groupID, int userID) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<UserGroup> userGroups = getAll();
            return userGroups.stream().filter(userGroup -> userGroup.getGroupID() == groupID && userGroup.getUserID() == userID).findFirst();
        } catch (Exception e) {
            System.out.println("Error retrieving user group: " + e.getMessage());
            return Optional.empty();
        }
    }

//    Update a specific user's groups
    public static boolean update(int id, UserGroup updatedUserGroup) {
        System.out.println("alo");
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            updatedUserGroup.setID(id);

            return DataBaseManager.getDBM().updateEntity(
                    new TypeReference<List<UserGroup>>() {
            },
                    updatedUserGroup,
                    userGroup -> userGroup.getID() == id
            );

        } catch (IOException e) {
            System.out.println("Error updating group: " + e.getMessage());
            return false;
        }
    }

//    Delete a specific user group
    public static boolean delete(int id) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().deleteEntity(new TypeReference<List<UserGroup>>() {
            }, userGroup -> userGroup.getID() == id);
        } catch (IOException e) {
            System.out.println("Error deleting user group: " + e.getMessage());
            return false;
        }
    }

}
