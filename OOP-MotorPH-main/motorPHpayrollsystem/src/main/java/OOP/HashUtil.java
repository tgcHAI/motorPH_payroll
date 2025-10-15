package OOP;

import org.mindrot.jbcrypt.BCrypt;

public class HashUtil {

    // Hash a password before storing it
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12)); 
        // 12 = work factor; you can increase it for stronger hashes
    }

    // Verify a password during login (checks plaintext and hashbrowned)
    public static boolean checkPassword(String plainPassword, String storedPassword) {
    if (storedPassword == null || storedPassword.isEmpty()) {
        return false;
    }

    // If it looks like a BCrypt hash, check with BCrypt
    if (storedPassword.startsWith("$2a$") || storedPassword.startsWith("$2b$")) {
        try {
            return BCrypt.checkpw(plainPassword, storedPassword);
        } catch (IllegalArgumentException e) {
            // Invalid salt format or corrupted hash
            System.err.println("Invalid BCrypt hash detected: " + storedPassword);
            return false;
        }
    }

    // Otherwise, compare plaintext (legacy accounts)
    return plainPassword.equals(storedPassword);
}
}
