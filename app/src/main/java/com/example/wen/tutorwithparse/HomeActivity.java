package com.example.wen.tutorwithparse;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Random;


public class HomeActivity extends AppCompatActivity {

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

        ImageButton btnSearch = (ImageButton) findViewById(R.id.searchButton);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchClick(v);
            }
        });

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Tutors");
    }

    public void goToCategories() {
        Intent categories = new Intent(this, CategoriesActivity.class);
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
   
}
