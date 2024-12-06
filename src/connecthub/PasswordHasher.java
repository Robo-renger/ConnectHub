package connecthub;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordHasher {

    // Hash a password with the static salt
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedBytes);
    }
    // Verify a password by comparing it with a stored hash
    public static boolean verifyPassword(String password, String hashedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String hashedInputPassword = hashPassword(password);
        return hashedInputPassword.equals(hashedPassword);
    }
}
