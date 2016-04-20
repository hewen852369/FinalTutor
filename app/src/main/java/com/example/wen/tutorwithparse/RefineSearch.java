package com.example.wen.tutorwithparse;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RefineSearch extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    String searchSubject = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refine_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    public void refineClick(View v){
        EditText a = (EditText)findViewById(R.id.txtsearch);
        String searchName = a.getText().toString();
        final Intent i = new Intent(RefineSearch.this,RefinedSearchList.class);
        i.putExtra("Name", searchName);
        i.putExtra("Subject", searchSubject);
        finish();
        startActivity(i);
    }

    public void searchMath(View v){
        searchSubject = "Math";
        Log.d("subject: ", searchSubject);
    }

    public void searchScience(View v){
        searchSubject = "Science";
        Log.d("subject: ", searchSubject);
    }

    public void searchSocialStudies(View v){
        searchSubject = "SocialStudies";
        Log.d("subject: ", searchSubject);
    }

    public void searchEnglish(View v){
        searchSubject = "English";
        Log.d("subject: ", searchSubject);
    }

    public void searchArt(View v){
        searchSubject = "Art";
        Log.d("subject: ", searchSubject);
    }

    public void searchBusiness(View v){
        searchSubject = "Business";
        Log.d("subject: ", searchSubject);
    }

    public void searchTechnology(View v){
        searchSubject = "Technology";
        Log.d("subject: ", searchSubject);
    }




}
