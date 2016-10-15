package com.mad.gymprogress.Model;

/**
 * Created by peerliss on 13/10/2016.
 */

public class Exercise {
    private String mCategory;
    private String mName;
    private int mWeight;
    private int mReps;

    public Exercise(String mCategory, String mName, int mWeight, int mReps) {
        this.mCategory = mCategory;
        this.mName = mName;
        this.mWeight = mWeight;
        this.mReps = mReps;
    }

    /*public Exercise(String mName, int mWeight, int mReps) {
        this.mName = mName;
        this.mWeight = mWeight;
        this.mReps = mReps;
    }

    public Exercise(String mCategory, String mName) {
        this.mCategory = mCategory;
        this.mName = mName;
    }*/

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmWeight() {
        return mWeight;
    }

    public void setmWeight(int mWeight) {
        this.mWeight = mWeight;
    }

    public int getmReps() {
        return mReps;
    }

    public void setmReps(int mReps) {
        this.mReps = mReps;
    }
}
