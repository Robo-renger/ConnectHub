package connecthub.mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.DataBaseManager;
import connecthub.entities.Group;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GroupMapper {

//    Static database file path
    private static final String DATABASE_FILE = "groups.json";

//    Set the database file for DataBaseManager during class loading
    static {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
    }

//    Create a new group
    public static void create(Group group) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            DataBaseManager.getDBM().createEntityWithID(group);
            group.creatorGroup();
        } catch (IOException e) {
            System.out.println("Error creating group: " + e.getMessage());
        }
    }

//    Retrieve all groups
    public static List<Group> getAll() {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().readEntities(new TypeReference<List<Group>>() {
            });
        } catch (IOException e) {
            System.out.println("Error retrieving all groups: " + e.getMessage());
            return List.of();
        }
    }

// Retrieve all Groups entities by CreatorID
    public static List<Group> getAllCreatedGroups(int creatorID) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<Group> groups = getAll();

            // Filter the UserGroup entities by UserID
            return groups.stream()
                    .filter(userGroup -> userGroup.getCreatorID() == creatorID)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error retrieving groups by creatorID: " + e.getMessage());
            return List.of(); // Return an empty list in case of an error
        }
    }

//    Retrieve a specific group
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

//    Update a specific group
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

//    Delete a specific group
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
