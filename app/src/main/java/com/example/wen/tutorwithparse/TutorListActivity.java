package com.example.wen.tutorwithparse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wen.tutorwithparse.Adapters.TutorListAdapter;
import com.example.wen.tutorwithparse.Models.Tutor;

import java.io.Serializable;
import java.util.ArrayList;

public class TutorListActivity extends AppCompatActivity implements Serializable {

    private Bundle extras;
    private String subCategoryName;
    private ArrayList<Tutor> tutorList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_list);

        tutorList = new ArrayList<>();

        extras = getIntent().getExtras();
        subCategoryName = getIntent().getExtras().getString("Subcategory");

        getTutorNames(subCategoryName);

        ListAdapter myAdapter = new TutorListAdapter(this, tutorList);

        ListView categoryListView = (ListView) findViewById(R.id.tutorListView);
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

    public void goToDetails(Tutor tutor) {
        Intent details = new Intent(this, TutorDetailsActivity.class);
        details.putExtra("Tutor", tutor);
        startActivity(details);
    }

    //TODO: Get tutors from Parse
    private void getTutorNames(String name) {
        switch (name) {
            case "Math":
                tutorList.add(new Tutor("Jeff", "Math", 2, "Hey!", "1234567890", "jeff@gmail.com"));
                tutorList.add(new Tutor("Jerry", "Math", 2, "Suh dude", "1234567890", "Jerry@gmail.com"));
                tutorList.add(new Tutor("Chris", "Math", 1, "lolwut?", "1234567890", "chris@gmail.com"));
                tutorList.add(new Tutor("David", "Math", 2, "What up boi?", "1234567890", "chris@gmail.com"));
                tutorList.add(new Tutor("Zoe", "Math", 0, "WHATS 9 + 10?                21?", "1234567890", "chris@gmail.com"));
                tutorList.add(new Tutor("John", "Math", 1, "I forgot how to breathe                  ._.", "1234567890", "chris@gmail.com"));
                tutorList.add(new Tutor("Steve", "Math", 2, "What would you do-oo-oo for a Klondike bar?", "1234567890", "chris@gmail.com"));
                tutorList.add(new Tutor("Rachel", "Math", 4, "Ba-dum-tissssss", "1234567890", "chris@gmail.com"));
                break;
            case "Science":
                tutorList.add(new Tutor("Talia", "Science", 1, "Word!", "1234567890", "chris@gmail.com"));
                tutorList.add(new Tutor("Sarah", "Science", 1, "DAAAAANNNNNNGGGGG!!!!!!!!", "1234567890", "chris@gmail.com"));
                break;
            case "Social Studies":
                tutorList.add(new Tutor("Charles", "Social Studies", 0, "Cool", "1234567890", "chris@gmail.com"));
                break;
            case "English/Language Arts":
                tutorList.add(new Tutor("Quincy", "English", 0, "BRUHHHH", "1234567890", "chris@gmail.com"));
                break;
            case "Fine Arts":
                tutorList.add(new Tutor("Jane", "Fine Arts", 2, "Think out side the box", "1234567890", "chris@gmail.com"));
                tutorList.add(new Tutor("Rachel", "Fine Arts", 0, "LOL", "1234567890", "chris@gmail.com"));
                break;
            case "Business Applications":
                tutorList.add(new Tutor("Stanley", "Business Applications", 3, "plssss", "1234567890", "chris@gmail.com"));
                tutorList.add(new Tutor("Richard", "Business Applications", 3, "Can I has cheezburger", "1234567890", "chris@gmail.com"));
                tutorList.add(new Tutor("Steve", "Business Applications", 3, "Best App EVER!!!", "1234567890", "chris@gmail.com"));
                break;
            case "Technology":
                tutorList.add(new Tutor("JOHN CENA", "Technology", 2, "DUN DUN DUN DUUUUNNNNN", "1234567890", "chris@gmail.com"));
                tutorList.add(new Tutor("Doge", "Technology", 2, "MUCH DESIGN! WOW!", "1234567890", "chris@gmail.com"));
                break;
            default:
                Toast.makeText(this, "NO TUTORS!!!!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tutor_list, menu);
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
