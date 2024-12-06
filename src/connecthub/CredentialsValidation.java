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

    private String email;
    private String password;

    public CredentialsValidation(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean validate(String data) {

        // Create a filter to find users by emailFilter
        Predicate<User> emailFilter = user -> user.getEmail().equals(email);

        // Pass the filter to UserMapper.get
        Optional<User> user = UserMapper.get(List.of(emailFilter));

        // Return true if user is found, false otherwise
        if (user.isPresent()) {
            System.out.println("User found: " + user.get().getEmail());
            User foundUser = user.get();

            try {
                // Comparing the entered password to the password in the database
                boolean isValidPassword = PasswordHasher.verifyPassword(PasswordHasher.hashPassword(password), foundUser.getPassword());
                if (isValidPassword) {
                    System.out.println("Correct: " + password + " " + foundUser.getPassword());
                    return true;
                } else {
                    System.out.println("Incorrect Password: " + password + " " + foundUser.getPassword());
                    return false;
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                System.out.println("Error validating password: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("No user found with the given emailFilter.");
            return false;
        }
    }
}
