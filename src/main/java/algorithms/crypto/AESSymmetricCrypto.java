package algorithms.crypto;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static com.google.common.base.Preconditions.checkNotNull;
import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

/**
 * This implementation require Unlimited JCE for Java 7 edition (download from Oracle site)
 * Otherwise Secret key size should be decreased to 128 bits
 */
public class AESSymmetricCrypto {

    public static final String AES = "AES";
    public static final int KEY_SIZE_BITS = 256;

    private static final int BLOCK_SIZE = 16;
    private static final String CIPHER_IMPL = "AES/CBC/PKCS5Padding";

    private static final KeyGenerator keyGenerator;
    private static final SecureRandom random = new SecureRandom();


    static {
        try {
            keyGenerator = KeyGenerator.getInstance(AES);
            keyGenerator.init(KEY_SIZE_BITS);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }


    public byte[] encryptMessage(String message, SecretKey key, IvParameterSpec vector) {
        checkNotNull(message);
        checkNotNull(key);
        checkNotNull(vector);
        final ByteArrayOutputStream encryptedStream = new ByteArrayOutputStream();
        try (OutputStreamWriter writer = new OutputStreamWriter(
                new CipherOutputStream(encryptedStream, createEncryptCipher(key, vector)))) {
            writer.write(message);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return encryptedStream.toByteArray();
    }

    public String decryptMessage(byte[] encryptedBytes, SecretKey key, IvParameterSpec vector) {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(encryptedBytes);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new CipherInputStream(inputStream, createDecryptCipher(key, vector))))) {
            return reader.readLine();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public SecretKey generateSecretKey() {
        return keyGenerator.generateKey();
    }

    public IvParameterSpec createInitVector() {
        byte[] buffer = new byte[BLOCK_SIZE];
        random.nextBytes(buffer);
        return new IvParameterSpec(buffer);
    }

    private Cipher createEncryptCipher(SecretKey key, IvParameterSpec vector) throws Exception {
        final Cipher aesCipher = Cipher.getInstance(CIPHER_IMPL);
        aesCipher.init(ENCRYPT_MODE, key, vector);
        return aesCipher;
    }

    private Cipher createDecryptCipher(SecretKey key, IvParameterSpec vector) throws Exception {
        final Cipher aesCipher = Cipher.getInstance(CIPHER_IMPL);
        aesCipher.init(DECRYPT_MODE, key, vector);
        return aesCipher;
    }

}
