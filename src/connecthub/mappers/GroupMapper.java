package connecthub.mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.DataBaseManager;
import connecthub.entities.Group;
import connecthub.entities.Profile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class GroupMapper {

//    Static database file path
    private static final String DATABASE_FILE = "groups.json";

//    Set the database file for DataBaseManager during class loading
    static {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
    }

//    Create a new profile
    public static void create(Group group) {
        try {
            DataBaseManager.getDBM().createEntityWithID(group);
        } catch (IOException e) {
            System.out.println("Error creating profile: " + e.getMessage());
        }
    }

//    Retrieve all Profiles
    public static List<Group> getAll() {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().readEntities(new TypeReference<List<Group>>() {
            });
        } catch (IOException e) {
            System.out.println("Error retrieving all profiles: " + e.getMessage());
            return List.of();
        }
    }

//    Retrieve a specific user's profile
    public static Optional<Group> get(int id) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<Group> groups = getAll();
            return groups.stream().filter(group -> group.getID() == id).findFirst();
        } catch (Exception e) {
            System.out.println("Error retrieving group: " + e.getMessage());
            return Optional.empty();
        }
    }

//    Update a specific user's profile
    public static boolean update(int id, Group updatedGroup) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            updatedGroup.setID(id);

            return DataBaseManager.getDBM().updateEntity(
                    new TypeReference<List<Group>>() {
            },
                    updatedGroup,
                    group -> group.getID() == id
            );

        } catch (IOException e) {
            System.out.println("Error updating group: " + e.getMessage());
            return false;
        }
    }

//    Delete a specific user's profile
    public static boolean delete(int id) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().deleteEntity(new TypeReference<List<Group>>() {
            }, group -> group.getID() == id);
        } catch (IOException e) {
            System.out.println("Error deleting group: " + e.getMessage());
            return false;
        }
    }

}
