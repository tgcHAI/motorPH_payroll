package FUNCTIONS.GoogleAuth;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * Generates QR codes for TOTP setup (compatible with Google Authenticator, Authy, etc.).
 */
public class QRCodeGenerator {

    /**
     * Generates a QR code PNG file from the given data.
     * @param data Text to encode (e.g., otpauth:// URL)
     * @param filePath Output file path (e.g., "secret.png")
     * @param width Image width in pixels
     * @param height Image height in pixels
     */
    public static void generateQRCode(String data, String filePath, int width, int height)
            throws WriterException, IOException {
        if (data == null || data.isEmpty()) 
            throw new IllegalArgumentException("Data cannot be null or empty");
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("Width and height must be positive");

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);
        Path path = new File(filePath).toPath();
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
}