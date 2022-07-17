package org.omdb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.omdb.OMDBApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = OMDBApplication.class,properties = "spring.main.web-application-type=reactive")
public class EndpointTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private WebTestClient webClient;

    @Test
    public void givenMovieTittleShouldReturnMovieDetails() {
        String movieTittle = "Avatar";
        this.webClient.get().uri("/films/"+movieTittle).accept(MediaType.APPLICATION_JSON)
                .exchange().expectStatus().isOk()
                .expectBody(List.class).consumeWith(res-> System.out.println(res.getResponseBody()));


    }


}
