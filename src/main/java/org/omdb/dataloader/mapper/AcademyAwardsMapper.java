package org.omdb.dataloader.mapper;

import org.omdb.dataloader.AcademyAwardsData;
import org.omdb.domain.AcademyAward;

public class AcademyAwardsMapper {
    public static AcademyAward map(AcademyAwardsData academyAwardsData) {
        AcademyAward awardsData = new AcademyAward();
        awardsData.setCategory(academyAwardsData.getCategory().trim());
        parseAndBuildYear(academyAwardsData, awardsData);
        awardsData.setNominee(academyAwardsData.getNominee().trim());
        awardsData.setAdditionalInfo(academyAwardsData.getAdditionalInfo().trim());
        awardsData.setWon(academyAwardsData.getWon().equalsIgnoreCase("YES"));
        return awardsData;
    }

    private static void parseAndBuildYear(AcademyAwardsData academyAwardsData, AcademyAward awardsData) {
        String[] yearAndAddition = academyAwardsData.getYear().split("\\(");
        awardsData.setEdition(Integer.parseInt(yearAndAddition[1].split("(\\D)")[0]));
        awardsData.setYear(yearAndAddition[0]);
    }
}
