package OOP;

import org.mindrot.jbcrypt.BCrypt;

public class HashUtil {

    // Hash a password before storing it
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12)); 
        // 12 = work factor; you can increase it for stronger hashes
    }

    // Verify a password during login
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        if (hashedPassword == null || hashedPassword.isEmpty()) {
            return false;
        }
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
