/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.CredentialsValidation;
import connecthub.DataBaseManager;
import connecthub.entities.User;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 *
 * @author User
 */
public class LoggedInMapper {

    private static final String DATABASE_FILE = "loggedUser.json";

    static {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
    }

    // Retrieve a single user by Filters(username or email, etc.)
    public static Optional<User> get(List<Predicate<User>> filters) {
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
}
