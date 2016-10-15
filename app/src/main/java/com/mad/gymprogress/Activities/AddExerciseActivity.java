package com.mad.gymprogress.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mad.gymprogress.Adapter.ExerciseAdapter;
import com.mad.gymprogress.R;

public class AddExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle exerciseBundle = getIntent().getBundleExtra(ExerciseAdapter.ViewHolder.EXERCISE_BUNDLE);

        TextView exerciseTitle = (TextView) findViewById(R.id.exerciseTitle);
        EditText weightEt = (EditText) findViewById(R.id.weightEt);
        EditText fatEt = (EditText) findViewById(R.id.repsEt);
        Button addSetBtn = (Button) findViewById(R.id.addSetBtn);
        Button clearBtn = (Button) findViewById(R.id.clearBtn);
        Button doneBtn = (Button) findViewById(R.id.addExerciseDoneBtn);

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        try {
            String exerciseTitleString = exerciseBundle.getString(ExerciseAdapter.ViewHolder.EXERCISE_NAME);
            exerciseTitle.setText(exerciseTitleString);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}