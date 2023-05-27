package com.svalero.bookApp.domain;

import java.io.Serializable;

public class Book implements Serializable {

    private long id;
    private String name;
    private int yearEdition;
    private String ageRecommended;
    private int pagesNumber;
    private String description;
    private boolean eBook;

    public Book(long id, String name, int yearEdition, String ageRecommended, int pagesNumber, String description, boolean eBook) {
        this.id = id;
        this.name = name;
        this.yearEdition = yearEdition;
        this.ageRecommended = ageRecommended;
        this.pagesNumber = pagesNumber;
        this.description = description;
        this.eBook = eBook;
    }

    public Book(String name, int yearEdition, int pagesNumber, String description, boolean eBook) {
        this.name = name;
        this.yearEdition = yearEdition;
        this.pagesNumber = pagesNumber;
        this.description = description;
        this.eBook = eBook;
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
