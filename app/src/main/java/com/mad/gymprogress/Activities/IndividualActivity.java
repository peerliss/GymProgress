package com.mad.gymprogress.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.mad.gymprogress.Adapter.ExerciseAdapter;
import com.mad.gymprogress.Model.Exercise;
import com.mad.gymprogress.R;

import java.util.ArrayList;

public class IndividualActivity extends AppCompatActivity {

    private static final String INDIVIDUAL_ACTIVITY = "Individual_Activity";
    private static final String EXERCISE = "Exercise";
    private ArrayList<Exercise> shouldersList = new ArrayList<>();
    private ArrayList<Exercise> bicepsList = new ArrayList<>();
    private ArrayList<Exercise> tricepsList = new ArrayList<>();
    private ArrayList<Exercise> chestList = new ArrayList<>();
    private ArrayList<Exercise> backList = new ArrayList<>();
    private ArrayList<Exercise> absList = new ArrayList<>();
    private ArrayList<Exercise> legsList = new ArrayList<>();
    private ArrayList<Exercise> cardioList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mExerciseAdapter;
    private String exercise = "Shoulders";
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.individualRecyclerView);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

//        if (savedInstanceState != null) {
//            exercise = savedInstanceState.getString(EXERCISE);
//        }
//        else {
//            exercise = getIntent().getStringExtra(MainActivity.EXERCISE_STRING);
//        }

        exercise = getIntent().getStringExtra(MainActivity.EXERCISE_STRING);

//        try {
            if (exercise.matches("Shoulders")) {
                mExerciseAdapter = new ExerciseAdapter(IndividualActivity.this, shouldersList);
                mRecyclerView.setAdapter(mExerciseAdapter);
                populateShouldersList();
            } else if (exercise.matches("Biceps")) {
                mExerciseAdapter = new ExerciseAdapter(IndividualActivity.this, bicepsList);
                mRecyclerView.setAdapter(mExerciseAdapter);
                populateBicepsList();
            } else if (exercise.matches("Triceps")) {
                mExerciseAdapter = new ExerciseAdapter(IndividualActivity.this, tricepsList);
                mRecyclerView.setAdapter(mExerciseAdapter);
                populateTricepsList();
            } else if (exercise.matches("Chest")) {
                mExerciseAdapter = new ExerciseAdapter(IndividualActivity.this, chestList);
                mRecyclerView.setAdapter(mExerciseAdapter);
                populateChestList();
            } else if (exercise.matches("Back")) {
                mExerciseAdapter = new ExerciseAdapter(IndividualActivity.this, backList);
                mRecyclerView.setAdapter(mExerciseAdapter);
                populateBackList();
            } else if (exercise.matches("Abs")) {
                mExerciseAdapter = new ExerciseAdapter(IndividualActivity.this, absList);
                mRecyclerView.setAdapter(mExerciseAdapter);
                populateAbsList();
            } else if (exercise.matches("Legs")) {
                mExerciseAdapter = new ExerciseAdapter(IndividualActivity.this, legsList);
                mRecyclerView.setAdapter(mExerciseAdapter);
                populateLegsList();
            } else if (exercise.matches("Cardio")) {
                mExerciseAdapter = new ExerciseAdapter(IndividualActivity.this, cardioList);
                mRecyclerView.setAdapter(mExerciseAdapter);
                populateCardioList();
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        exercise = getIntent().getStringExtra(MainActivity.EXERCISE_STRING);
        outState.putString(EXERCISE, exercise);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        exercise = savedInstanceState.getString(EXERCISE);
    }

    private void populateShouldersList() {
        Log.d(INDIVIDUAL_ACTIVITY, "populateShouldersList");
        Exercise shoulders = new Exercise("Shoulders", "Side laterals to front raise", 0, 0);
        shouldersList.add(shoulders);

        shoulders = new Exercise("Shoulders", "Single arm linear jammer", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Clean and press", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Standing palm in one arm dumbbell press", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Clean and jerk", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "One arm kettlebell push press", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Push press", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Standing military press", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Standing palms in dumbbell press", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Seated barbell military press", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Power partials", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Reverse flyes", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Seated dumbbell press", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Standing dumbbell press", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Standing dumbbell straight arm front delt raise above head", 0, 0);
        shouldersList.add(shoulders);

        mExerciseAdapter.notifyDataSetChanged();
    }

    private void populateBicepsList() {
        Log.d(INDIVIDUAL_ACTIVITY, "populateBicepsList");
        Exercise biceps = new Exercise("Biceps", "Biceps curl to shoulder press", 0, 0);
        bicepsList.add(biceps);

        biceps = new Exercise("Biceps", "Incline hammer curls", 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "Wide grip standing barbell curl", 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "Spider curl", 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "EZ Bar curl", 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "Hammer curl", 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "Zottman curl", 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "Barbell curl", 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "Concentration curls", 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "Dumbell bicep curl", 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "Flexor incline dumbell curls", 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "Machine bicep curl", 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "Overhead cable curl", 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "Close grip EZ bar curl", 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "Cross body hammer curl", 0, 0);
        bicepsList.add(biceps);

        mExerciseAdapter.notifyDataSetChanged();
    }

    private void populateTricepsList() {
        Log.d(INDIVIDUAL_ACTIVITY, "populateTricepsList");
        Exercise triceps = new Exercise("Triceps", "Parallel bar dip", 0, 0);
        tricepsList.add(triceps);

        triceps = new Exercise("Triceps", "Decline EZ bar triceps extension", 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "Dips", 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "Dumbbell floor press", 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "Body up", 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "Close grip barbbell bench press", 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "Triceps pushdown - V-Bar attachment", 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "Weighted bench dip", 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "EZ bar skullcrushers", 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "Kneeling cable triceps extension", 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "Pushups - Close triceps position", 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "Reverse grip triceps pushdown", 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "Standing dumbell triceps extension", 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "Cable one arm tricep extension", 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "Decline close grip bench to skull crusher", 0, 0);
        tricepsList.add(triceps);

        mExerciseAdapter.notifyDataSetChanged();
    }

    private void populateChestList() {
        Log.d(INDIVIDUAL_ACTIVITY, "populateChestList");
        Exercise chest = new Exercise("Chest", "Dumbbell bench press", 0, 0);
        chestList.add(chest);

        chest = new Exercise("Chest", "Pushups", 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Dumbbell flyes", 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Incline dumbbell press", 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Low cable crossover", 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Barbel bench press - medium grip", 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Bodyweight flyes", 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Decline dumbbell flyes", 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Dips - chest version", 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Incline cable flye", 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Incline push up - wide", 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Decline barbell bench press", 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Incline dumbbell press reverse grip", 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Wide grip barbell bench press", 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Wide grip decline barbell bench press", 0, 0);
        chestList.add(chest);

        mExerciseAdapter.notifyDataSetChanged();
    }

    private void populateBackList() {
        Log.d(INDIVIDUAL_ACTIVITY, "populateBackList");
        Exercise back = new Exercise("Back", "Atlas stones", 0, 0);
        backList.add(back);

        back = new Exercise("Back", "T-Bar row with handle", 0, 0);
        backList.add(back);
        back = new Exercise("Back", "Deficit deadlift", 0, 0);
        backList.add(back);
        back = new Exercise("Back", "Pendlay rown", 0, 0);
        backList.add(back);
        back = new Exercise("Back", "Reverse grip bent over rows", 0, 0);
        backList.add(back);
        back = new Exercise("Back", "Axle deadlift", 0, 0);
        backList.add(back);
        back = new Exercise("Back", "Hyperextensions (Back extensions)", 0, 0);
        backList.add(back);
        back = new Exercise("Back", "One arm dumbbell row", 0, 0);
        backList.add(back);
        back = new Exercise("Back", "One arm long bar row", 0, 0);
        backList.add(back);
        back = new Exercise("Back", "Bent over one arm long bar row", 0, 0);
        backList.add(back);
        back = new Exercise("Back", "Bent over two arm long bar row", 0, 0);
        backList.add(back);
        back = new Exercise("Back", "T-bar row", 0, 0);
        backList.add(back);
        back = new Exercise("Back", "Bent over two dumbbell row with palms in", 0, 0);
        backList.add(back);
        back = new Exercise("Back", "Bodyweight mid row", 0, 0);
        backList.add(back);
        back = new Exercise("Back", "Dumbbell incline row", 0, 0);
        backList.add(back);

        mExerciseAdapter.notifyDataSetChanged();
    }

    private void populateAbsList() {
        Log.d(INDIVIDUAL_ACTIVITY, "populateAbsList");
        Exercise abs = new Exercise("Abs", "Landmine 180's", 0, 0);
        absList.add(abs);

        abs = new Exercise("Abs", "One arm medicine ball slam", 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "Bottoms up", 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "Plank", 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "Spell caster", 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "Suspended fallout", 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "Decline reverse crunch", 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "Spider crawl", 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "Standing cable lift", 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "Cocoons", 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "Cross body crunch", 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "Elbow to knee", 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "One arm high pulley cable side bends", 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "Decline crunch", 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "Gorilla chin/crunch", 0, 0);
        absList.add(abs);

        mExerciseAdapter.notifyDataSetChanged();
    }

    private void populateLegsList() {
        Log.d(INDIVIDUAL_ACTIVITY, "populateLegsList");
        Exercise legs = new Exercise("Legs", "Single leg press", 0, 0);
        legsList.add(legs);

        legs = new Exercise("Legs", "Clean from blocks", 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Barbell full squat", 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Smith machine calf raise", 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Tire flip", 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Box squat", 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Hang clean", 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Reverse band box squat", 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Single leg push off", 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Snatch", 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Front squats with two kettlebells", 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Rope jumping", 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Barbell walking lunge", 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Kettlebell pistol squat", 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Narrow stance leg press", 0, 0);
        legsList.add(legs);

        mExerciseAdapter.notifyDataSetChanged();
    }

    private void populateCardioList() {
        Log.d(INDIVIDUAL_ACTIVITY, "populateCardioList");
        Exercise cardio = new Exercise("Cardio", "Rope jumping", 0, 0);
        cardioList.add(cardio);

        cardio = new Exercise("Cardio", "Step mill", 0, 0);
        cardioList.add(cardio);
        cardio = new Exercise("Cardio", "Jog in place", 0, 0);
        cardioList.add(cardio);
        cardio = new Exercise("Cardio", "Bicycling", 0, 0);
        cardioList.add(cardio);
        cardio = new Exercise("Cardio", "Rowing, stationary", 0, 0);
        cardioList.add(cardio);
        cardio = new Exercise("Cardio", "Elliptical trainer", 0, 0);
        cardioList.add(cardio);
        cardio = new Exercise("Cardio", "Stairmaster", 0, 0);
        cardioList.add(cardio);
        cardio = new Exercise("Cardio", "Trail running/walking", 0, 0);
        cardioList.add(cardio);
        cardio = new Exercise("Cardio", "Burpee", 0, 0);
        cardioList.add(cardio);
        cardio = new Exercise("Cardio", "Running, treadmill", 0, 0);
        cardioList.add(cardio);
        cardio = new Exercise("Cardio", "Skating", 0, 0);
        cardioList.add(cardio);
        cardio = new Exercise("Cardio", "Bicycling, stationary", 0, 0);
        cardioList.add(cardio);
        cardio = new Exercise("Cardio", "Jogging, treadmill", 0, 0);
        cardioList.add(cardio);
        cardio = new Exercise("Cardio", "Butt kicks", 0, 0);
        cardioList.add(cardio);
        cardio = new Exercise("Cardio", "Recumbent bike", 0, 0);
        cardioList.add(cardio);

        mExerciseAdapter.notifyDataSetChanged();
    }

}
