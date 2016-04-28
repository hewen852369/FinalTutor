package com.example.wen.tutorwithparse;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Random;

import com.example.wen.tutorwithparse.Adapters.TutorListAdapter;
import com.example.wen.tutorwithparse.Models.Tutor;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeActivity extends AppCompatActivity implements Serializable{

    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayList<ParseObject> ArrObj;
    ParseObject p;
    Tutor result;
    private ArrayList<Tutor> tutorList = new ArrayList<>();
    TextView advertisement;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String username = getIntent().getStringExtra("Username");
        TextView tv = (TextView)findViewById(R.id.TVusername);
        tv.setText(username);

        ImageButton btnCategory = (ImageButton) findViewById(R.id.categoriesButton);
        assert btnCategory != null;     //idk... Android studios told me to do it...

        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategories();
            }
        });

        advertisement = (TextView) findViewById(R.id.advertisement);




        ImageButton btnSearch = (ImageButton) findViewById(R.id.searchButton);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchClick(v);
            }
        });


        ParseQuery<ParseObject> query = new ParseQuery("TutorsSubjects");
        query.include("members");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> userList, ParseException e) {
                tutorList = new ArrayList<>();
                if (e == null) {
                    if (userList.size() > 0) {
                        ParseObject tutor;
                        ParseObject member;

                        for (int i = 0; i < userList.size(); i++) {
                            tutor = userList.get(i);
                            member = userList.get(i).getParseObject("members");

                            if(member.getBoolean("Upgrade") == true)
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
                        Random rand = new Random();
                        int random1 = rand.nextInt(tutorList.size());
                        result = tutorList.get(random1);

                        //TextView text = (TextView) findViewById(R.id.advertisement);
                        String string = "Check out " + result.getName() + "\n" + "For " + result.getSubject();
                        advertisement.setText(string);
                        advertisement.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String username = getIntent().getStringExtra("Username");
                                Intent i = new Intent(HomeActivity.this, TutorDetailsActivity.class);
                                i.putExtra("Username", username);
                                i.putExtra("Tutor", result);
                                startActivity(i);
                            }
                        });



                    }

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }


            }

        });



    }


    public void goToCategories() {
        Intent categories = new Intent(this, CategoriesActivity.class);
        String username = getIntent().getStringExtra("Username");
        categories.putExtra("Username", username);
        startActivity(categories);
    }

    public void searchClick(View v)
    {
        //if(v.getId()==R.id.action_settings)
        {
            String username = getIntent().getStringExtra("Username");
            Intent i = new Intent(HomeActivity.this,SearchActivity.class);
            i.putExtra("Username", username);
            startActivity(i);
        }
    }


    public void goToDetails(Tutor tutor) {
        Intent details = new Intent(this, TutorDetailsActivity.class);
        details.putExtra("Tutor", tutor);
        startActivity(details);
    }




}


