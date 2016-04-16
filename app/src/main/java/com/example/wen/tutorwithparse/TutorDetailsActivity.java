package com.example.wen.tutorwithparse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wen.tutorwithparse.Models.Tutor;

import java.io.Serializable;

public class TutorDetailsActivity extends AppCompatActivity implements Serializable {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_details);

        Tutor tutor = (Tutor) getIntent().getSerializableExtra("Tutor");

        TextView tvName = (TextView) findViewById(R.id.TVname);
        TextView tvSubject = (TextView) findViewById(R.id.TVsubject);
        TextView tvNumStudent = (TextView) findViewById(R.id.TVnumStudents);
        TextView tvBio = (TextView) findViewById(R.id.tv_bio);

        ImageView andyTheAndroid = (ImageView) findViewById(R.id.tutor_pic);

        tvName.setText(tutor.getName());
        tvSubject.setText(tutor.getSubject());
        tvNumStudent.setText(String.valueOf(tutor.getNumOfStudents()));
        tvBio.setText(tutor.getMessage());

        andyTheAndroid.setImageResource(R.drawable.ic_person_black_48dp);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tutor_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}