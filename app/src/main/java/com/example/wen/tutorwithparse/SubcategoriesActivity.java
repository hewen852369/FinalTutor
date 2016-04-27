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

import com.example.wen.tutorwithparse.Adapters.SubcategoryAdapter;

public class SubcategoriesActivity extends AppCompatActivity {

    private String[] subcategory;
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategories);

        categoryName = getIntent().getExtras().getString("Category");

        getSubcategoryList(categoryName);

        ListAdapter myAdapter = new SubcategoryAdapter(this, subcategory);

        ListView categoryListView = (ListView) findViewById(R.id.subcategoryListView);
        categoryListView.setAdapter(myAdapter);

        categoryListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String subCategoryName = String.valueOf(parent.getItemAtPosition(position));
                        goToTutorList(subCategoryName);
                    }
                }
        );
    }

    //TODO: Get subcategories from Parse
    private void getSubcategoryList(String category) {
        switch(category) {
            case "Math":
                subcategory = new String[] {"Algebra", "Geometry", "College Algebra", "Pre-Calculus", "Calculus"};
                break;
            case "Science":
                subcategory = new String[] {"Biology", "Chemistry", "Physics", "Geology"};
                break;
            case "Social Studies":
                subcategory = new String[] {"World History", "US History", "World Geography", "US Government", "Macroeconomics", "Microeconomics"};
                break;
            case "English/Language Arts":
                subcategory = new String[] {"English Grammar", "American Literature", "British Literature", "World Literature"};
                break;
            case "Fine Arts":
                subcategory = new String[] {"Music", "Drawing", "Painting", "Dancing"};
                break;
            case "Business Applications":
                subcategory = new String[] {"Information Systems", "Accounting", "Finance"};
                break;
            case "Technology":
                subcategory = new String[] {"Programming", "Multimedia", "Web Development", "Computers", "Electronics"};
                break;
            default:
                subcategory = new String[0];
                break;
        }
    }

    public void goToTutorList(String name) {
        String username = getIntent().getStringExtra("Username");
        Intent tutorList = new Intent(this, TutorListActivity.class);
        tutorList.putExtra("Username", username);
        tutorList.putExtra("Subcategory", categoryName);
        startActivity(tutorList);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_subcategories, menu);
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
