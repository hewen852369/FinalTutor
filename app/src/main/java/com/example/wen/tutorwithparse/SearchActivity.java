package com.example.wen.tutorwithparse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.wen.tutorwithparse.Adapters.SearchTutorAdapter;
import com.example.wen.tutorwithparse.Adapters.TutorListAdapter;
import com.example.wen.tutorwithparse.Models.Tutor;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import android.widget.ListView;


public class SearchActivity extends AppCompatActivity implements Serializable {
    String[] items;

    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayList<ParseObject> ArrObj;
    ParseObject p;


    private ArrayList<Tutor> tutorList;

    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tutor_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ArrObj = new ArrayList<ParseObject>();
        initList();

    }





    public void initList(){
        ParseQuery<ParseObject> query = new ParseQuery("TutorsSubjects");
        query.include("members");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> userList, ParseException e) {
                tutorList = new ArrayList<>();
                if (e == null) {
                    if (userList.size() > 0) {
                        String temp;
                        String type;
                        ParseObject tutor;
                        ParseObject member;
                        for (int i = 0; i < userList.size(); i++) {
                            tutor = userList.get(i);
                            member = userList.get(i).getParseObject("members");



                            tutorList.add(new Tutor(member.getString("Name"),
                                    tutor.getString("Subject"),
                                    tutor.getString("Subcategory"),
                                    tutor.getInt("numStudents"),
                                    tutor.getString("Message"),
                                    member.getString("PhoneNumber"),
                                    member.getString("Email"),
                                    member.getString("Address"),
                                    tutor.getInt("Price")
                                    ));


                        }
                        ListAdapter myAdapter = new TutorListAdapter(SearchActivity.this, tutorList);
                        ListView categoryListView = (ListView) findViewById(R.id.tutorListView1);
                        categoryListView.setAdapter(myAdapter);


                        categoryListView.setOnItemClickListener(
                                new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Tutor t = (Tutor) parent.getItemAtPosition(position);
                                        goToDetails(t);
                                    }
                                }
                        );
                    }

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }


            }

        });

        Log.d("initlist: ", "MemberID !");


    }

    public void goToDetails(Tutor tutor) {
        Intent details = new Intent(this, TutorDetailsActivity.class);
        details.putExtra("Tutor", tutor);
        startActivity(details);
    }

    public void refineSearchClick(View v)
    {
        finish();
        Intent i = new Intent(SearchActivity.this,RefineSearch.class);
        startActivity(i);
    }
    
}
