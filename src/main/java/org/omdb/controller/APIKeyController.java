package org.omdb.controller;

import org.omdb.repository.APIKeyRepository;
import org.omdb.service.APIKeysCreationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/keys")
public class APIKeyController {
    public static final Logger LOGGER = LoggerFactory.getLogger(APIKeyController.class);

    private final APIKeysCreationService apiKeysCreationService;
    @Autowired
    public APIKeyController(APIKeysCreationService apiKeysCreationService) {
        this.apiKeysCreationService = apiKeysCreationService;
    }

    @PostMapping
    public Mono<ResponseEntity> create(@RequestBody Map<String,Object> apiKeyREquest) {
        LOGGER.info("getByName");
        return  Mono.just(apiKeysCreationService.createApiKey(apiKeyREquest));
    }


}
