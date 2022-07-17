package org.omdb.dataloader;

import org.omdb.domain.ApiKey;
import org.omdb.repository.APIKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import static javax.crypto.Cipher.ENCRYPT_MODE;

@Component
public class DefaultAPIKeyIntializer {
    private static final String CIPHER = "AES";

    @Autowired
    private  APIKeyRepository apiKeyRepository;
    public DefaultAPIKeyIntializer(APIKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;

    }
    @PostConstruct
    public void loadApiKeys() throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        String email = "anil.ravula@gmail.com";
        ApiKey apiKey =   new ApiKey();
        Key keyGenerated = getKeyFromKeyGenerator(CIPHER,128);
        apiKey.setKey(Base64.getEncoder().encodeToString(encrypt(email,keyGenerated)));
        apiKey.setCreatedOn(LocalDateTime.now());
        apiKey.setEmail(email);
        apiKey.setExpireDays(60);
        System.out.println(apiKey.getKey());
        apiKeyRepository.save(apiKey);
    }

    private static Key getKeyFromKeyGenerator(String cipher, int keySize) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(cipher);
        keyGenerator.init(keySize);
        return keyGenerator.generateKey();
    }
    private static byte[] encrypt(String plainText, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(ENCRYPT_MODE, key);
        byte[] cipherTextBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        System.out.println(cipherTextBytes.toString());
        return cipherTextBytes;
    }

}
