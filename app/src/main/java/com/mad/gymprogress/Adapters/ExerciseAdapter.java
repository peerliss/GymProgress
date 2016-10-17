/**
 * Adapter for RecyclerView in IndividualActivity
 */

package com.mad.gymprogress.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mad.gymprogress.Activities.AddExerciseActivity;
import com.mad.gymprogress.Model.Exercise;
import com.mad.gymprogress.R;

import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {

    private ArrayList<Exercise> exerciseArrayList;
    private Context context;

    /**
     * ExerciseAdapter constructor
     *
     * @param context
     * @param exercises
     */
    public ExerciseAdapter(Context context, ArrayList<Exercise> exercises) {
        this.context = context;
        this.exerciseArrayList = exercises;
    }

    /**
     * Set RecyclerView item layout to view
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.individual_recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Bind TextView with Exercise Name
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Exercise exercise = exerciseArrayList.get(position);
        holder.exerciseListTv.setText(exercise.getname());
    }

    /**
     * Return exerciseArrayList size
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return exerciseArrayList.size();
    }

    /**
     * RecyclerView.ViewHolder class to add Exercise to RecyclerView item
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public static final String EXERCISE_BUNDLE = "Exercise_Bundle";
        public static final String EXERCISE_CATEGORY = "Exercise_Category";
        public static final String EXERCISE_NAME = "Exercise_Name";
        public static final String EXERCISE_WEIGHT = "Exercise_Weight";
        public static final String EXERCISE_REPS = "Exercise_Reps";
        private TextView exerciseListTv;
        private LinearLayout linearLayout;

        /**
         * Initialize fields and set onClickListener for RecyclerView item's layout
         *
         * @param itemView
         */
        public ViewHolder(View itemView) {
            super(itemView);
            exerciseListTv = (TextView) itemView.findViewById(R.id.exerciseListTv);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.exerciseItemLayout);
            linearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            // Get exercise based on position in RecyclerView
            Exercise exercise = exerciseArrayList.get(position);
            String exerciseCategory = exercise.getcategory();
            String exerciseName = exercise.getname();
            int exerciseWeight = exercise.getweight();
            int exerciseReps = exercise.getreps();

            // Start activity AddExerciseActivity with intent including exercise details added in a bundle
            Intent intent = new Intent(context, AddExerciseActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString(EXERCISE_CATEGORY, exerciseCategory);
            bundle.putString(EXERCISE_NAME, exerciseName);
            bundle.putInt(EXERCISE_WEIGHT, exerciseWeight);
            bundle.putInt(EXERCISE_REPS, exerciseReps);

            intent.putExtra(EXERCISE_BUNDLE, bundle);
            context.startActivity(intent);
        }
    }
}
