package connecthub.mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.DataBaseManager;
import connecthub.entities.Blocked;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class BlockedMapper {

    // Static database file path
    private static final String DATABASE_FILE = "blocks.json";

    // Set the database file for DataBaseManager during class loading
    static {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
    }

    // Create a new blocked entity
    public static void create(Blocked blocked) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            DataBaseManager.getDBM().createEntityWithID(blocked);
        } catch (IOException e) {
            System.out.println("Error adding blocked entity to the database: " + e.getMessage());
        }
    }

    // Retrieve all blocked entities
    public static List<Blocked> getAll() {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().readEntities(new TypeReference<List<Blocked>>() {
            });
        } catch (IOException e) {
            System.out.println("Error retrieving all blocked entities: " + e.getMessage());
            return List.of();
        }
    }

    // Retrieve a specific blocked entity by ID
    public static Optional<Blocked> get(int id) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<Blocked> blockedList = getAll();
            return blockedList.stream().filter(blocked -> blocked.getID() == id).findFirst();
        } catch (Exception e) {
            System.out.println("Error retrieving blocked entity: " + e.getMessage());
            return Optional.empty();
        }
    }

    // Update a specific blocked entity
    public static boolean update(int id, Blocked updatedBlocked) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            updatedBlocked.setID(id);

            return DataBaseManager.getDBM().updateEntity(
                    new TypeReference<List<Blocked>>() {
            },
                    updatedBlocked,
                    blocked -> blocked.getID() == id
            );

        } catch (IOException e) {
            System.out.println("Error updating blocked entity: " + e.getMessage());
            return false;
        }
    }

    // Delete a specific blocked entity
    public static boolean delete(int id) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().deleteEntity(
                    new TypeReference<List<Blocked>>() {
            },
                    blocked -> blocked.getID() == id
            );
        } catch (IOException e) {
            System.out.println("Error deleting blocked entity: " + e.getMessage());
            return false;
        }
    }
}
