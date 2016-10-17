package com.mad.gymprogress.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mad.gymprogress.R;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class EnterWeightFragment extends Fragment {

    private EditText weightEt;
    private EditText fatEt;
    private Button saveBtn;
    private Date currentDate;
    private TextView weightTv;
    private String dateStr;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private String uid;
    private String weightStr;
    private String fatStr;
    private int weightInt;
    private int fatInt;

    public EnterWeightFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View weightView = inflater.inflate(R.layout.fragment_enter_weight, container, false);

        // Initialize fields
        weightEt = (EditText) weightView.findViewById(R.id.track_weightEt);
        fatEt = (EditText) weightView.findViewById(R.id.track_fatEt);
        saveBtn = (Button) weightView.findViewById(R.id.weight_saveBtn);

        // Get current date in format dd/MM/yy
        dateStr = (String) DateFormat.format("dd/MM/yy", new java.util.Date());

        // Get current user id from current Firebase instance
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        uid = user.getUid();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightStr = weightEt.getText().toString();
                fatStr = fatEt.getText().toString();

                // Check if EditText fields are empty
                if (TextUtils.isEmpty(weightStr) & TextUtils.isEmpty(fatStr)) {
                    Toast.makeText(getContext(), R.string.please_enter_weight_or_fat, Toast.LENGTH_LONG).show();
                } else {
                    saveDetails();
                }
            }
        });
        return weightView;
    }

    /**
     * Get databaseReference based on UserID
     * Add data entered by user, along with current date to Firebase Database
     */
    private void saveDetails() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Track");
        DatabaseReference newTrack = databaseReference.push();
        newTrack.child("date").setValue(dateStr);
        newTrack.child("weight").setValue(weightStr);
        newTrack.child("fat").setValue(fatStr);

        Toast.makeText(getContext(), R.string.successfully_added, Toast.LENGTH_SHORT).show();
    }
}
