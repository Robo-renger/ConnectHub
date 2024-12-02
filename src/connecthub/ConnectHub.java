/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package connecthub;

import connecthub.entities.User;
import connecthub.mappers.UserMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.entities.Profile;
import connecthub.mappers.ProfileMapper;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class ConnectHub {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        // ########### User test cases ###########
        
        // Test Case 1: Create a new user
//        testCreateUser();

        // Test Case 2: Retrieve all users
//        testGetAllUsers();

        // Test Case 3: Delete a user by ID
//        testDeleteUserById();

        // Test Case 4: Update an existing user
//        testUpdateUser();

        // Test Case 5: Retrieve a user with filters
//        testRetrieveUserWithFilters();

        // Test Case 6: Retrieve a user by ID
//        testRetrieveUserById();

//        System.out.println(Validator.validate(0, "user123@gmail.com"));
//        System.out.println(Validator.validate(0, "User123@GMAIL.com"));  

        // ########### Profile test cases ###########

        // Test Case 1: Create a new profile
//        testCreateProfile();

        // Test Case 2: Retrieve all profiles
//        testGetAllProfiles();

        // Test Case 3: Retrieve a profile by user ID
//        testGetProfileByUserId();

        // Test Case 4: Update an existing profile
//        testUpdateProfile();

        // Test Case 5: Delete a profile
//        testDeleteProfile();

        // Test Case 6: Handle non-existing user ID
//        testNonExistingUserId();

        // Test Case 7: Handle empty database
//        testEmptyDatabase();

    }
    
    // ########### User test cases ###########  
    
    private static void testCreateUser() {
        System.out.println("Running Test Case 1: Create User");
        User newUser = new User("roborenger72@gmail.com", "Ziad", "12341231", LocalDate.of(2003, 10, 26));
        UserMapper.create(newUser);
        System.out.println("User created successfully.");
    }

    private static void testGetAllUsers() {
        System.out.println("Running Test Case 2: Get All Users");
        List<User> usersList = UserMapper.getAll();
        if (usersList.isEmpty()) {
            System.out.println("No users found.");
        } else {
            usersList.forEach(user -> System.out.println("ID: " + user.getID() + " Name: " + user.getUsername() +
                    ", Email: " + user.getEmail() + " Password: " + user.getPassword() + " Date: " + user.getDateOfBirth()));
        }
    }

    private static void testDeleteUserById() {
        System.out.println("Running Test Case 3: Delete User By ID");
        UserMapper.delete(0);
        System.out.println("User deleted successfully.");
    }

    private static void testUpdateUser() {
        System.out.println("Running Test Case 4: Update User");
        User updatedUser = new User("roborenger72@gmail.com", "sha3boly", "12341231", LocalDate.of(2003, 10, 26));
        UserMapper.update(12, updatedUser);
        System.out.println("User updated successfully.");
    }

    private static void testRetrieveUserWithFilters() {
        System.out.println("Running Test Case 5: Retrieve User with Filters");

        Predicate<User> filterByEmail = user -> user.getEmail().equals("roborenger72@gmail.com");
        Predicate<User> filterByStatus = user -> user.getStatus().equals("offline");
        Predicate<User> filterById = user -> user.getID() == 15;

        List<Predicate<User>> filters = List.of(filterByEmail, filterByStatus, filterById);

        Optional<User> user = UserMapper.get(filters);
        user.ifPresentOrElse(
                u -> System.out.println("User found: " + u.getUsername()),
                () -> System.out.println("No user found with the given filters.")
        );
    }
    
    private static void testRetrieveUserById() {
        System.out.println("Running Test Case 6: Retrieve User by ID");
        Optional<User> userByID = UserMapper.get(15);
        userByID.ifPresentOrElse(
                u -> System.out.println("User found: " + u.getUsername()),
                () -> System.out.println("No user found with the given ID.")
        );
    }
    
    // ########### Profile test cases ###########    
    
     private static void testCreateProfile() {
        System.out.println("Running Test Case 1: Create Profile");
        Profile profile = new Profile(1, "Hello, world!", "path/to/profile.jpg", "path/to/cover.jpg");
        ProfileMapper.create(profile);
        System.out.println("Profile created successfully.");
    }

    private static void testGetAllProfiles() {
        System.out.println("Running Test Case 2: Get All Profiles");
        var profiles = ProfileMapper.getAll();
        if (profiles.isEmpty()) {
            System.out.println("No profiles found.");
        } else {
            System.out.println("Retrieved profiles: " + profiles.size());
        }
    }

    private static void testGetProfileByUserId() {
        System.out.println("Running Test Case 3: Get Profile by User ID");
        Optional<Profile> profile = ProfileMapper.get(4);
        if (profile.isPresent()) {
            System.out.println("Profile found " + profile.get().getBio());
        } else {
            System.out.println("No profile found");
        }
    }

    private static void testUpdateProfile() {
        System.out.println("Running Test Case 4: Update Profile");
        Profile updatedProfile = new Profile(11, "Updated Bio", "new/path/profile.jpg", "new/path/cover.jpg");
        boolean isUpdated = ProfileMapper.update(2, updatedProfile);
        if (isUpdated) {
            System.out.println("Profile updated successfully.");
        } else {
            System.out.println("Failed to update profile.");
        }
    }

    private static void testDeleteProfile() {
        System.out.println("Running Test Case 5: Delete Profile");
        boolean isDeleted = ProfileMapper.delete(1);
        if (isDeleted) {
            System.out.println("Profile deleted successfully.");
        } else {
            System.out.println("Failed to delete profile.");
        }
    }

    private static void testNonExistingUserId() {
        System.out.println("Running Test Case 6: Non-Existing User ID");
        Optional<Profile> profile = ProfileMapper.get(99);
        if (profile.isEmpty()) {
            System.out.println("Correctly handled non-existing User ID.");
        } else {
            System.out.println("Unexpected profile found: " + profile.get().getBio());
        }
    }

    private static void testEmptyDatabase() {
        System.out.println("Running Test Case 7: Empty Database");
        var profiles = ProfileMapper.getAll();
        if (profiles.isEmpty()) {
            System.out.println("Empty database handled correctly.");
        } else {
            System.out.println("Unexpected data found in empty database.");
        }
    }
}
