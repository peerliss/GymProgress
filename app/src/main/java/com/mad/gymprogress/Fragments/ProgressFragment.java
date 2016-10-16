package com.mad.gymprogress.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.AxisValueFormatter;
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
        entries.add(new BarEntry(4f, 60));
        entries.add(new BarEntry(5f, 61));
        entries.add(new BarEntry(6f, 62));
        entries.add(new BarEntry(7f, 63));

        BarDataSet dataset = new BarDataSet(entries, "Weight");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("10/10/16");
        labels.add("11/10/16");
        labels.add("12/10/16");
        labels.add("13/10/16");

        BarData barData = new BarData(dataset);

        weightBarChart.setData(barData);
        weightBarChart.setDescription("History");
        weightBarChart.setDescriptionTextSize(20f);


        /*Legend legend = weightBarChart.getLegend();
        legend.setTextSize(15f);
        legend.setXEntrySpace(5f);
        legend.setYEntrySpace(5f);
        legend.setTextColor(R.color.black);*/

        weightBarChart.invalidate();

        return progressView;
    }

}