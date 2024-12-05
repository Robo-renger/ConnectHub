package connecthub.mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.DataBaseManager;
import connecthub.entities.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class UserMapper {

    // Static database file path
    private static final String DATABASE_FILE = "users.json";

    static {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        // Set the database file for DataBaseManager during class loading
    }

    // Retrieve a single user by Filters(username or email,.....)
    public static Optional<User> get(List<Predicate<User>> filters) {
        try {
            List<User> users = DataBaseManager.getDBM().readEntities(new TypeReference<List<User>>() {
            });

            return users.stream()
                    .filter(user -> filters.stream().allMatch(filter -> filter.test(user))) // Apply all filters
                    .findFirst();  // Return the first matching user
        } catch (IOException e) {
            System.out.println("Error retrieving user: " + e.getMessage());
            return Optional.empty();
        }
    }

    // Retrieve a single user by id
    public static Optional<User> get(int userId) {
        try {
            List<User> users = DataBaseManager.getDBM().readEntities(new TypeReference<List<User>>() {
            });
            return users.stream().filter(user -> user.getID() == userId).findFirst();
        } catch (IOException e) {
            System.out.println("Error retrieving user: " + e.getMessage());
            return Optional.empty();
        }
    }

    // Retrieve all users
    public static List<User> getAll() {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
//        System.out.println("aloooo");
        try {
            return DataBaseManager.getDBM().readEntities(new TypeReference<List<User>>() {
            });
        } catch (IOException e) {
            System.out.println("Error retrieving all users: " + e.getMessage());
            return List.of(); // Return an empty list on failure
        }
    }

    // Create a new user
    public static void create(User user) {
        try {
            DataBaseManager.getDBM().createEntityWithID(user);
        } catch (IOException e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }

    // Delete a user by email
    public static boolean delete(int id) {
        try {
            return DataBaseManager.getDBM().deleteEntity(new TypeReference<List<User>>() {
            }, user -> user.getID() == id);
        } catch (IOException e) {
            System.out.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }

    // Update an existing user
    public static boolean update(int id, User updatedUser) {
        try {
            updatedUser.setID(id);

            return DataBaseManager.getDBM().updateEntity(
                    new TypeReference<List<User>>() {},
                    updatedUser,
                    user -> user.getID() == id
            );
            
        } catch (IOException e) {
            System.out.println("Error updating user: " + e.getMessage());
            return false;
        }
    }

}
