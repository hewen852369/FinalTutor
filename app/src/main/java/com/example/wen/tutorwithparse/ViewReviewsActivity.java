package com.example.wen.tutorwithparse;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import android.content.Intent;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import com.example.wen.tutorwithparse.Adapters.ReviewAdapter;
import com.example.wen.tutorwithparse.Models.ReviewModel;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import android.widget.ListView;

import java.io.Serializable;

public class ViewReviewsActivity extends AppCompatActivity implements Serializable {
    String[] items;

    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayList<ParseObject> ArrObj;
    ParseObject p;

    private ArrayList<ReviewModel> reviewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reviews);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String tutorName = getIntent().getStringExtra("tutorName");
        TextView tv = (TextView)findViewById(R.id.tNametext);
        tv.setText(tutorName);

        ArrObj = new ArrayList<ParseObject>();
        initList();
    }

    public void initList(){
        String tutorPhone = getIntent().getStringExtra("tutorPhone");
        ParseQuery<ParseObject> query = new ParseQuery("Reviews");
        query.whereEqualTo("tutorPhone",tutorPhone);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> userList, ParseException e) {
                reviewList = new ArrayList<>();

                if (e == null) {
                    if (userList.size() > 0) {
                        ParseObject review;

                        for (int i = 0; i < userList.size(); i++) {
                            review = userList.get(i);
                            reviewList.add(new ReviewModel(review.getString("studentMemberID"),
                                    review.getString("Comment"),
                                    review.getInt("Stars")));
                        }
                        ListAdapter myAdapter = new ReviewAdapter(ViewReviewsActivity.this, reviewList);
                        ListView categoryListView = (ListView) findViewById(R.id.listView);
                        categoryListView.setAdapter(myAdapter);

                    }

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }


            }

        });


    }
}
