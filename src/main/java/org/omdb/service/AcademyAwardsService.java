package org.omdb.service;

import org.omdb.domain.AcademyAward;
import org.omdb.repository.AcademyAwardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AcademyAwardsService {
    private final AcademyAwardsRepository academyAwardsRepository;

    @Autowired
    public AcademyAwardsService(AcademyAwardsRepository academyAwardsRepository) {
        this.academyAwardsRepository = academyAwardsRepository;
    }

    public ResponseEntity getByTittle(String tittle) {
        List<AcademyAward> academyAwardList = academyAwardsRepository.findByCategoryAndNominee("Best Picture", tittle);
        if (CollectionUtils.isEmpty(academyAwardList)) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<List>(academyAwardList.stream().map(academyAward -> Map.of("tittle", academyAward.getNominee(), "year", academyAward.getYear(),
                "won", academyAward.getWon() ? "YES" : "NO")).collect(Collectors.toList()), HttpStatus.OK);
    }
}
