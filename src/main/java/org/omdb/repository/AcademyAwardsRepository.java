package org.omdb.repository;

import org.omdb.domain.AcademyAward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface AcademyAwardsRepository extends JpaRepository<AcademyAward, BigInteger> {

    List<AcademyAward> findByCategoryAndNominee(String category,String nominee);
}
