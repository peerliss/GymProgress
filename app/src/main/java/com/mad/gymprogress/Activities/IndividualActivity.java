/**
 * Activity to display list of exercises based on category selected by user
 */

package com.mad.gymprogress.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mad.gymprogress.Adapters.ExerciseAdapter;
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
    private ArrayList<Exercise> latsList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mExerciseAdapter;
    private String exercise = "Shoulders";
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton addFab;
    private EditText addCustomExerciseEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set view and toolbar
        setContentView(R.layout.activity_individual);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize RecyclerView and set its LayoutManager
        mRecyclerView = (RecyclerView) findViewById(R.id.individualRecyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Check intents and set exercise category accordingly
        try {
            if (getIntent().getStringExtra(MainActivity.EXERCISE_STRING) == null) {
                Bundle bundle = getIntent().getBundleExtra(AddExerciseActivity.ADD_EXERCISE_BUNDLE);
                exercise = bundle.getString(ExerciseAdapter.ViewHolder.EXERCISE_CATEGORY);
            } else
                exercise = getIntent().getStringExtra(MainActivity.EXERCISE_STRING);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Check exercise category and set RecyclerView adapter according to the corresponding category's list
        try {
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
            } else if (exercise.matches("Lats")) {
                mExerciseAdapter = new ExerciseAdapter(IndividualActivity.this, latsList);
                mRecyclerView.setAdapter(mExerciseAdapter);
                populateLatsList();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        addFab = (FloatingActionButton) findViewById(R.id.individual_addExerciseFab);

        // Add exercise in the exercise category list with an Exercise name according to user input
        addFab.setOnClickListener(new View.OnClickListener() {
            public String customExerciseStr;
            public String confirm = "false";

            @Override
            public void onClick(View v) {
                // Display an AlertDialog with a view containing an EditText
                AlertDialog.Builder builder = new AlertDialog.Builder(IndividualActivity.this);
                builder.setTitle("Please enter exercise name");
                LayoutInflater layoutInflater = LayoutInflater.from(IndividualActivity.this);
                View promptView = layoutInflater.inflate(R.layout.add_custom_exercise, null);
                builder.setView(promptView);

                // Initialize EditText
                addCustomExerciseEt = (EditText) promptView.findViewById(R.id.addCustomExerciseInput);

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setCancelable(true);

                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(INDIVIDUAL_ACTIVITY, "AlertDialog_Confirm");
                        addCustomExercise();
                    }

                    /**
                     * Check if EditText is empty
                     * Add Exercise with a name input by a user to the categories list
                     */
                    private void addCustomExercise() {
                        customExerciseStr = addCustomExerciseEt.getText().toString();
                        if (!TextUtils.isEmpty(customExerciseStr)) {
                            Exercise customExercise = new Exercise(exercise, customExerciseStr, 0, 0, 0);
                            returnExerciseList().add(customExercise);
                            mExerciseAdapter.notifyDataSetChanged();
                        } else
                            Toast.makeText(IndividualActivity.this, R.string.enter_exercise_name, Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create();
                builder.show();
            }
        });
    }

    /**
     * Return currently selected Categories ArrayList
     *
     * @return
     */
    protected ArrayList<Exercise> returnExerciseList() {
        if (exercise.matches("Shoulders")) {
            return shouldersList;
        } else if (exercise.matches("Biceps")) {
            return bicepsList;
        } else if (exercise.matches("Triceps")) {
            return tricepsList;
        } else if (exercise.matches("Chest")) {
            return chestList;
        } else if (exercise.matches("Back")) {
            return backList;
        } else if (exercise.matches("Abs")) {
            return absList;
        } else if (exercise.matches("Legs")) {
            return legsList;
        } else if (exercise.matches("Lats")) {
            return latsList;
        }
        return null;
    }

    private void populateShouldersList() {
        Log.d(INDIVIDUAL_ACTIVITY, "populateShouldersList");
        Exercise shoulders = new Exercise("Shoulders", "Side laterals to front raise", 0, 0, 0);
        shouldersList.add(shoulders);

        shoulders = new Exercise("Shoulders", "Single arm linear jammer", 0, 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Clean and press", 0, 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Standing palm in one arm dumbbell press", 0, 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Clean and jerk", 0, 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "One arm kettlebell push press", 0, 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Push press", 0, 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Standing military press", 0, 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Standing palms in dumbbell press", 0, 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Seated barbell military press", 0, 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Power partials", 0, 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Reverse flyes", 0, 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Seated dumbbell press", 0, 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Standing dumbbell press", 0, 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Standing dumbbell straight arm front delt raise above head", 0, 0, 0);
        shouldersList.add(shoulders);

        mExerciseAdapter.notifyDataSetChanged();
    }

    private void populateBicepsList() {
        Log.d(INDIVIDUAL_ACTIVITY, "populateBicepsList");
        Exercise biceps = new Exercise("Biceps", "Biceps curl to shoulder press", 0, 0, 0);
        bicepsList.add(biceps);

        biceps = new Exercise("Biceps", "Incline hammer curls", 0, 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "Wide grip standing barbell curl", 0, 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "Spider curl", 0, 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "EZ Bar curl", 0, 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "Hammer curl", 0, 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "Zottman curl", 0, 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "Barbell curl", 0, 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "Concentration curls", 0, 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "Dumbell bicep curl", 0, 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "Flexor incline dumbell curls", 0, 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "Machine bicep curl", 0, 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "Overhead cable curl", 0, 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "Close grip EZ bar curl", 0, 0, 0);
        bicepsList.add(biceps);
        biceps = new Exercise("Biceps", "Cross body hammer curl", 0, 0, 0);
        bicepsList.add(biceps);

        mExerciseAdapter.notifyDataSetChanged();
    }

    private void populateTricepsList() {
        Log.d(INDIVIDUAL_ACTIVITY, "populateTricepsList");
        Exercise triceps = new Exercise("Triceps", "Parallel bar dip", 0, 0, 0);
        tricepsList.add(triceps);

        triceps = new Exercise("Triceps", "Decline EZ bar triceps extension", 0, 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "Dips", 0, 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "Dumbbell floor press", 0, 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "Body up", 0, 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "Close grip barbbell bench press", 0, 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "Triceps pushdown - V-Bar attachment", 0, 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "Weighted bench dip", 0, 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "EZ bar skullcrushers", 0, 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "Kneeling cable triceps extension", 0, 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "Pushups - Close triceps position", 0, 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "Reverse grip triceps pushdown", 0, 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "Standing dumbell triceps extension", 0, 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "Cable one arm tricep extension", 0, 0, 0);
        tricepsList.add(triceps);
        triceps = new Exercise("Triceps", "Decline close grip bench to skull crusher", 0, 0, 0);
        tricepsList.add(triceps);

        mExerciseAdapter.notifyDataSetChanged();
    }

    private void populateChestList() {
        Log.d(INDIVIDUAL_ACTIVITY, "populateChestList");
        Exercise chest = new Exercise("Chest", "Dumbbell bench press", 0, 0, 0);
        chestList.add(chest);

        chest = new Exercise("Chest", "Pushups", 0, 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Dumbbell flyes", 0, 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Incline dumbbell press", 0, 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Low cable crossover", 0, 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Barbel bench press - medium grip", 0, 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Bodyweight flyes", 0, 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Decline dumbbell flyes", 0, 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Dips - chest version", 0, 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Incline cable flye", 0, 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Incline push up - wide", 0, 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Decline barbell bench press", 0, 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Incline dumbbell press reverse grip", 0, 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Wide grip barbell bench press", 0, 0, 0);
        chestList.add(chest);
        chest = new Exercise("Chest", "Wide grip decline barbell bench press", 0, 0, 0);
        chestList.add(chest);

        mExerciseAdapter.notifyDataSetChanged();
    }

    private void populateBackList() {
        Log.d(INDIVIDUAL_ACTIVITY, "populateBackList");
        Exercise back = new Exercise("Back", "Atlas stones", 0, 0, 0);
        backList.add(back);

        back = new Exercise("Back", "T-Bar row with handle", 0, 0, 0);
        backList.add(back);
        back = new Exercise("Back", "Deficit deadlift", 0, 0, 0);
        backList.add(back);
        back = new Exercise("Back", "Pendlay rown", 0, 0, 0);
        backList.add(back);
        back = new Exercise("Back", "Reverse grip bent over rows", 0, 0, 0);
        backList.add(back);
        back = new Exercise("Back", "Axle deadlift", 0, 0, 0);
        backList.add(back);
        back = new Exercise("Back", "Hyperextensions (Back extensions)", 0, 0, 0);
        backList.add(back);
        back = new Exercise("Back", "One arm dumbbell row", 0, 0, 0);
        backList.add(back);
        back = new Exercise("Back", "One arm long bar row", 0, 0, 0);
        backList.add(back);
        back = new Exercise("Back", "Bent over one arm long bar row", 0, 0, 0);
        backList.add(back);
        back = new Exercise("Back", "Bent over two arm long bar row", 0, 0, 0);
        backList.add(back);
        back = new Exercise("Back", "T-bar row", 0, 0, 0);
        backList.add(back);
        back = new Exercise("Back", "Bent over two dumbbell row with palms in", 0, 0, 0);
        backList.add(back);
        back = new Exercise("Back", "Bodyweight mid row", 0, 0, 0);
        backList.add(back);
        back = new Exercise("Back", "Dumbbell incline row", 0, 0, 0);
        backList.add(back);

        mExerciseAdapter.notifyDataSetChanged();
    }

    private void populateAbsList() {
        Log.d(INDIVIDUAL_ACTIVITY, "populateAbsList");
        Exercise abs = new Exercise("Abs", "Landmine 180's", 0, 0, 0);
        absList.add(abs);

        abs = new Exercise("Abs", "One arm medicine ball slam", 0, 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "Bottoms up", 0, 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "Plank", 0, 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "Spell caster", 0, 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "Suspended fallout", 0, 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "Decline reverse crunch", 0, 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "Spider crawl", 0, 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "Standing cable lift", 0, 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "Cocoons", 0, 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "Cross body crunch", 0, 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "Elbow to knee", 0, 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "One arm high pulley cable side bends", 0, 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "Decline crunch", 0, 0, 0);
        absList.add(abs);
        abs = new Exercise("Abs", "Gorilla chin/crunch", 0, 0, 0);
        absList.add(abs);

        mExerciseAdapter.notifyDataSetChanged();
    }

    private void populateLegsList() {
        Log.d(INDIVIDUAL_ACTIVITY, "populateLegsList");
        Exercise legs = new Exercise("Legs", "Single leg press", 0, 0, 0);
        legsList.add(legs);

        legs = new Exercise("Legs", "Clean from blocks", 0, 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Barbell full squat", 0, 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Smith machine calf raise", 0, 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Tire flip", 0, 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Box squat", 0, 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Hang clean", 0, 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Reverse band box squat", 0, 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Single leg push off", 0, 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Snatch", 0, 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Front squats with two kettlebells", 0, 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Rope jumping", 0, 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Barbell walking lunge", 0, 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Kettlebell pistol squat", 0, 0, 0);
        legsList.add(legs);
        legs = new Exercise("Legs", "Narrow stance leg press", 0, 0, 0);
        legsList.add(legs);

        mExerciseAdapter.notifyDataSetChanged();
    }

    private void populateLatsList() {
        Log.d(INDIVIDUAL_ACTIVITY, "populateLatsList");
        Exercise lats = new Exercise("Lats", "Wide grip pull up", 0, 0, 0);
        latsList.add(lats);

        lats = new Exercise("Lats", "Weighted pull ups", 0, 0, 0);
        latsList.add(lats);
        lats = new Exercise("Lats", "Pullups", 0, 0, 0);
        latsList.add(lats);
        lats = new Exercise("Lats", "Chin up", 0, 0, 0);
        latsList.add(lats);
        lats = new Exercise("Lats", "Machine assisted pull up", 0, 0, 0);
        latsList.add(lats);
        lats = new Exercise("Lats", "Rocky pull ups/pulldowns", 0, 0, 0);
        latsList.add(lats);
        lats = new Exercise("Lats", "Muscle up", 0, 0, 0);
        latsList.add(lats);
        lats = new Exercise("Lats", "Shotgun row", 0, 0, 0);
        latsList.add(lats);
        lats = new Exercise("Lats", "V bar pulldown", 0, 0, 0);
        latsList.add(lats);
        lats = new Exercise("Lats", "Close grip front lat pulldown", 0, 0, 0);
        latsList.add(lats);
        lats = new Exercise("Lats", "V bar pullup", 0, 0, 0);
        latsList.add(lats);
        lats = new Exercise("Lats", "Rope climb", 0, 0, 0);
        latsList.add(lats);
        lats = new Exercise("Lats", "Rope straight arm pulldown", 0, 0, 0);
        latsList.add(lats);
        lats = new Exercise("Lats", "Wide grip rear pull up", 0, 0, 0);
        latsList.add(lats);
        lats = new Exercise("Lats", "Wide grip lat pulldown", 0, 0, 0);
        latsList.add(lats);

        mExerciseAdapter.notifyDataSetChanged();
    }

}
