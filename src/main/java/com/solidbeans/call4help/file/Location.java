package com.solidbeans.call4help.file;


public class Location {
    private String region;
    private String city;


    public Location() {
    }

    public Location(String region, String city) {
        this.region = region;
        this.city = city;
    }


    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
