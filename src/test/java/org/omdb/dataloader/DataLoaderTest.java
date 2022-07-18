package org.omdb.dataloader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.omdb.repository.AcademyAwardsRepository;
import org.omdb.service.AcademyAwardsService;
import org.springframework.test.util.ReflectionTestUtils;

import javax.annotation.security.RunAs;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class DataLoaderTest {
    DataLoader dataLoader ;
    @Mock
    AcademyAwardsService academyAwardsService;
    @BeforeEach
    public void setUp() {
        dataLoader = new DataLoader(academyAwardsService);
    }

    @Test
    public void test_run() throws Exception {
        ReflectionTestUtils.setField(dataLoader, "dataPath", "db/academy_awards.csv");
        dataLoader.run(null);
        Mockito.verify(academyAwardsService).saveAll(any());

    }

}