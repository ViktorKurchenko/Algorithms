package algorithms.crypto;

import org.junit.Test;

import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import static algorithms.crypto.RSAAsymmetricCrypto.RSA;
import static org.assertj.core.api.Assertions.assertThat;

public class RSAAsymmetricCryptoTest {

    private static final RSAAsymmetricCrypto RSA_CRYPTO = new RSAAsymmetricCrypto();


    @Test
    public void test_generateKeyPair() {
        final KeyPair KEY_PAIR = RSA_CRYPTO.generateKeyPair();

        assertThat(KEY_PAIR).isNotNull();
        assertThat(KEY_PAIR.getPrivate()).isNotNull();
        assertThat(KEY_PAIR.getPublic()).isNotNull();
        assertThat(KEY_PAIR.getPublic().getAlgorithm()).isEqualTo(RSA);
    }

    @Test
    public void test_RSA_encryptDecryptSecretKey() {
        final KeyPair KEY_PAIR = RSA_CRYPTO.generateKeyPair();
        final PrivateKey privateKey = KEY_PAIR.getPrivate();
        final PublicKey publicKey = KEY_PAIR.getPublic();

        final AESSymmetricCrypto aesCrypto = new AESSymmetricCrypto();
        final SecretKey secretKey = aesCrypto.generateSecretKey();

        final byte[] encryptSecretKey = RSA_CRYPTO.encryptSecretKey(publicKey, secretKey);
        final byte[] decryptedSecretKey = RSA_CRYPTO.decryptSecretKey(privateKey, encryptSecretKey);

        assertThat(decryptedSecretKey).isEqualTo(secretKey.getEncoded());
    }

    @Test
    public void test_verifySignature() {
        final KeyPair KEY_PAIR = RSA_CRYPTO.generateKeyPair();
        final PrivateKey privateKey = KEY_PAIR.getPrivate();
        final PublicKey publicKey = KEY_PAIR.getPublic();

        final String MESSAGE = "Test message!";

        final byte[] signedMessage = RSA_CRYPTO.signMessage(privateKey, MESSAGE);
        RSA_CRYPTO.verifySignature(publicKey, MESSAGE, signedMessage);
    }

}