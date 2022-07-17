package org.omdb.repository;

import org.omdb.domain.AcademyAward;
import org.omdb.domain.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface APIKeyRepository extends JpaRepository<ApiKey, BigInteger> {
    Optional<ApiKey> findByKey(String key);
}
