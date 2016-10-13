package com.mad.gymprogress;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by peerliss on 13/10/2016.
 */

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {

    private ArrayList<Exercise> exerciseArrayList;
    private Context context;

    public ExerciseAdapter(Context context, ArrayList<Exercise> exercises) {
        this.context = context;
        this.exerciseArrayList = exercises;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.individual_recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Exercise exercise = exerciseArrayList.get(position);
        holder.exerciseListTv.setText(exercise.getmName());
    }

    @Override
    public int getItemCount() {
        return exerciseArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView exerciseListTv;
        public ViewHolder(View itemView) {
            super(itemView);
            exerciseListTv = (TextView) itemView.findViewById(R.id.exerciseListTv);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
