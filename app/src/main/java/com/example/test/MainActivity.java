package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;


import com.google.android.material.tabs.TabLayout;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class MainActivity extends AppCompatActivity {
//    PieChart pieChart;
//    PieData pieData;
//    PieDataSet pieDataSet;
//    ArrayList pieEntries;
//    ArrayList PieEntryLabels;
    TabLayout tabLayout;
    ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Pie-chart"));
        tabLayout.addTab(tabLayout.newTab().setText("Line-graph"));

               try{
            //ListView lv = (ListView) findViewById(R.id.user_list);
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            final int []res=new int[5];
            final ArrayList<Integer> time=new ArrayList<Integer>();
            final ArrayList<String> cname=new ArrayList<String>();
          //  final int total=0,pass=0,fail=0,rerun,skipped,error;
            DefaultHandler handler = new DefaultHandler(){
                String currentValue = "";
                boolean currentElement = false;
                int total=0,pass=0,fail=0,notrun=0,skipped=0,error=0;;
                //String cname="";
                public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
                    currentElement = true;
                    currentValue = "";
                    if(localName.equals("Component")){
                        total+=Integer.parseInt(attributes.getValue("nbTests"));
                        fail+=Integer.parseInt(attributes.getValue("nbFailures"));
                        error+=Integer.parseInt(attributes.getValue("nbErrors"));
                        skipped+=Integer.parseInt(attributes.getValue("nbSkipped"));
                        notrun+=Integer.parseInt(attributes.getValue("nbNotRun"));
                        res[0]=total;
                        res[1]=fail;
                        res[2]=error;
                        res[3]=skipped;
                        res[4]=notrun;
                        time.add(Integer.parseInt(attributes.getValue("ElapsedTime")));
                        cname.add(attributes.getValue("Name"));




                    }
                }
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    currentElement = false;

                }
                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if (currentElement) {
                        currentValue = currentValue +  new String(ch, start, length);
                    }
                }
            };
            InputStream istream = getAssets().open("test.xml");
            parser.parse(istream,handler);
            System.out.println("Array........ "+ Arrays.toString(res));

                   final MyAdapter adapter = new MyAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount(),res,time,cname);
                   viewPager.setAdapter(adapter);

                   viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

                   tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                       @Override
                       public void onTabSelected(TabLayout.Tab tab) {
                           viewPager.setCurrentItem(tab.getPosition());
                       }

                       @Override
                       public void onTabUnselected(TabLayout.Tab tab) {

                       }

                       @Override
                       public void onTabReselected(TabLayout.Tab tab) {

                       }
                   });




                   // l.setExtra(ColorTemplate.JOYFUL_COLORS, new String[] { "fail", "error", "skipped","notrun"});
    }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
