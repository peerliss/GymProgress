package com.mad.gymprogress;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mad.gymprogress.Model.Track;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseReference databaseReference;
    private String uid;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View historyView = inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = (RecyclerView) historyView.findViewById(R.id.historyRecyclerView);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Track");

        return historyView;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Track, HistoryViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Track, HistoryViewHolder>(
                Track.class,
                R.layout.history_recyclerview_item,
                HistoryViewHolder.class,
                databaseReference
        ) {
            @Override
            protected void populateViewHolder(HistoryViewHolder viewHolder, Track model, int position) {
                viewHolder.setDate(model.getDate());
                viewHolder.setWeight(model.getWeight());
                viewHolder.setFat(model.getBodyFat());
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setDate(String date) {
            TextView trackDate = (TextView) mView.findViewById(R.id.historyItem_date);
            trackDate.setText(date);
        }

        public void setWeight(int weight) {
            TextView trackWeight = (TextView) mView.findViewById(R.id.historyItem_weightTv);
            trackWeight.setText(weight);
        }

        public void setFat(int fat) {
            TextView trackFat = (TextView) mView.findViewById(R.id.historyItem_fatTv);
            trackFat.setText(fat);
        }
    }
}
