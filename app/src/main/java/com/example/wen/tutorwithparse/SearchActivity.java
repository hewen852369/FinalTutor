package com.example.wen.tutorwithparse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.example.wen.tutorwithparse.Adapters.TutorListAdapter;
import com.example.wen.tutorwithparse.Models.Tutor;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import android.widget.ListView;


public class SearchActivity extends AppCompatActivity implements Serializable {
    String[] items;

    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText editText;
    String memberID;
    String password;
    ArrayList<ParseObject> ArrObj;
    List<ParseObject> ArrObj1;
    ParseObject p;


    private ArrayList<Tutor> tutorList;

    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tutor_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Search");
        ArrObj = new ArrayList<ParseObject>();
        //listView = (ListView)findViewById(R.id.listview);
        //editText = (EditText)findViewById(R.id.txtsearch);
        //queryParse();

        initList();
        /*editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    initList();
                } else {
                    searchItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/




    }

    public void searchItem(String textToSearch){
        for(String item:items){
            if(!item.contains(textToSearch)){
                listItems.remove(item);
            }
        }

        adapter.notifyDataSetChanged();
    }


    public void queryParse(){
        ParseQuery<ParseObject> query = new ParseQuery("Members");
        Toast password_check = Toast.makeText(this,"Search the database",Toast.LENGTH_SHORT);

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
                            memberID = p.getString("MemberID");
                            password = p.getString("password");
                            Log.d("MemberFromParse", "MemberID:" + ArrObj.get(i).getString("MemberID"));
                            Log.d("Sizeof ArrObj", "M" + ArrObj.size());
                            String temp;
                            temp = ArrObj.get(i).getString("MemberId") + "\n" + ArrObj.get(i).getString("Name");
                            listItems.add(i, temp);
                            Log.d("Test", temp);

                        }


                    }

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }


            }

        });

    }

    public void initList(){


        ParseQuery<ParseObject> query = new ParseQuery("Members");
        //query.orderByAscending("MemberID");
        //query.whereNotEqualTo("MemberID", "Michael Yabuti");
        //query.whereExists("MemberID");

        //query.whereEqualTo("MemberID", "tutor123");
        //Toast password_check = Toast.makeText(this,"Search the database",Toast.LENGTH_SHORT);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> userList, ParseException e) {
                //dlg.dismiss();
                //Log.d("klakla", "get: " + e + userList.size());
                tutorList = new ArrayList<>();
                if (e == null) {
                    if (userList.size() > 0) {
                        String temp;
                        String type;
                        for (int i = 0; i < userList.size(); i++) {


                            p = userList.get(i);
                            ArrObj.add(p);

                            memberID = p.getString("MemberID");
                            password = p.getString("password");
                            Log.d("MemberFromParse", "MemberID:" + ArrObj.get(i).getString("MemberID"));
                            Log.d("Sizeof ArrObj", "M" + ArrObj.size());

                            //items[i] =ArrObj.get(i).getString("MemberID");
                            temp = ArrObj.get(i).getString("MemberID") + "\n" + ArrObj.get(i).getString("Name");
                            //listItems.add(ArrObj.get(i).getString("MemberID"));
                            type = ArrObj.get(i).getString("UserType");

                            listItems.add(temp);
                            Log.d("text", temp);
                            if (type.equals("tutor")) {
                                tutorList.add(new Tutor(ArrObj.get(i).getString("Name"), ArrObj.get(i).getString("Subject"), 2, "Hey!", "1234567890", ArrObj.get(i).getString("Name")+"@gmail.com"));
                                }


                        }

                        //items = new String[]{ArrObj.get(0).getString("MemberID")};

                        //listItems = new ArrayList<>(Arrays.asList(items));


                        /*1
                        adapter =  new ArrayAdapter<String>(SearchActivity.this, R.layout.list_item, R.id.txtitem,listItems);
                        listView.setAdapter(adapter);
                        */

                        //listItems =  toArr(listItems);
                        //tutorList = new ArrayList<>();
                        //tutorList.add(new Tutor("Jeff", "Math", 2, "Hey!"));
                        //tutorList.add(new Tutor("Jerry", "Math", 2, "Suh dude"));
                        ListAdapter myAdapter = new TutorListAdapter(SearchActivity.this, tutorList);
                        ListView categoryListView = (ListView) findViewById(R.id.tutorListView1);
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

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                    // Alert.alertOneBtn(getActivity(),"Something went wrong!");
                }


            }

        });

        Log.d("initlist: ", "MemberID !");


    }



    public void goToDetails(Tutor tutor) {
        Intent details = new Intent(this, TutorDetailsActivity.class);
        details.putExtra("Tutor", tutor);
        startActivity(details);
    }






    public void refineSearchClick(View v)
    {
        finish();
        Intent i = new Intent(SearchActivity.this,RefineSearch.class);
        startActivity(i);

    }



}
