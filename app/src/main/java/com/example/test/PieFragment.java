package com.example.test;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieFragment extends Fragment {
    PieChart pieChart;
    PieData pieData;
    PieDataSet pieDataSet;
    ArrayList pieEntries;
    ArrayList PieEntryLabels;
    int []res;

    private void getEntries(int pass,final  int [] rs)
    {
        pieEntries = new ArrayList<>();
        // pieEntries.add(new PieEntry(pass, "pass"));
        pieEntries.add(new PieEntry(rs[1], "fail"));
        pieEntries.add(new PieEntry(rs[2], "error"));
        pieEntries.add(new PieEntry(rs[3], "skipped"));
        pieEntries.add(new PieEntry(rs[4], "notrun"));

    }
    public PieFragment(int []res) {
        // Required empty public constructor
        this.res=res;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.tab1, container, false);
        pieChart = v.findViewById(R.id.pieChart);
        int pass=res[0]-(res[1]+res[2]+res[3]+res[4]);
        getEntries(pass,res);
        pieDataSet = new PieDataSet(pieEntries,"Test-Cases");
        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        pieDataSet.setSliceSpace(10f);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(10f);
        pieChart.setCenterText("pass "+String.valueOf(pass));
        // pieDataSet.setSliceSpace(15f);
        Legend l = pieChart.getLegend();
        l.setEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        //l.setDrawInside(true);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        pieChart.setEntryLabelColor(Color.BLUE);
        //chart.setEntryLabelTypeface(tfRegular);
        pieChart.setEntryLabelTextSize(12f);

        return v;
    }

}
