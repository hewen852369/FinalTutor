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
                tutorList.add(new Tutor("Jeff", "Math", 2, "Hey!"));
                tutorList.add(new Tutor("Jerry", "Math", 2, "Suh dude"));
                tutorList.add(new Tutor("Chris", "Math", 1, "lolwut?"));
                tutorList.add(new Tutor("David", "Math", 2, "What up boi?"));
                tutorList.add(new Tutor("Zoe", "Math", 0, "WHATS 9 + 10?                21?"));
                tutorList.add(new Tutor("John", "Math", 1, "I forgot how to breathe                  ._."));
                tutorList.add(new Tutor("Steve", "Math", 2, "What would you do-oo-oo for a Klondike bar?"));
                tutorList.add(new Tutor("Rachel", "Math", 4, "Ba-dum-tissssss"));
                break;
            case "Science":
                tutorList.add(new Tutor("Talia", "Science", 1, "Word!"));
                tutorList.add(new Tutor("Sarah", "Science", 1, "DAAAAANNNNNNGGGGG!!!!!!!!"));
                break;
            case "Social Studies":
                tutorList.add(new Tutor("Charles", "Social Studies", 0, "Cool"));
                break;
            case "English/Language Arts":
                tutorList.add(new Tutor("Quincy", "English", 0, "BRUHHHH"));
                break;
            case "Fine Arts":
                tutorList.add(new Tutor("Jane", "Fine Arts", 2, "Think out side the box"));
                tutorList.add(new Tutor("Rachel", "Fine Arts", 0, "LOL"));
                break;
            case "Business Applications":
                tutorList.add(new Tutor("Stanley", "Business Applications", 3, "plssss"));
                tutorList.add(new Tutor("Richard", "Business Applications", 3, "Can I has cheezburger"));
                tutorList.add(new Tutor("Steve", "Business Applications", 3, "Best App EVER!!!"));
                break;
            case "Technology":
                tutorList.add(new Tutor("JOHN CENA", "Technology", 2, "DUN DUN DUN DUUUUNNNNN"));
                tutorList.add(new Tutor("Doge", "Technology", 2, "MUCH DESIGN! WOW!"));
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
