package algorithms.crypto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.security.*;

import static com.google.common.base.Preconditions.checkNotNull;
import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

public class RSAAsymmetricCrypto {

    public static final String RSA = "RSA";

    private static int KEY_SIZE_BITS = 2048;

    private static final String CIPHER_IMPL = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";
    private static final String SIGNATURE_IMPL = "SHA256withRSA";

    private static final KeyPairGenerator generator;

    static {
        try {
            generator = KeyPairGenerator.getInstance(RSA);
            generator.initialize(KEY_SIZE_BITS);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public KeyPair generateKeyPair() {
        return generator.generateKeyPair();
    }

    public byte[] encryptSecretKey(PublicKey publicKey, SecretKey secretKey) {
        checkNotNull(publicKey);
        checkNotNull(secretKey);
        try {
            final Cipher rsaCipher = Cipher.getInstance(CIPHER_IMPL);
            rsaCipher.init(ENCRYPT_MODE, publicKey);
            return rsaCipher.doFinal(secretKey.getEncoded());
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public byte[] decryptSecretKey(PrivateKey privateKey, byte[] encryptedKey) {
        checkNotNull(privateKey);
        checkNotNull(encryptedKey);
        try {
            final Cipher rsaCipher = Cipher.getInstance(CIPHER_IMPL);
            rsaCipher.init(DECRYPT_MODE, privateKey);
            return rsaCipher.doFinal(encryptedKey);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public byte[] signMessage(PrivateKey key, String message) {
        checkNotNull(key);
        checkNotNull(message);
        try {
            final Signature signature = Signature.getInstance(SIGNATURE_IMPL);
            signature.initSign(key);
            signature.update(message.getBytes());
            return signature.sign();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public void verifySignature(PublicKey key, String message, byte[] signedMessage) {
        checkNotNull(key);
        checkNotNull(message);
        checkNotNull(signedMessage);
        try {
            final Signature signature = Signature.getInstance(SIGNATURE_IMPL);
            signature.initVerify(key);
            signature.update(message.getBytes());
            if (!signature.verify(signedMessage)) {
                throw new IllegalStateException("Broken signature!");
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
