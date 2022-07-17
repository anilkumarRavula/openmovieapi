package org.omdb.controller;

import org.omdb.repository.AcademyAwardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmSearchController {
    @Autowired
    private RequestMappingHandlerMapping urlHandlerMapping;
    @Autowired
    private AcademyAwardsRepository academyAwardsRepository;

    public FilmSearchController() {
        System.out.println("Created Object");
    }
    @PostConstruct
    public void init() {
        System.out.println(urlHandlerMapping.getHandlerMethods());
    }

    @GetMapping("/{name}")
    public Mono<ResponseEntity> getByName(@PathVariable String name) {
        return  Mono.just(academyAwardsRepository.findByCategoryAndNominee("Best Picture",name)).map(res-> new ResponseEntity<List>(res, HttpStatus.OK));
    }


}
