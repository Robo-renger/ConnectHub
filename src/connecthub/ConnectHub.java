/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package connecthub;

import connecthub.entities.User;
import connecthub.mappers.UserMapper;
import com.fasterxml.jackson.core.type.TypeReference;
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

        //Creating a new user in the database
//        User newUser = new User("roborenger72@gmail.com", "Ziad", "12341231", LocalDate.of(2003, 10, 26));
//        UserMapper.create(newUser);
//
//        //Retrieving all users
//        List<User> usersList = UserMapper.getAll();
//        for (User user : usersList) {
//            System.out.println("ID: " + user.getID() + " Name: " + user.getUsername() + ", Email: " + user.getEmail() + " Password: " + user.getPassword() + " Date: " + user.getDateOfBirth());
//        }
//
//        //Deleting the user from the database using its id
//        UserMapper.delete(0);
//
//        //Update User using ID
//        User updatedUser = new User("roborenger72@gmail.com", "sha3boly", "12341231", LocalDate.of(2003, 10, 26));
//        UserMapper.update(12,updatedUser);
//        
//        //Retrieve User by Filters
//        Predicate<User> filterByEmail = user -> user.getEmail().equals("roborenger72@gmail.com");
//        Predicate<User> filterByStatus = user -> user.getStatus().equals("offline");
//        Predicate<User> filterById = user -> user.getID() == 15;
//        
//        List<Predicate<User>> filters = List.of(filterByEmail, filterByStatus, filterById);
//
//        // Retrieve the user based on the filters
//        Optional<User> user = UserMapper.get(filters);
//
//        // Output the result
//        user.ifPresentOrElse(
//                u -> System.out.println("User found: " + u.getUsername()),
//                () -> System.out.println("No user found with the given filters.")
//        );
//        //Retrieving a user only using its id
//        Optional<User> userByID = UserMapper.get(15);
//        userByID.ifPresentOrElse(
//                u -> System.out.println("User found: " + u.getUsername()),
//                () -> System.out.println("No user found with the given filters.")
//        );

        System.out.println(Validator.validate(0, "user123@gmail.com"));
        System.out.println(Validator.validate(0, "User123@GMAIL.com"));  
    
    }

}
