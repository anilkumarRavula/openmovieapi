package org.omdb.dataloader;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.omdb.dataloader.mapper.AcademyAwardsMapper;
import org.omdb.domain.AcademyAward;
import org.omdb.repository.AcademyAwardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataLoader implements ApplicationRunner {

    @Value("${data.seed.path:db/academy_awards.csv}")
    private String dataPath;

    @Autowired
    private AcademyAwardsRepository academyAwardsRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        read(getResourceFileAsInputStream(dataPath));

    }

    public Reader getReader(InputStream is) {
        if (is != null) {
            return new BufferedReader(new InputStreamReader(is));
        } else {
            throw new RuntimeException("resource not found");
        }
    }

    public InputStream getResourceFileAsInputStream(String fileName) throws IOException {
        ClassLoader classLoader = DataLoader.class.getClassLoader();
        return classLoader.getResource(fileName).openStream();
    }

    public List<String[]> read(InputStream stream) throws Exception {
        List<String[]> list = new ArrayList<>();
        try (Reader reader = getReader(stream)) {

            CsvToBean<AcademyAwardsData> cb = new CsvToBeanBuilder<AcademyAwardsData>(reader)
                    .withType(AcademyAwardsData.class)
                    .build();
            List<AcademyAwardsData> allData = cb.parse();
            List<AcademyAward> data = allData.stream().map(AcademyAwardsMapper::map).collect(Collectors.toList());
            System.out.println(data);
            academyAwardsRepository.saveAll(data);

        }
        return list;
    }
}
