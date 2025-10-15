package OOP;

import org.mindrot.jbcrypt.BCrypt;

public class HashUtil {

    private static final int BCRYPT_LOG_ROUNDS = 12;
    public static String hashPassword(String plainPassword) {
        if (plainPassword == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(BCRYPT_LOG_ROUNDS));
    }
    public static boolean checkPassword(String plainPassword, String storedPassword) {
        if (storedPassword == null || storedPassword.isEmpty()) {
            return false;
        }

        // Handle BCrypt hashes (including $2y$ for compatibility)
        if (storedPassword.startsWith("$2a$") || 
            storedPassword.startsWith("$2b$") || 
            storedPassword.startsWith("$2y$")) {
            try {
                return BCrypt.checkpw(plainPassword, storedPassword);
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid BCrypt hash: " + storedPassword);
                return false;
            }
        }

        // Legacy fallback: plaintext comparison (log for audit)
        System.out.println("WARNING: Plaintext password comparison used for: " + 
                          (storedPassword.length() > 10 ? storedPassword.substring(0, 10) + "..." : storedPassword));
        return plainPassword != null && plainPassword.equals(storedPassword);
    }
}