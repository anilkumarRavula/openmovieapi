package org.omdb.dataloader;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.omdb.service.AcademyAwardsService;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class DataLoaderTest {

    DataLoader dataLoader ;
    AcademyAwardsService academyAwardsService;
    public DataLoaderTest(@Mock AcademyAwardsService academyAwardsService) {
        dataLoader = new DataLoader(academyAwardsService);
    }

    @Test
    public void test_run() throws Exception {
        dataLoader.run(null);
        Mockito.verify(academyAwardsService).saveAll(any());

    }

}