package org.omdb.controller;

import org.omdb.service.AcademyAwardsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/academy-awards")
public class AcademyAwardsController {
    public static final Logger LOGGER = LoggerFactory.getLogger(AcademyAwardsController.class);
    private final AcademyAwardsService academyAwardsService;

    public AcademyAwardsController(AcademyAwardsService academyAwardsService){
        this.academyAwardsService = academyAwardsService;
    }

    @GetMapping
    public Mono<ResponseEntity> getByTittle(@RequestParam String tittle) {
        LOGGER.debug("getByTittle");
        return  Mono.just(academyAwardsService.getByTittle(tittle));
    }


}
