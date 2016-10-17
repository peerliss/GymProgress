package com.mad.gymprogress.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mad.gymprogress.R;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutsFragment extends Fragment {

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private String uid;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String dateStr;
    private Button selectDateBtn;

    public WorkoutsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View workoutsView = inflater.inflate(R.layout.fragment_workouts, container, false);

        // Get current date in format dd/MM/yy
        dateStr = (String) DateFormat.format("dd-MM-yy", new java.util.Date());

        // Initialize fields
        selectDateBtn = (Button) workoutsView.findViewById(R.id.workoutsDateBtn);

        // Get current user id from current Firebase instance
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        uid = user.getUid();

        try {
            // Get databaseReference based on date
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Workouts").child(dateStr).child("Exercise").child("Set");

            // Get data from databaseReference as a map
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {
                        Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                        String exerciseName = (String) map.get("name");
                        selectDateBtn.setText(exerciseName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workoutsView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
}
