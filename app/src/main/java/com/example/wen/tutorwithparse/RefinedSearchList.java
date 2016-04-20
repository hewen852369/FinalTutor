package com.example.wen.tutorwithparse;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.wen.tutorwithparse.Adapters.TutorListAdapter;
import com.example.wen.tutorwithparse.Models.Tutor;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class RefinedSearchList extends AppCompatActivity {




    private ArrayList<Tutor> tutorList;
    ArrayList<String> listItems = new ArrayList<>();
    ArrayList<ParseObject> ArrObj;
    ParseObject p;
    String name;
    String subject;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tutor_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        ArrObj = new ArrayList<ParseObject>();
        name = getIntent().getStringExtra("Name");
        subject = getIntent().getStringExtra("Subject");
        Log.d("name from oncreate:" , "name");
        refineSearch();



    }





    public void refineSearch(){


        ParseQuery<ParseObject> query = new ParseQuery("Members");
        query.whereEqualTo("UserType", "tutor");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> userList, ParseException e) {
                tutorList = new ArrayList<>();
                if (e == null) {
                    if (userList.size() > 0) {
                        String temp;
                        String type;
                        for (int i = 0; i < userList.size(); i++) {
                            p = userList.get(i);
                            ArrObj.add(p);
                            Log.d("MemberFromParse", "MemberID:" + ArrObj.get(i).getString("MemberID"));
                            temp = ArrObj.get(i).getString("Name");
                            type = ArrObj.get(i).getString("Subject");
                            Log.d("subject", subject + " = " + type);
                            Log.d("name", name + " = " + temp);
                            Log.d("text", temp);
                            if ("".equals(name) && type.equals(subject)) {
                                tutorList.add(new Tutor(ArrObj.get(i).getString("Name"), ArrObj.get(i).getString("Subject"), 2, "Hey!", ArrObj.get(i).getString("PhoneNumber"), ArrObj.get(i).getString("Name") + "@gmail.com"));
                            }
                            else if (temp.equals(name) && type.equals(subject)) {
                                tutorList.add(new Tutor(ArrObj.get(i).getString("Name"), ArrObj.get(i).getString("Subject"), 2, "Hey!", ArrObj.get(i).getString("PhoneNumber"), ArrObj.get(i).getString("Name") + "@gmail.com"));
                            }
                            else if("".equals(subject) && temp.equals(name)){
                                tutorList.add(new Tutor(ArrObj.get(i).getString("Name"), ArrObj.get(i).getString("Subject"), 2, "Hey!", ArrObj.get(i).getString("PhoneNumber"), ArrObj.get(i).getString("Name") + "@gmail.com"));
                            }



                        }
                        ListAdapter myAdapter = new TutorListAdapter(RefinedSearchList.this, tutorList);
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

    }


    public void goToDetails(Tutor tutor) {
        Intent details = new Intent(this, TutorDetailsActivity.class);
        details.putExtra("Tutor", tutor);
        startActivity(details);
    }



    public void refineSearchClick(View v)
    {
        Intent i = new Intent(RefinedSearchList.this,RefineSearch.class);
        finish();
        startActivity(i);
    }

}
