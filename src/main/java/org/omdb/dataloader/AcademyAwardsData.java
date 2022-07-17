package org.omdb.dataloader;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

public class AcademyAwardsData {

    @CsvBindByName(column = "Year")
    private String year;

    @CsvBindByName(column = "Category")
    private String category;

    @CsvBindByName(column = "Nominee")

    private String nominee;

    @CsvBindByName(column = "Additional Info")

    private String additionalInfo;
    @CsvBindByName(column = "Won?")

    private String won;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNominee() {
        return nominee;
    }

    public void setNominee(String nominee) {
        this.nominee = nominee;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getWon() {
        return won;
    }

    public void setWon(String won) {
        this.won = won;
    }

}
