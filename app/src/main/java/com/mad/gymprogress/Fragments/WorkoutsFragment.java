package com.mad.gymprogress.Fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mad.gymprogress.Activities.MainActivity;
import com.mad.gymprogress.R;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class WorkoutsFragment extends Fragment {

    private static final String WORKOUTS_FRAGMENT = "Workouts_Fragment";
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private String uid;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String dateStr;
    private Button selectDateBtn;
    private int year_x, month_x, day_x;
    static final int DIALOG_ID = 0;
    Calendar calendar = Calendar.getInstance();

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

        // Get current user id from current Firebase instance
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        uid = user.getUid();

        /*try {
            // Get databaseReference based on date
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Workouts").child(dateStr).child("Exercise").child("Set");

            // Get data from databaseReference as a map
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {
                        Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
//                        String exerciseName = (String) map.get("name");
//                        selectDateBtn.setText(exerciseName);
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
        }*/
        return workoutsView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Initialize fields
        selectDateBtn = (Button) getView().findViewById(R.id.workoutsDateBtn);
        String dateSelectedStr = "Date Selected: " + dateStr;

        selectDateBtn.setText(dateSelectedStr);
        selectDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(WORKOUTS_FRAGMENT, "selectDateBtnOnClick");
                // Start DatePickerDialog
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    new DatePickerDialog(getContext(), dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });
    }

    // Set OnDateSetListener for DatePickerDialog
    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dateStr = "Date Selected: " + dayOfMonth + "-" + (month + 1) + "-" + year;
            selectDateBtn.setText(dateStr);
        }
    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
}
