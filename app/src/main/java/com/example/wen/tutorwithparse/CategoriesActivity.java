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

import java.util.ArrayList;
import com.example.wen.tutorwithparse.Adapters.CustomAdapter;

public class CategoriesActivity extends AppCompatActivity {

    //TODO: Get from Parse
    private static final String[] names = new String[] { "Math", "Science", "Social Studies",
            "English/Language Arts", "Fine Arts", "Business Applications", "Technology" };

    /*private static final Integer[] images = { R.drawable.ic_reply,
            R.drawable.ic_retweet, R.drawable.ic_person_black_48dp, R.drawable.ic_star };*/

    //ListView listView;
    //ArrayList<CategoriesRowItem> rowItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        /* Was under development, but idk if i want to continue... */

        //ArrayList<String> namesList = new ArrayList<String>(Arrays.asList(names));
        /*
        rowItems = new ArrayList<CategoriesRowItem>();
        for (int i = 0; i < names.length; i++) {
            CategoriesRowItem item = new CategoriesRowItem(images[i], names[i]);
            rowItems.add(item);
        }

        listView = (ListView) findViewById(R.id.CategoryListView);
        CategoryAdapter adapter = new CategoryAdapter(this, rowItems);
        listView.setAdapter(adapter);
        */

        //simple_list_item_1 is a basic text list
        ListAdapter myAdapter = new CustomAdapter(this, names);
        ListView categoryListView = (ListView) findViewById(R.id.CategoryListView);
        categoryListView.setAdapter(myAdapter);


        categoryListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //converts the value of the list position into a string
                        String name = String.valueOf(parent.getItemAtPosition(position));
                        goToSubCategory(name);
                    }
                }
        );
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
