package connecthub.mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.CredentialsValidation;
import connecthub.DataBaseManager;
import connecthub.Factory;
import connecthub.builders.UserBuilder;
import connecthub.entities.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class UserMapper {

    private static final String DATABASE_FILE = "users.json";
    private static User loggedInUser;

    static {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
    }

    // Retrieve a single user by Filters(username or email, etc.)
    public static Optional<User> get(List<Predicate<User>> filters) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);

        try {
            List<User> users = DataBaseManager.getDBM().readEntities(new TypeReference<List<User>>() {
            });
            return users.stream()
                    .filter(user -> filters.stream().allMatch(filter -> filter.test(user))) // Apply all filters
                    .findFirst();
        } catch (IOException e) {
            System.out.println("Error retrieving user: " + e.getMessage());
            return Optional.empty();
        }
    }

    // Retrieve a single user by id
    public static Optional<User> get(int userId) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
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
        try {
            return DataBaseManager.getDBM().readEntities(new TypeReference<List<User>>() {
            });
        } catch (IOException e) {
            System.out.println("Error retrieving all users: " + e.getMessage());
            return List.of(); // Return an empty list on failure
        }
    }

    // Create a new user with unique email
    public static void create(User user) throws IllegalArgumentException {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<User> users = DataBaseManager.getDBM().readEntities(new TypeReference<List<User>>() {
            });

            // Check for duplicate email
            if (users.stream().anyMatch(existingUser -> existingUser.getEmail().equals(user.getEmail()))) {
                throw new IllegalArgumentException("Email already exists: " + user.getEmail());
            }

            DataBaseManager.getDBM().createEntityWithID(user);
        } catch (IOException e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }

    // Delete a user by id
    public static boolean delete(int id) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().deleteEntity(new TypeReference<List<User>>() {
            }, user -> user.getID() == id);
        } catch (IOException e) {
            System.out.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }

    // Update an existing user with unique email
    public static boolean update(int id, User updatedUser) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);

        try {
            List<User> users = DataBaseManager.getDBM().readEntities(new TypeReference<List<User>>() {
            });

            // Check for duplicate email (excluding the user being updated)
            if (users.stream().anyMatch(existingUser -> existingUser.getEmail().equals(updatedUser.getEmail()) && existingUser.getID() != id)) {
                throw new IllegalArgumentException("Email already exists: " + updatedUser.getEmail());
            }

            updatedUser.setID(id);
            return DataBaseManager.getDBM().updateEntity(
                    new TypeReference<List<User>>() {
            },
                    updatedUser,
                    user -> user.getID() == id
            );
        } catch (IOException e) {
            System.out.println("Error updating user: " + e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static Optional<User> login(String loginEmail, String loginUserpassword) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        connecthub.CredentialsValidation cv = new CredentialsValidation(loginEmail, loginUserpassword);
        if (cv.validate("")) {
            Predicate<User> emailFilter = user -> user.getEmail().equals(loginEmail);
            Optional<User> user = UserMapper.get(List.of(emailFilter));

            if (user.isPresent()) {
                loggedInUser = user.get();
//                UserBuilder.getInstance().setEntity(loggedInUser);
//                User updatedUser = (User) Factory.createEntity(UserBuilder.getInstance());
                loggedInUser.setStatus("online");
                update(loggedInUser.getID(), loggedInUser);
                LoggedInMapper.create(loggedInUser);
                return Optional.of(loggedInUser);
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    public static Optional<User> getLoggedInUser() {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        if (loggedInUser != null) {
            return Optional.of(loggedInUser); // Return the logged-in user wrapped in Optional
        }
        return Optional.empty(); // Return an empty Optional if no user is logged in
    }

    public static void signOut() {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);

        if (loggedInUser != null) {
            // Delete from the LoggedInMapper
            boolean deleted = LoggedInMapper.delete(loggedInUser.getID());
            if (!deleted) {
                System.out.println("Failed to delete logged-in user from LoggedInMapper.");
            }

            // Retrieve the user to update their status
            Optional<User> logged = get(loggedInUser.getID());
            if (logged.isPresent()) {
                User toUpdate = logged.get();
                toUpdate.setStatus("offline");
                boolean updated = update(toUpdate.getID(), toUpdate);
                if (!updated) {
                    System.out.println("Failed to update user status to 'offline'.");
                }
            } else {
                System.out.println("User not found in the database.");
            }

            // Clear the logged-in user reference
            loggedInUser = null;
        } else {
            System.out.println("No user is currently logged in.");
        }
    }
}
