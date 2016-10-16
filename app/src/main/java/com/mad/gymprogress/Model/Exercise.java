package com.mad.gymprogress.Model;

/**
 * Created by peerliss on 13/10/2016.
 */

public class Exercise {
    private String category;
    private String name;
    private int weight;
    private int reps;
    private int set;

    public Exercise(String category, String name, int weight, int reps, int set) {
        this.category = category;
        this.name = name;
        this.weight = weight;
        this.reps = reps;
        this.set = set;

    }

    public Exercise() {

    }

    public String getcategory() {
        return category;
    }

    public void setcategory(String category) {
        this.category = category;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public int getweight() {
        return weight;
    }

    public void setweight(int weight) {
        this.weight = weight;
    }

    public int getreps() {
        return reps;
    }

    public void setreps(int reps) {
        this.reps = reps;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }
}
