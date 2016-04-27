package com.example.wen.tutorwithparse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wen.tutorwithparse.Adapters.CategoryAdapter;
import com.example.wen.tutorwithparse.Adapters.TutorListAdapter;
import com.example.wen.tutorwithparse.Models.CategoriesRowItem;
import com.example.wen.tutorwithparse.Models.Tutor;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TutorListActivity extends AppCompatActivity implements Serializable {

    private String subCategoryName;
    private String categoryName;
    private ArrayList<Tutor> tutorList;
    private ListAdapter tutorListAdapter;
    ListView tutorListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_list);

        tutorList = new ArrayList<>();

        //subCategoryName = getIntent().getExtras().getString("Subcategory");
        categoryName = getIntent().getExtras().getString("Category");

        //getTutorNames(subCategoryName);

        queryParse();

    }

    public void queryParse() {
        ParseQuery<ParseObject> query = new ParseQuery<>("TutorsSubjects");
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
        query.include("members");
        if (!categoryName.equals("Others"))
            query.whereEqualTo("Subject", categoryName);
        else
            query.whereNotEqualTo("Subject", categoryName);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> userList, ParseException e) {
                if (e == null) {
                    if (userList.size() > 0) {
                        String type;
                        ParseObject tutor;
                        ParseObject member;

                        for (int i = 0; i < userList.size(); i++) {
                            tutor = userList.get(i);
                            member = userList.get(i).getParseObject("members");

                            Tutor t = new Tutor(member.getString("Name"), tutor.getString("Subject"),
                                    tutor.getString("Subcategory"), tutor.getInt("numStudents"), tutor.getString("Message"),
                                    member.getString("PhoneNumber"), member.getString("Email"), member.getString("Address"),
                                    tutor.getInt("Price"));

                            if (!doesTutorExist(t))
                                tutorList.add(t);
                        }

                        tutorListAdapter = new TutorListAdapter(TutorListActivity.this, tutorList);
                        tutorListView = (ListView) findViewById(R.id.tutorListView);
                        tutorListView.setAdapter(tutorListAdapter);

                        tutorListView.setOnItemClickListener(
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
                    //Alert.alertOneBtn(getActivity(),"Something went wrong!");
                    Toast.makeText(TutorListActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    public void goToDetails(Tutor tutor) {
        Intent details = new Intent(this, TutorDetailsActivity.class);
        details.putExtra("Tutor", tutor);
        startActivity(details);
    }

    private boolean doesTutorExist(Tutor tutor) {
        if (!tutorList.isEmpty())
            for(Tutor row : tutorList)
                if (row.getPhoneNumber() == tutor.getPhoneNumber())
                    return true;
        return false;
    }

    //TODO: Get tutors from Parse
    private void getTutorNames(String name) {
        switch (name) {
            case "Math":
                tutorList.add(new Tutor("Jeff", "Math", "Math", 2, "Hey!", "1234567890", "jeff@gmail.com", "1111 Somewhere Dr.", 5));
                tutorList.add(new Tutor("Jerry", "Math","Math", 2, "Suh dude", "1234567890", "Jerry@gmail.com", "1111 Somewhere Dr.", 5));
                tutorList.add(new Tutor("Chris", "Math", "Math",1, "lolwut?", "1234567890", "chris@gmail.com", "1111 Somewhere Dr.", 5));
                tutorList.add(new Tutor("David", "Math", "Math",2, "What up boi?", "1234567890", "chris@gmail.com", "1111 Somewhere Dr.", 5));
                tutorList.add(new Tutor("Zoe", "Math", "Math",0, "WHATS 9 + 10?                21?", "1234567890", "chris@gmail.com", "1111 Somewhere Dr.", 5));
                tutorList.add(new Tutor("John", "Math", "Math",1, "I forgot how to breathe                  ._.", "1234567890", "chris@gmail.com", "1111 Somewhere Dr.", 5));
                tutorList.add(new Tutor("Steve", "Math", "Math",2, "What would you do-oo-oo for a Klondike bar?", "1234567890", "chris@gmail.com", "1111 Somewhere Dr.", 5));
                tutorList.add(new Tutor("Rachel", "Math", "Math",4, "Ba-dum-tissssss", "1234567890", "chris@gmail.com", "1111 Somewhere Dr.", 5));
                break;
            case "Science":
                tutorList.add(new Tutor("Talia", "Science", "Math",1, "Word!", "1234567890", "chris@gmail.com", "1111 Somewhere Dr.", 5));
                tutorList.add(new Tutor("Sarah", "Science", "Math",1, "DAAAAANNNNNNGGGGG!!!!!!!!", "1234567890", "chris@gmail.com", "1111 Somewhere Dr.", 5));
                break;
            case "Social Studies":
                tutorList.add(new Tutor("Charles", "Social Studies", "Math",0, "Cool", "1234567890", "chris@gmail.com", "1111 Somewhere Dr.", 5));
                break;
            case "English/Language Arts":
                tutorList.add(new Tutor("Quincy", "English", "Math",0, "BRUHHHH", "1234567890", "chris@gmail.com", "1111 Somewhere Dr.", 5));
                break;
            case "Fine Arts":
                tutorList.add(new Tutor("Jane", "Fine Arts", "Math",2, "Think out side the box", "1234567890", "chris@gmail.com", "1111 Somewhere Dr.", 5));
                tutorList.add(new Tutor("Rachel", "Fine Arts", "Math",0, "LOL", "1234567890", "chris@gmail.com", "1111 Somewhere Dr.", 5));
                break;
            case "Business Applications":
                tutorList.add(new Tutor("Stanley", "Business Applications", "Math",3, "plssss", "1234567890", "chris@gmail.com", "1111 Somewhere Dr.", 5));
                tutorList.add(new Tutor("Richard", "Business Applications", "Math",3, "Can I has cheezburger", "1234567890", "chris@gmail.com", "1111 Somewhere Dr.", 5));
                tutorList.add(new Tutor("Steve", "Business Applications", "Math",3, "Best App EVER!!!", "1234567890", "chris@gmail.com", "1111 Somewhere Dr.", 5));
                break;
            case "Technology":
                tutorList.add(new Tutor("JOHN CENA", "Technology", "Math",2, "DUN DUN DUN DUUUUNNNNN", "1234567890", "chris@gmail.com", "1111 Somewhere Dr.", 5));
                tutorList.add(new Tutor("Doge", "Technology", "Math",2, "MUCH DESIGN! WOW!", "1234567890", "chris@gmail.com", "1111 Somewhere Dr.", 5));
                break;
            default:
                Toast.makeText(this, "NO TUTORS!!!!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
