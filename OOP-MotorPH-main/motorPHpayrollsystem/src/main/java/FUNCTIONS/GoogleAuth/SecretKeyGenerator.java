/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FUNCTIONS.GoogleAuth;

/**
 *
 * @author Tone
 */
import org.apache.commons.codec.binary.Base32;
import java.security.SecureRandom;

public class SecretKeyGenerator {

    /**
     * Generates a cryptographically strong, random secret key.
     * The key is 20 bytes long (160 bits) and is Base32 encoded.
     * @return A randomly generated secret key as a Base32-encoded string.
     */
    public static String generateSecretKey() {
        // Generate a random byte array
        byte[] buffer = new byte[20];
        new SecureRandom().nextBytes(buffer);

        // Encode the byte array to a Base32 string
        Base32 base32 = new Base32();
        return base32.encodeAsString(buffer);
    }

    public static void main(String[] args) {
        String secret = generateSecretKey();
        System.out.println("Generated Secret Key: " + secret);
    }
}
