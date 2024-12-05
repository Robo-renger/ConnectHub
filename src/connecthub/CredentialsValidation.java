package connecthub;

import connecthub.entities.User;
import connecthub.interfaces.ValidationStrategy;
import connecthub.mappers.UserMapper;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class CredentialsValidation implements ValidationStrategy {
    
    private String userName;
    private String password;

    public CredentialsValidation(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    
    /**
     * 
     * @param data pass any data, we are ignoring it anyway
     * @return true if credentials are valid, false otherwise
     */
    @Override
    public boolean validate(String data) {
        
         // Create a filter to find users by username
        Predicate<User> usernameFilter = user -> user.getUsername().equals(userName);

        // Pass the filter to UserMapper.get
        Optional<User> user = UserMapper.get(List.of(usernameFilter));

        // Return true if user is found, false otherwise
        if (user.isPresent())
        {
            System.out.println("User found: " + user.get().getUsername());
            User foundUser = user.get();
            
            try{
                // Comparing the entered password to the password in the database
                boolean isValidPassword = PasswordHasher.verifyPassword(password, foundUser.getPassword());
                if(isValidPassword)
                    return true;
                else
                {
                    System.out.println("Incorrect Password: " + password + " " + foundUser.getPassword());
                    return false;
                }
            }catch(NoSuchAlgorithmException | InvalidKeySpecException e)
            {
                System.out.println("Error validating password: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("No user found with the given username.");
            return false;
        }
    }
}
