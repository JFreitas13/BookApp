package com.svalero.bookApp.domain;

import java.io.Serializable;

public class Bookstore implements Serializable {

    private long id;
    private String name;
    private String city;
    private String zipCode;
    private String phoneNumber;
    private double latitude;
    private double longitud;

    public Bookstore(String name, String city, String zipCode, String phoneNumber, double latitude, double longitud) {
        this.name = name;
        this.city = city;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.latitude = latitude;
        this.longitud = longitud;
    }

    public Bookstore(String name, String city, String zipCode, String phoneNumber) {
        this.name = name;
        this.city = city;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
