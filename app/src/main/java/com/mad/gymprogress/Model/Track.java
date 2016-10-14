package com.mad.gymprogress.Model;

import java.util.Date;

/**
 * Created by peerliss on 14/10/2016.
 */

public class Track {
    private String date;
    private int weight;
    private int bodyFat;

    public Track(String date, int weight, int bodyFat) {
        this.date = date;
        this.weight = weight;
        this.bodyFat = bodyFat;
    }

    public Track() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(int bodyFat) {
        this.bodyFat = bodyFat;
    }
}
