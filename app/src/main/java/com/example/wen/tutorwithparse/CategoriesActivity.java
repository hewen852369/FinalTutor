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
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class CategoriesActivity extends AppCompatActivity {


    private ListView categoryListView;
    private ArrayList<CategoriesRowItem> rowItems;
    private ParseObject p;
    private ArrayList<ParseObject> ArrObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        rowItems = new ArrayList<>();
        addCategories();

        categoryListView = (ListView) findViewById(R.id.CategoryListView);
        CategoryAdapter adapter = new CategoryAdapter(this, rowItems);
        categoryListView.setAdapter(adapter);

        categoryListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //converts the value of the list position into a string
                        CategoriesRowItem item =(CategoriesRowItem) parent.getItemAtPosition(position);
                        String name = item.getTitle();
                        goToSubCategory(name);
                    }
                }
        );
    }

    /*
    public void queryParse() {
        ParseQuery<ParseObject> query = new ParseQuery("Members");
        //query.orderByAscending("MemberID");
        //query.whereNotEqualTo("MemberID", "Michael Yabuti");
        //query.whereExists("MemberID");

        //query.whereEqualTo("MemberID", "tutor123");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> userList, ParseException e) {
                //dlg.dismiss();
                //Log.d("klakla", "get: " + e + userList.size());
                if (e == null) {
                    if (userList.size() > 0) {

                        for (int i = 0; i < userList.size(); i++) {
                            p = userList.get(i);
                            ArrObj.add(p);
                            //col name -->  p.getString("");

                        }

                    }

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                    // Alert.alertOneBtn(getActivity(),"Something went wrong!");
                }
            }

        });
        */

    private void addCategories() {
        rowItems.add(new CategoriesRowItem(R.drawable.math_52, "Math"));
        rowItems.add(new CategoriesRowItem(R.drawable.test_tube_52, "Science"));
        rowItems.add(new CategoriesRowItem(R.drawable.conference_48, "Social Studies"));
        rowItems.add(new CategoriesRowItem(R.drawable.literature_52, "English/Language Arts"));
        rowItems.add(new CategoriesRowItem(R.drawable.university_52, "Fine Arts"));
        rowItems.add(new CategoriesRowItem(R.drawable.ic_work_black_48dp, "Business Applications"));
        rowItems.add(new CategoriesRowItem(R.drawable.ic_phonelink_black_48dp, "Technology"));
    }


    public void goToSubCategory(String category) {
        Intent subcategory = new Intent(this, SubcategoriesActivity.class);
        subcategory.putExtra("Category", category);
        startActivity(subcategory);
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
