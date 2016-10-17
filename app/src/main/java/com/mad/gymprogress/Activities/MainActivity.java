/**
 * Activity containing a NavigationDrawer which displays different fragments based on the MenuItem selected
 */

package com.mad.gymprogress.Activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.mad.gymprogress.Fragments.CategoriesFragment;
import com.mad.gymprogress.Fragments.WorkoutsFragment;
import com.mad.gymprogress.R;
import com.mad.gymprogress.Fragments.TrackFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String EXERCISE_STRING = "Exercise_String";
    public static final String EXERCISE_BUNDLE = "Exercise_Bundle";
    android.support.v4.app.FragmentManager mFragmentManager;
    android.support.v4.app.FragmentTransaction mFragmentTransaction;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set view and toolbar
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Inflate initial fragment to be viewed based on intents received
        if (getIntent().getStringExtra(AddExerciseActivity.ADD_EXERCISE_DONE) != null) {
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.containerView, new WorkoutsFragment()).commit();
        } else {
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.containerView, new CategoriesFragment()).commit();
        }

        // Setup firebase with current instance
        mAuth = FirebaseAuth.getInstance();

        // Setup listener for FirebaseAuth, if user is signed out they are send to the LoginActivity
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            }
        };

        // Set up DrawerLayout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Set up DrawerToggle for the toolbar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Set up NavigationView
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Close drawer if open
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    /**
     * Inflate menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Handle MenuItem clicks here
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_logout:
                mAuth.signOut();
            case R.id.action_addExercise:
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.containerView, new CategoriesFragment()).commit();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Handle navigation view item clicks
     * Display fragment according to MenuItem selected
     *
     * @param item
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_today) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerView, new WorkoutsFragment()).commit();
        } else if (id == R.id.nav_addExercise) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerView, new CategoriesFragment()).commit();
        } else if (id == R.id.nav_track) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerView, new TrackFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Start IndividualActivity with intent passing the category selected
     *
     * @param view
     */
    public void shouldersOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, IndividualActivity.class);
        intent.putExtra(EXERCISE_STRING, "Shoulders");
        startActivity(intent);
    }

    /**
     * Start IndividualActivity with intent passing the category selected
     *
     * @param view
     */
    public void bicepsOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, IndividualActivity.class);
        intent.putExtra(EXERCISE_STRING, "Biceps");
        startActivity(intent);
    }

    /**
     * Start IndividualActivity with intent passing the category selected
     *
     * @param view
     */
    public void tricepsOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, IndividualActivity.class);
        intent.putExtra(EXERCISE_STRING, "Triceps");
        startActivity(intent);
    }

    /**
     * Start IndividualActivity with intent passing the category selected
     *
     * @param view
     */
    public void chestOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, IndividualActivity.class);
        intent.putExtra(EXERCISE_STRING, "Chest");
        startActivity(intent);
    }

    /**
     * Start IndividualActivity with intent passing the category selected
     *
     * @param view
     */
    public void backOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, IndividualActivity.class);
        intent.putExtra(EXERCISE_STRING, "Back");
        startActivity(intent);
    }

    /**
     * Start IndividualActivity with intent passing the category selected
     *
     * @param view
     */
    public void absOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, IndividualActivity.class);
        intent.putExtra(EXERCISE_STRING, "Abs");
        startActivity(intent);
    }

    /**
     * Start IndividualActivity with intent passing the category selected
     *
     * @param view
     */
    public void legsOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, IndividualActivity.class);
        intent.putExtra(EXERCISE_STRING, "Legs");
        startActivity(intent);
    }

    /**
     * Start IndividualActivity with intent passing the category selected
     *
     * @param view
     */
    public void latsOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, IndividualActivity.class);
        intent.putExtra(EXERCISE_STRING, "Lats");
        startActivity(intent);
    }
}
