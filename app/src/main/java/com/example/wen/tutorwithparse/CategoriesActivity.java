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

import java.io.Serializable;
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
    private ArrayList<String> names;
    private ParseObject p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        rowItems = new ArrayList<>();
        names = new ArrayList<>();
        //addCategories();
        queryParse();
    }

    public void queryParse() {
        ParseQuery<ParseObject> query = new ParseQuery<>("TutorsSubjects");

        query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);

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
                names.add("Math");
                break;
            case "Science":
                rowItems.add(new CategoriesRowItem(R.drawable.test_tube_52, "Science"));
                names.add("Science");
                break;
            case "Social Studies":
                rowItems.add(new CategoriesRowItem(R.drawable.conference_48, "Social Studies"));
                names.add("Social Studies");
                break;
            case "English/Language Arts":
                rowItems.add(new CategoriesRowItem(R.drawable.literature_52, "English/Language Arts"));
                names.add("English/Language Arts");
                break;
            case "Fine Arts":
                rowItems.add(new CategoriesRowItem(R.drawable.university_52, "Fine Arts"));
                names.add("Fine Arts");
                break;
            case "Business Applications":
                rowItems.add(new CategoriesRowItem(R.drawable.ic_work_black_48dp, "Business Applications"));
                names.add("Business Applications");
                break;
            case "Technology":
                rowItems.add(new CategoriesRowItem(R.drawable.ic_phonelink_black_48dp, "Technology"));
                names.add("Technology");
                break;
            default:
                rowItems.add(new CategoriesRowItem(R.drawable.ic_person_black_48dp, "Others"));
                names.add("Others");
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
        String username = getIntent().getStringExtra("Username");
        Intent subcategory = new Intent(this, SubcategoriesActivity.class);
        subcategory.putExtra("Username", username);
        subcategory.putExtra("Category", category);
        startActivity(subcategory);
    }

    public void goToTutorList(String category) {
        String username = getIntent().getStringExtra("Username");
        Intent tutorList = new Intent(this, TutorListActivity.class);
        tutorList.putExtra("Username", username);
        tutorList.putExtra("Category", category);
        tutorList.putExtra("Username",getIntent().getStringExtra("Username"));
        tutorList.putExtra("CategoryList", names);
        startActivity(tutorList);
    }
}
