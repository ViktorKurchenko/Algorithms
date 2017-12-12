package algorithms.crypto;

import org.junit.Test;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import static algorithms.crypto.AESSymmetricCrypto.AES;
import static algorithms.crypto.AESSymmetricCrypto.KEY_SIZE_BITS;
import static org.assertj.core.api.Assertions.assertThat;

public class AESSymmetricCryptoTest {

    private static final AESSymmetricCrypto AES_CRYPTO = new AESSymmetricCrypto();


    @Test
    public void test_AES_secretKeyGeneration() {
        final SecretKey KEY = AES_CRYPTO.generateSecretKey();

        assertThat(KEY).isNotNull();
        assertThat(KEY.getAlgorithm()).isEqualTo(AES);
        assertThat(KEY.getEncoded().length).isEqualTo(KEY_SIZE_BITS / 8);
    }

    @Test
    public void test_AES_encryptDecryptMessage() {
        final String MESSAGE = "Test message!";
        final SecretKey KEY = AES_CRYPTO.generateSecretKey();
        final IvParameterSpec VECTOR = AES_CRYPTO.createInitVector();

        byte[] encryptedBytes = AES_CRYPTO.encryptMessage(MESSAGE, KEY, VECTOR);
        String decryptedMessage = AES_CRYPTO.decryptMessage(encryptedBytes, KEY, VECTOR);

        assertThat(decryptedMessage).isEqualTo(MESSAGE);
    }

}