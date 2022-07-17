package org.omdb.service;

import org.omdb.controller.APIKeyController;
import org.omdb.domain.ApiKey;
import org.omdb.repository.APIKeyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;

import static javax.crypto.Cipher.ENCRYPT_MODE;

@Service
public class APIKeysCreationService {
    public static final Logger LOGGER = LoggerFactory.getLogger(APIKeyController.class);
    private final APIKeyRepository apiKeyRepository;
    private static final String CIPHER = "AES";

    @Autowired
    public APIKeysCreationService(APIKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    public ResponseEntity<Map<String, Object>> createApiKey(Map<String, Object> request) {
        try {
            ApiKey key = apiKeyRepository.save(buildApIkey((String) request.get("email")));
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("apiKey", key.getKey()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private ApiKey buildApIkey(String email) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        ApiKey apiKey = new ApiKey();
        Key keyGenerated = getKeyFromKeyGenerator(CIPHER, 128);
        apiKey.setKey(Base64.getEncoder().encodeToString(encrypt(email, keyGenerated)));
        apiKey.setCreatedOn(LocalDateTime.now());
        apiKey.setEmail(email);
        apiKey.setExpireDays(60);
        return apiKey;
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
