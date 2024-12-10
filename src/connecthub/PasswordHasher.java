package connecthub;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordHasher {

    // Static salt (shared across all passwords)
    private static final String STATIC_SALT = "StaticSaltValue";

    // Hash a password with the static salt
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        // Combine password and static salt
        String saltedPassword = password + STATIC_SALT;
        Throwable throwable = new Throwable();

        // Print the stack trace to the standard output
//        throwable.printStackTrace();
//        System.out.println("pasword:" + password);
//        System.out.println("hashed counter");
        // Hash the salted password
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(saltedPassword.getBytes());

        // Encode the hash as a Base64 string
        return Base64.getEncoder().encodeToString(hashedBytes);
    }

    // Verify a password by hashing it with the static salt and comparing it to the stored hash
    public static boolean verifyPassword(String password, String hashedPassword) throws NoSuchAlgorithmException {
        // Combine input password and static salt
        String saltedPassword = password + STATIC_SALT;
//        System.out.println("salted pass:" + saltedPassword);
        // Hash the salted password
        String hashedInputPassword = hashPassword(password);
//        System.out.println("hashed input: " + hashedInputPassword);
        // Compare the hashed input password with the stored hashed password
        return hashedInputPassword.equals(hashedPassword);
    }
}
