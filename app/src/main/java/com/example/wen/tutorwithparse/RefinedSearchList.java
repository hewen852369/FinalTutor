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

import com.example.wen.tutorwithparse.Adapters.SearchTutorAdapter;
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
        ParseQuery<ParseObject> query = new ParseQuery<>("TutorsSubjects");   //TutorsSubjects table works for now
        query.include("members");       //pointer to Member's table

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

                            temp = member.getString("Name");
                            type = tutor.getString("Subject");

                            Log.d("name:", temp + " = " + name);
                            Log.d("type:", type + " = " +subject);

                            if ("".equals(subject) && "".equals(name)) {
                                tutorList.add(new Tutor(member.getString("Name"),
                                        tutor.getString("Subject"),
                                        tutor.getString("Subcategory"),
                                        tutor.getInt("numStudents"),
                                        tutor.getString("Message"),
                                        member.getString("PhoneNumber"),
                                        member.getString("Email"),
                                        member.getString("Address"),
                                        tutor.getInt("Price")));
                            }
                            else if (temp.equals(name) && type.equals(subject)) {
                                tutorList.add(new Tutor(member.getString("Name"),
                                        tutor.getString("Subject"),
                                        tutor.getString("Subcategory"),
                                        tutor.getInt("numStudents"),
                                        tutor.getString("Message"),
                                        member.getString("PhoneNumber"),
                                        member.getString("Email"),
                                        member.getString("Address"),
                                        tutor.getInt("Price")));
                            }
                            else if("".equals(subject) && temp.equals(name)){
                                tutorList.add(new Tutor(member.getString("Name"),
                                        tutor.getString("Subject"),
                                        tutor.getString("Subcategory"),
                                        tutor.getInt("numStudents"),
                                        tutor.getString("Message"),
                                        member.getString("PhoneNumber"),
                                        member.getString("Email"),
                                        member.getString("Address"),
                                        tutor.getInt("Price")));
                            }
                            else if ("".equals(name) && type.equals(subject)){
                                tutorList.add(new Tutor(member.getString("Name"),
                                        tutor.getString("Subject"),
                                        tutor.getString("Subcategory"),
                                        tutor.getInt("numStudents"),
                                        tutor.getString("Message"),
                                        member.getString("PhoneNumber"),
                                        member.getString("Email"),
                                        member.getString("Address"),
                                        tutor.getInt("Price")));
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
        String username = getIntent().getStringExtra("Username");
        Intent details = new Intent(this, TutorDetailsActivity.class);
        details.putExtra("Username",username);
        details.putExtra("Tutor", tutor);
        startActivity(details);
    }



    public void refineSearchClick(View v)
    {
        String username = getIntent().getStringExtra("Username");
        Intent i = new Intent(RefinedSearchList.this,RefineSearch.class);
        i.putExtra("Username",username);
        finish();
        startActivity(i);
    }

}
