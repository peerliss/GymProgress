package com.mad.gymprogress;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class IndividualActivity extends AppCompatActivity {

    private static final String INDIVIDUAL_ACTIVITY = "Individual_Activity";
    private ArrayList<Exercise> shouldersList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mExerciseAdapter;
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

        mExerciseAdapter = new ExerciseAdapter(this, shouldersList);
        mRecyclerView.setAdapter(mExerciseAdapter);
        populateShoulderList();
    }

    private void populateShoulderList() {
        Log.d(INDIVIDUAL_ACTIVITY, "populateShoulderList");
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

}
