package org.omdb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.omdb.OMDBApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = OMDBApplication.class,properties = "spring.main.web-application-type=reactive")
public class OscarAwardsEndpointTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private WebTestClient webClient;

    @Test
    public void givenMovieTitleShouldReturnMovieDetails() {
        String movieTitle = "Avatar";
        String apiKey = getAPIKey("anil.r@gmail.com");
        System.out.println(apiKey);
        this.webClient.get().uri(uriBuilder -> uriBuilder.path( "/api/academy-awards")
                        .queryParam("title",movieTitle)
                        .queryParam("key", "{apiKey}")
                        .build(Map.of("apiKey",apiKey)))
                .accept(MediaType.APPLICATION_JSON)
                .exchange().expectStatus().isOk()
                .expectBody(List.class).consumeWith(res-> System.out.println(res.getResponseBody()));


    }
    private String getAPIKey(String email) {
       return  this.webClient.post().uri("/api/keys")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(Map.of("email",email)), Map.class)
                .accept(MediaType.APPLICATION_JSON).
                exchange().expectStatus().isCreated()
               .expectBody(Map.class).returnResult()
               .getResponseBody().get("apiKey").toString();
    }


}
