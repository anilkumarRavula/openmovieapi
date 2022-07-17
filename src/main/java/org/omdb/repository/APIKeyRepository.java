package org.omdb.repository;

import org.omdb.domain.AcademyAward;
import org.omdb.domain.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface APIKeyRepository extends JpaRepository<ApiKey, BigInteger> {
}
