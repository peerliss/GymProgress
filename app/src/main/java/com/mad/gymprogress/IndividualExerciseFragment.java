package com.mad.gymprogress;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mad.gymprogress.Model.Exercise;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IndividualExerciseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class IndividualExerciseFragment extends Fragment {

    private static final String INDIVIDUAL_FRAGMENT = "Individual_Fragment";
    private ArrayList<Exercise> shouldersList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mExerciseAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private OnFragmentInteractionListener mListener;

    public IndividualExerciseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_individual_exercise, container, false);

//        mRecyclerView = (RecyclerView) getView().findViewById(R.id.individualRecyclerView);

//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);

//        mExerciseAdapter = new ExerciseAdapter(shouldersList);
//        mRecyclerView.setAdapter(mExerciseAdapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void populateShoulderList() {
        Log.d(INDIVIDUAL_FRAGMENT, "populateShoulderList");
        Exercise shoulders = new Exercise("Shoulders", "Side laterals to front raise", 0, 0);
        shouldersList.add(shoulders);

        shoulders = new Exercise("Shoulders", "Single arm linear jammer", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Clean and press", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Standing palm in one arm dumbbell press", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Clean and jerk", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "One arm kettlebell push press", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Push press", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Standing military press", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Standing palms in dumbbell press", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Seated barbell military press", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Power partials", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Reverse flyes", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Seated dumbbell press", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Standing dumbbell press", 0, 0);
        shouldersList.add(shoulders);
        shoulders = new Exercise("Shoulders", "Standing dumbbell straight arm front delt raise above head", 0, 0);
        shouldersList.add(shoulders);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
