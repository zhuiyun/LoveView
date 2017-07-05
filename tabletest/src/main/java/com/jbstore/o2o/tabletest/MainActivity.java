package com.jbstore.o2o.tabletest;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TableView tableView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableView= (TableView) findViewById(R.id.tview);
        List<Point> points=new ArrayList<>();
        points.add(new Point(5,150));
        points.add(new Point(10,100));
        points.add(new Point(15,0));
        points.add(new Point(20,200));
        points.add(new Point(25,300));
        tableView.setData(points);
    }
}
