/**
 * Activity to display exercise selected by user and add their exercise details
 */

package com.mad.gymprogress.Activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
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
import com.mad.gymprogress.Fragments.WorkoutsFragment;
import com.mad.gymprogress.Model.Exercise;
import com.mad.gymprogress.Model.Track;
import com.mad.gymprogress.R;

public class AddExerciseActivity extends AppCompatActivity {

    public static final String ADD_EXERCISE_BUNDLE = "Add_Exercise_Bundle";
    public static final String ADD_EXERCISE_DONE = "Add_Exercise_Done";
    private static final String ADD_EXERCISE_ACTIVITY = "Add_Exercise_Activity";
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
        // Set view and toolbar
        setContentView(R.layout.activity_add_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get current user id from current Firebase instance
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        uid = user.getUid();

        // Initialize RecyclerView and set its LayoutManager
        recyclerView = (RecyclerView) findViewById(R.id.addExerciseRecyclerView);
        layoutManager = new LinearLayoutManager(AddExerciseActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        // Initialize fields in view
        TextView exerciseTitle = (TextView) findViewById(R.id.exerciseTitle);
        weightEt = (EditText) findViewById(R.id.weightEt);
        repsEt = (EditText) findViewById(R.id.repsEt);
        Button addSetBtn = (Button) findViewById(R.id.addSetBtn);
        Button clearBtn = (Button) findViewById(R.id.clearBtn);
        final Button doneBtn = (Button) findViewById(R.id.addExerciseDoneBtn);

        // Get exercise name from exercise list
        exerciseBundle = getIntent().getBundleExtra(ExerciseAdapter.ViewHolder.EXERCISE_BUNDLE);
        exerciseName = exerciseBundle.getString(ExerciseAdapter.ViewHolder.EXERCISE_NAME);

        exerciseTitle.setText(exerciseName);

        // Get current date in the format of dd-MM-yy
        dateStr = (String) DateFormat.format("dd-MM-yy", new java.util.Date());

        addSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check to identify if required fields are empty
                if (checkFields()) {
                    // Check value of set
                    checkSet();
                    if (set > 5) {
                        Toast.makeText(AddExerciseActivity.this, R.string.cannot_add_sets, Toast.LENGTH_LONG).show();
                        return;
                    }

                    // Set databaseReference to a new reference based on the exercise selected, date and set
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Workouts").child(dateStr).child(exerciseName).child("Set " + set);

                    // Add set to database
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

        // Start MainActitvity, unique intent is passed to identify which activity started MainActivity
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ADD_EXERCISE_ACTIVITY, "doneBtn onClick");
                Intent doneIntent = new Intent(AddExerciseActivity.this, MainActivity.class);
                doneIntent.putExtra(ADD_EXERCISE_DONE, "doneBtn");
                startActivity(doneIntent);
            }
        });

        // Set EditText fields to null and remove all data entered by user in the current session
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearWorkout();
            }
        });
    }

    protected void clearWorkout() {
        weightEt.setText(null);
        repsEt.setText(null);
        DatabaseReference clearReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Workouts").child(dateStr).child(exerciseName);
        clearReference.removeValue();
    }

    /**
     * Check database to see if set already exists and is to be increased
     */
    protected void checkSet() {
        if (FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Workouts").child(dateStr).child(exerciseName).child("Set " + set).child("weight") != null) {
            set++;
        }
    }

    /**
     * Check to see if EditText fields are empty
     *
     * @return boolean according to field values
     */
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
        // Get database reference according to current user, date and exercise name
        DatabaseReference setDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Workouts").child(dateStr).child(exerciseName);

        // Set up FirebaseRecyclerAdapter
        FirebaseRecyclerAdapter<Exercise, AddExerciseViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Exercise, AddExerciseViewHolder>(
                Exercise.class,
                R.layout.history_recyclerview_item,
                AddExerciseActivity.AddExerciseViewHolder.class,
                setDatabaseReference
        ) {
            /**
             * Populate RecyclerView items
             * @param viewHolder
             * @param model
             * @param position
             */
            @Override
            protected void populateViewHolder(AddExerciseViewHolder viewHolder, Exercise model, int position) {
                viewHolder.setSet(model.getSet());
                viewHolder.setWeight(model.getweight());
                viewHolder.setReps(model.getreps());
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    /**
     * Static inner RecyclerView.ViewHolder class to add Exercise to RecyclerView item
     */
    public static class AddExerciseViewHolder extends RecyclerView.ViewHolder {

        View mView;

        /**
         * Set view
         *
         * @param itemView
         */
        public AddExerciseViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        /**
         * Populate RecyclerView items TextView
         *
         * @param set
         */
        public void setSet(int set) {
            TextView trackDate = (TextView) mView.findViewById(R.id.historyItem_date);
            trackDate.setText(String.valueOf(set));
        }

        /**
         * Populate RecyclerView items TextView
         *
         * @param weight
         */
        public void setWeight(int weight) {
            TextView trackWeight = (TextView) mView.findViewById(R.id.historyItem_weightTv);
            trackWeight.setText(String.valueOf(weight));
        }

        /**
         * Populate RecyclerView items TextView
         *
         * @param reps
         */
        public void setReps(int reps) {
            TextView trackFat = (TextView) mView.findViewById(R.id.historyItem_fatTv);
            trackFat.setText(String.valueOf(reps));
        }
    }

    /**
     * Set Home as up button to have the functionality of onBackPressed
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                clearWorkout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Return to parent activity
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        clearWorkout();
        passIntentToParent();
    }

    /**
     * Pass exercise details in intent to parent
     */
    protected void passIntentToParent() {
        Intent intent = new Intent(AddExerciseActivity.this, IndividualActivity.class);
        intent.putExtra(ADD_EXERCISE_BUNDLE, exerciseBundle);
        startActivity(intent);
    }
}