package com.mad.gymprogress.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.mad.gymprogress.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProgressFragment extends Fragment {


    public ProgressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View progressView = inflater.inflate(R.layout.fragment_progress, container, false);

        BarChart weightBarChart = (BarChart) progressView.findViewById(R.id.progressBarChart);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(9f, 5));

        BarDataSet dataset = new BarDataSet(entries, "# of Calls");

        BarData barData = new BarData(dataset);
        weightBarChart.setData(barData);
        weightBarChart.invalidate();

        return progressView;
    }

}
