package com.example.test;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class LineFragment extends Fragment {

    ArrayList<Integer> time;
    ArrayList<String> cname;
    LineChart mChart;
    public LineFragment(ArrayList<Integer> time,
            ArrayList<String> cname) {
        // Required empty public constructor

        this.time=time;
        this.cname=cname;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v2=inflater.inflate(R.layout.tab2, container, false);
        mChart = v2.findViewById(R.id.chart);
        mChart.setTouchEnabled(true);
        mChart.setPinchZoom(true);
        ArrayList<Entry> entries = new ArrayList<>();
        for (int counter = 0; counter < time.size(); counter++)
        {
            int val=time.get(counter)/(60*60*24);
            entries.add(new Entry(counter,val));
        }
        LineDataSet dataSet = new LineDataSet(entries, "Customized values");

        dataSet.setDrawValues(false);
        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(dataSet);
        dataSet.setColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        dataSet.setValueTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        XAxis xAxis = mChart.getXAxis();
        // Set the xAxis position to bottom. Default is top
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //Customizing x axis value

        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return cname.get((int)value);
            }
        };
       // xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        //xAxis.setValueFormatter(formatter);
        YAxis yAxisRight = mChart.getAxisRight();
        yAxisRight.setEnabled(false);
        mChart.setHighlightPerTapEnabled(true);

        //***
        // Controlling left side of y axis
        YAxis yAxisLeft = mChart.getAxisLeft();
        yAxisLeft.setGranularity(1f);
        Legend l= mChart.getLegend();
        //l.setEnabled(true);
        // declaration and initialise String Array
        String str[] = new String[time.size()];

        // ArrayList to Array Conversion
        for (int j = 0; j < time.size(); j++) {

            // Assign each value to String array
            str[j] = cname.get(j);
        }
       // l.set
        //l.setCustom(ColorTemplate.VORDIPLOM_COLORS, str);

        // Setting Data
        LineData data = new LineData(dataSet);
        mChart.setData(data);
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
               String y= String.valueOf(e.getY());
               String x=cname.get(Math.round(e.getX()));
                Toast.makeText(getActivity(),"Component "+x+" hours "+y,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });


        return v2;
    }

}
