package com.mad.gymprogress.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getStringExtra(AddExerciseActivity.ADD_EXERCISE_DONE) != null) {
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.containerView, new WorkoutsFragment()).commit();
        } else {
            // Inflate initial fragment to be viewed
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.containerView, new CategoriesFragment()).commit();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection switch/case statement
        switch (id) {
            case R.id.action_logout:
                mAuth.signOut();
            case R.id.action_addExercise:
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.containerView, new CategoriesFragment()).commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_today) {
            // Handle the today action
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

    public void shouldersOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, IndividualActivity.class);
        intent.putExtra(EXERCISE_STRING, "Shoulders");
        startActivity(intent);
    }

    public void bicepsOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, IndividualActivity.class);
        intent.putExtra(EXERCISE_STRING, "Biceps");
        startActivity(intent);
    }

    public void tricepsOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, IndividualActivity.class);
        intent.putExtra(EXERCISE_STRING, "Triceps");
        startActivity(intent);
    }

    public void chestOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, IndividualActivity.class);
        intent.putExtra(EXERCISE_STRING, "Chest");
        startActivity(intent);
    }

    public void backOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, IndividualActivity.class);
        intent.putExtra(EXERCISE_STRING, "Back");
        startActivity(intent);
    }

    public void absOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, IndividualActivity.class);
        intent.putExtra(EXERCISE_STRING, "Abs");
        startActivity(intent);
    }

    public void legsOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, IndividualActivity.class);
        intent.putExtra(EXERCISE_STRING, "Legs");
        startActivity(intent);
    }

    public void cardioOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, IndividualActivity.class);
        intent.putExtra(EXERCISE_STRING, "Cardio");
        startActivity(intent);
    }
}
