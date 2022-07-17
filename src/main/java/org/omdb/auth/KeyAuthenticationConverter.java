/**
 * Copyright (C) 2020  Greg Whitaker
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package org.omdb.auth;

import org.omdb.domain.ApiKey;
import org.omdb.repository.APIKeyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Converter that gets the API key from the incoming headers and converts it to an {@link Authentication}
 * that can be checked by the {@link KeyAuthenticationManager}.
 */
@Component
public class KeyAuthenticationConverter implements ServerAuthenticationConverter {
    private static final Logger LOG = LoggerFactory.getLogger(KeyAuthenticationConverter.class);
    private static final String API_KEY_HEADER_NAME = "key";

    private final APIKeyLoader apiKeyLoader;
    @Autowired
    public KeyAuthenticationConverter(APIKeyLoader apiKeyLoader) {
        this.apiKeyLoader = apiKeyLoader;
    }

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange)
                .flatMap(serverWebExchange -> Mono.justOrEmpty(serverWebExchange.getRequest().getQueryParams().get(API_KEY_HEADER_NAME)))
                .filter(headerValues -> !headerValues.isEmpty())
                .flatMap(headerValues -> lookup(headerValues.get(0)));
    }

    /**
     * Lookup authentication token in cache.
     *
     * @param apiKey api key
     * @return
     */
    private Mono<KeyAuthenticationToken> lookup(final String apiKey) {
        if(apiKey == null) return Mono.empty();
        Optional<ApiKey> key = apiKeyLoader.getAPIKey(apiKey);
        if(key.isPresent()) {
            return Mono.just(new KeyAuthenticationToken(key.get().getKey(), key.get().getEmail()));
        } else {
            return Mono.just(new KeyAuthenticationToken(null, null));
        }

    }

    /**
     * Loads the cache authentication data from the database.
     */
    @Component
    private static class APIKeyLoader  {
        @Autowired
        private final APIKeyRepository apiKeyRepository;
        public APIKeyLoader(APIKeyRepository apiKeyRepository) {
            this.apiKeyRepository = apiKeyRepository;

        }
        public Optional<ApiKey> getAPIKey(String key) {
           return apiKeyRepository.findByKey(key);
        }

    }
}
