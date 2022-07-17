package org.omdb.dataloader;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
       read(Paths.get(ClassLoader.getSystemResource(dataPath).toURI()));

    }

    public  List<String[]> read(Path filePath) throws Exception {
        List<String[]> list = new ArrayList<>();
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
              //  .withIgnoreQuotations(true)
                .build();
        System.out.println(filePath.getFileName());
        try (Reader reader = new FileReader(filePath.toFile())) {

            CSVReaderBuilder csvReaderBuilder = new CSVReaderBuilder(reader)
                    .withSkipLines(1)
                    .withCSVParser(parser);

            try (CSVReader csvReader = csvReaderBuilder.build()) {
                CsvToBean<AcademyAwardsData> cb = new CsvToBeanBuilder<AcademyAwardsData>(reader)
                        .withType(AcademyAwardsData.class)
                        .build();
                List<AcademyAwardsData> allData = cb.parse();
                List<AcademyAward> data = allData.stream().map(AcademyAwardsMapper::map).collect(Collectors.toList());
                System.out.println(data);
                academyAwardsRepository.saveAll(data);
                System.out.println("done");
            }

        }
        return list;
    }
}
