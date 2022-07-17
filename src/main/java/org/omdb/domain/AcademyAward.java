package org.omdb.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "academy_award")
public class AcademyAward {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String year;

    private Integer edition;

    private String category;

    private String nominee;

    private String additionalInfo;

    private Boolean won;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
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

    public void setNominee(String filmItem) {
        this.nominee = filmItem;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Boolean getWon() {
        return won;
    }

    public void setWon(Boolean won) {
        this.won = won;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademyAward that = (AcademyAward) o;
        return Objects.equals(id, that.id) && Objects.equals(year, that.year) && Objects.equals(edition, that.edition) && Objects.equals(category, that.category) && Objects.equals(nominee, that.nominee) && Objects.equals(additionalInfo, that.additionalInfo) && Objects.equals(won, that.won);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, edition, category, nominee, additionalInfo, won);
    }

    @Override
    public String toString() {
        return "AcademyAward{" +
                "id=" + id +
                ", year='" + year + '\'' +
                ", no=" + edition +
                ", category='" + category + '\'' +
                ", nominee='" + nominee + '\'' +
                ", won=" + won +
                '}';
    }
}
