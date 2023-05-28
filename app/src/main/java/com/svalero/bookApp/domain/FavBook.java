package com.svalero.bookApp.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FavBook {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private int yearEdition;
    @ColumnInfo
    private String ageRecommended;
    @ColumnInfo
    private int pagesNumber;
    @ColumnInfo
    private String description;
    @ColumnInfo
    private boolean eBook;

    public FavBook(long id, String name, int yearEdition, String ageRecommended, int pagesNumber, String description, boolean eBook) {
        this.id = id;
        this.name = name;
        this.yearEdition = yearEdition;
        this.ageRecommended = ageRecommended;
        this.pagesNumber = pagesNumber;
        this.description = description;
        this.eBook = eBook;
    }

    public FavBook() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearEdition() {
        return yearEdition;
    }

    public String getYearEditionString() {

        String yearEditionString = String.valueOf(yearEdition);
        return yearEditionString;
    }

    public void setYearEdition(int yearEdition) {
        this.yearEdition = yearEdition;
    }

    public String getAgeRecommended() {
        return ageRecommended;
    }

    public void setAgeRecommended(String ageRecommended) {
        this.ageRecommended = ageRecommended;
    }

    public int getPagesNumber() {
        return pagesNumber;
    }

    public String getPagesNumberString() {
        String pagesNumberString = String.valueOf(pagesNumber);
        return pagesNumberString;
    }

    public void setPagesNumber(int pagesNumber) {
        this.pagesNumber = pagesNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEBook() {
        return eBook;
    }

    public void setEBook(boolean eBook) {
        this.eBook = eBook;
    }

}
