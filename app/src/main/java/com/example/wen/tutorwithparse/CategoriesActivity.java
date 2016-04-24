package com.example.wen.tutorwithparse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.wen.tutorwithparse.Adapters.CategoryAdapter;
import com.example.wen.tutorwithparse.Models.CategoriesRowItem;
import com.example.wen.tutorwithparse.Models.Tutor;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class CategoriesActivity extends AppCompatActivity {

    private ListView categoryListView;
    private ArrayList<CategoriesRowItem> rowItems;
    private ParseObject p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        rowItems = new ArrayList<>();
        //addCategories();
        queryParse();

        categoryListView = (ListView) findViewById(R.id.CategoryListView);
        CategoryAdapter adapter = new CategoryAdapter(CategoriesActivity.this, rowItems);
        categoryListView.setAdapter(adapter);

        categoryListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //converts the value of the list position into a string
                        CategoriesRowItem item = (CategoriesRowItem) parent.getItemAtPosition(position);
                        String name = item.getTitle();
                        //goToSubCategory(name);
                        goToTutorList(name);
                    }
                }
        );


    }


    public void queryParse() {
        ParseQuery<ParseObject> query = new ParseQuery<>("TutorsSubjects");

        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> userList, ParseException e) {
                if (e == null) {
                    if (userList.size() > 0) {
                        String type;

                        for (int i = 0; i < userList.size(); i++) {
                            p = userList.get(i);
                            addCategory(p.getString("Subject"));
                        }
                    }

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                    //Alert.alertOneBtn(getActivity(),"Something went wrong!");
                    Toast.makeText(CategoriesActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

        });
    }


    private void addCategories() {
        rowItems.add(new CategoriesRowItem(R.drawable.math_52, "Math"));
        rowItems.add(new CategoriesRowItem(R.drawable.test_tube_52, "Science"));
        rowItems.add(new CategoriesRowItem(R.drawable.conference_48, "Social Studies"));
        rowItems.add(new CategoriesRowItem(R.drawable.literature_52, "English/Language Arts"));
        rowItems.add(new CategoriesRowItem(R.drawable.university_52, "Fine Arts"));
        rowItems.add(new CategoriesRowItem(R.drawable.ic_work_black_48dp, "Business Applications"));
        rowItems.add(new CategoriesRowItem(R.drawable.ic_phonelink_black_48dp, "Technology"));
    }

    private void addCategory(String category) {
        if (doesCategoryExist(category))
            return;

        switch (category) {
            case "Math":
                rowItems.add(new CategoriesRowItem(R.drawable.math_52, "Math"));
                break;
            case "Science":
                rowItems.add(new CategoriesRowItem(R.drawable.test_tube_52, "Science"));
                break;
            case "Social Studies":
                rowItems.add(new CategoriesRowItem(R.drawable.conference_48, "Social Studies"));
                break;
            case "English/Language Arts":
                rowItems.add(new CategoriesRowItem(R.drawable.literature_52, "English/Language Arts"));
                break;
            case "Fine Arts":
                rowItems.add(new CategoriesRowItem(R.drawable.university_52, "Fine Arts"));
                break;
            case "Business Applications":
                rowItems.add(new CategoriesRowItem(R.drawable.ic_work_black_48dp, "Business Applications"));
                break;
            case "Technology":
                rowItems.add(new CategoriesRowItem(R.drawable.ic_phonelink_black_48dp, "Technology"));
                break;
            default:
                rowItems.add(new CategoriesRowItem(R.drawable.ic_person_black_48dp, "Others"));
                break;
        }
    }

    private boolean doesCategoryExist(String name) {
        for(CategoriesRowItem row : rowItems)
            if (row.getTitle().equals(name))
                return true;
        return false;
    }


    public void goToSubCategory(String category) {
        Intent subcategory = new Intent(this, SubcategoriesActivity.class);
        subcategory.putExtra("Category", category);
        startActivity(subcategory);
    }

    public void goToTutorList(String category) {
        Intent tutorList = new Intent(this, TutorListActivity.class);
        tutorList.putExtra("Category", category);
        startActivity(tutorList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categories, menu);
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
