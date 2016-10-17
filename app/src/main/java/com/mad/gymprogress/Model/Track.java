package com.mad.gymprogress.Model;

import java.util.Date;

/**
 * Track class with constructors for all fields
 * Empty constructor
 * Getter/Setter for all fields
 */

public class Track {
    private String date;
    private String weight;
    private String fat;

    public Track(String date, String weight, String fat) {
        this.date = date;
        this.weight = weight;
        this.fat = fat;
    }

    public Track() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }
}
