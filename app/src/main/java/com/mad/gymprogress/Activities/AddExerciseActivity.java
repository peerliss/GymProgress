package com.mad.gymprogress.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mad.gymprogress.Adapters.ExerciseAdapter;
import com.mad.gymprogress.Fragments.HistoryFragment;
import com.mad.gymprogress.Model.Exercise;
import com.mad.gymprogress.Model.Track;
import com.mad.gymprogress.R;

public class AddExerciseActivity extends AppCompatActivity {

    public static final String ADD_EXERCISE_BUNDLE = "Add_Exercise_Bundle";
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private String uid;
    private int set = 0;
    private String exerciseName;
    private Bundle exerciseBundle;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private EditText weightEt;
    private EditText repsEt;
    private String dateStr;
    private String repsStr;
    private String weightStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        uid = user.getUid();

        recyclerView = (RecyclerView) findViewById(R.id.addExerciseRecyclerView);
        layoutManager = new LinearLayoutManager(AddExerciseActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        exerciseBundle = getIntent().getBundleExtra(ExerciseAdapter.ViewHolder.EXERCISE_BUNDLE);

        TextView exerciseTitle = (TextView) findViewById(R.id.exerciseTitle);
        weightEt = (EditText) findViewById(R.id.weightEt);
        repsEt = (EditText) findViewById(R.id.repsEt);
        Button addSetBtn = (Button) findViewById(R.id.addSetBtn);
        Button clearBtn = (Button) findViewById(R.id.clearBtn);
        Button doneBtn = (Button) findViewById(R.id.addExerciseDoneBtn);

        dateStr = (String) DateFormat.format("dd-MM-yy", new java.util.Date());
        exerciseName = exerciseBundle.getString(ExerciseAdapter.ViewHolder.EXERCISE_NAME);

        addSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFields()) {

                    checkSet();
                    if (set > 5) {
                        Toast.makeText(AddExerciseActivity.this, R.string.cannot_add_sets, Toast.LENGTH_LONG).show();
                        return;
                    }
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Workouts").child("Date").child(dateStr).child(exerciseName).child("Set " + set);

                    if (databaseReference != null) {
                        databaseReference.child("category").setValue(exerciseBundle.getString(ExerciseAdapter.ViewHolder.EXERCISE_CATEGORY));
                        databaseReference.child("name").setValue(exerciseName);
                        databaseReference.child("weight").setValue(Integer.parseInt(weightEt.getText().toString()));
                        databaseReference.child("reps").setValue(Integer.parseInt(repsEt.getText().toString()));
                        databaseReference.child("set").setValue(set);

                        Toast.makeText(AddExerciseActivity.this, R.string.successfully_added, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        try {
            exerciseTitle.setText(exerciseName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightEt.setText(null);
                repsEt.setText(null);
                DatabaseReference clearReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Workouts").child("Date").child(dateStr).child(exerciseName);
                clearReference.removeValue();
            }
        });
    }

    protected void checkSet() {
        if (FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Workouts").child("Date").child(dateStr).child(exerciseName).child("Set " + set).child("weight") != null) {
            set++;
        }
    }

    protected boolean checkFields() {
        weightStr = weightEt.getText().toString();
        repsStr = repsEt.getText().toString();

        if (TextUtils.isEmpty(weightStr)) {
            Toast.makeText(AddExerciseActivity.this, R.string.please_enter_weight, Toast.LENGTH_LONG).show();
            return false;
        }
        if (TextUtils.isEmpty(repsStr)) {
            Toast.makeText(AddExerciseActivity.this, R.string.enter_reps, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference setDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Workouts").child("Date").child(dateStr).child(exerciseName);
        FirebaseRecyclerAdapter<Exercise, AddExerciseViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Exercise, AddExerciseViewHolder>(
                Exercise.class,
                R.layout.history_recyclerview_item,
                AddExerciseActivity.AddExerciseViewHolder.class,
                setDatabaseReference
        ) {
            @Override
            protected void populateViewHolder(AddExerciseViewHolder viewHolder, Exercise model, int position) {
                viewHolder.setSet(model.getSet());
                viewHolder.setWeight(model.getweight());
                viewHolder.setReps(model.getreps());
//                set++;
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class AddExerciseViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public AddExerciseViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setSet(int set) {
            TextView trackDate = (TextView) mView.findViewById(R.id.historyItem_date);
            trackDate.setText(String.valueOf(set));
        }

        public void setWeight(int weight) {
            TextView trackWeight = (TextView) mView.findViewById(R.id.historyItem_weightTv);
            trackWeight.setText(String.valueOf(weight));
        }

        public void setReps(int reps) {
            TextView trackFat = (TextView) mView.findViewById(R.id.historyItem_fatTv);
            trackFat.setText(String.valueOf(reps));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        passIntentToParent();
    }

    protected void passIntentToParent() {
        Intent intent = new Intent(AddExerciseActivity.this, IndividualActivity.class);
        intent.putExtra(ADD_EXERCISE_BUNDLE, exerciseBundle);
        startActivity(intent);
    }
}