package com.mad.gymprogress;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Exercise exercise = null;

//        Bundle exerciseBundle = getIntent().getBundleExtra(ExerciseAdapter.ViewHolder.EXERCISE_INTENT);
//        if (exerciseBundle != null) {
//            exercise = (Exercise) exerciseBundle.getSerializable(ExerciseAdapter.ViewHolder.EXERCISE_INTENT);
//        }

        Bundle exerciseBundle = getIntent().getBundleExtra(ExerciseAdapter.ViewHolder.EXERCISE_BUNDLE);

        TextView exerciseTitle = (TextView) findViewById(R.id.exerciseTitle);
        EditText weightEt = (EditText) findViewById(R.id.weightEt);
        EditText fatEt = (EditText) findViewById(R.id.repsEt);
        Button addSetBtn = (Button) findViewById(R.id.addSetBtn);
        Button clearBtn = (Button) findViewById(R.id.clearBtn);
        Button doneBtn = (Button) findViewById(R.id.addExerciseDoneBtn);

        try {
            String exerciseTitleString = exerciseBundle.getString(ExerciseAdapter.ViewHolder.EXERCISE_NAME);
            exerciseTitle.setText(exerciseTitleString);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
