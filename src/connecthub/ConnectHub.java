/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package connecthub;

import connecthub.entities.User;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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
        FileHandler fileHandler = new FileHandler("users.json", "a");
        User user = new User("john.doe@example.com", "Johns Doe", "12345", LocalDate.of(1995, 5, 15));
        try {
            fileHandler.writeToFile(user); // Writes the User object as JSON
        } catch (IOException ex) {
            Logger.getLogger(ConnectHub.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            List<User> users = fileHandler.readFromFile(new TypeReference<List<User>>() {
            });
            for (User userr : users) {
                System.out.println("Name: " + userr.getUsername() + ", Email: " + userr.getEmail()+" Password: "+ userr.getPassword());
            }
        } catch (IOException ex) {
            Logger.getLogger(ConnectHub.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
