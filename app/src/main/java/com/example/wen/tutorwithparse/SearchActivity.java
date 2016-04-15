package com.example.wen.tutorwithparse;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.InterruptedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import android.widget.ListView;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.parse.ParseUser;

public class SearchActivity extends AppCompatActivity {
    String[] items;

    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView listView;
    ListView listView3;
    EditText editText;
    EditText etSearch;
    TextView tList;
    String memberID;
    String password;
    ArrayList<ParseObject> ArrObj;
    List<ParseObject> ArrObj1;
    ParseObject p;

    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search");
        ArrObj = new ArrayList<ParseObject>();
        listView = (ListView)findViewById(R.id.listview);
        editText = (EditText)findViewById(R.id.txtsearch);
        //queryParse();

        initList();
        editText.addTextChangedListener(new TextWatcher() {
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
        });
        /*TextView tv = (TextView) findViewById(R.id.SearchResults);
        String greeting = "hi";

        tv.setText(greeting);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Members");
        query.whereGreaterThan("MemberID", 0);*/



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
        //query.orderByAscending("MemberID");
        //query.whereNotEqualTo("MemberID", "Michael Yabuti");
        //query.whereExists("MemberID");

        //query.whereEqualTo("MemberID", "tutor123");
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

                            //ArrObj.get(i).getString("MemberId") + "\n" + ArrObj.get(i).getString("Name");
                            Log.d("Test", temp);
                            //listItems.add(i, ArrObj.get(i).getString("MemberId"));
                            //items = new String[]{ArrObj1.get(0).getString("MemberID")};

                        }

                    }

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                    // Alert.alertOneBtn(getActivity(),"Something went wrong!");
                }


            }

        });

    }

    public void initList(){

        //items = new String[100];
        items = new String[]{"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola",
                "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia",
                "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium",
                "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island",
                "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi",
                "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile",
                "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the",
                "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica",
                "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia",
                "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia",
                "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe",
                "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras",
                "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan",
                "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan",
                "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania",
                "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands",
                "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat",
                "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger",
                "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru",
                "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis",
                "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone",
                "Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain",
                "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic",
                "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan",
                "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan",
                "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"};

        ParseQuery<ParseObject> query = new ParseQuery("Members");
        //query.orderByAscending("MemberID");
        //query.whereNotEqualTo("MemberID", "Michael Yabuti");
        //query.whereExists("MemberID");

        //query.whereEqualTo("MemberID", "tutor123");
        Toast password_check = Toast.makeText(this,"Search the database",Toast.LENGTH_SHORT);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> userList, ParseException e) {
                //dlg.dismiss();
                //Log.d("klakla", "get: " + e + userList.size());
                if (e == null) {
                    if (userList.size() > 0) {
                        String temp;
                        for (int i = 0; i < userList.size(); i++) {
                            p = userList.get(i);
                            ArrObj.add(p);
                            memberID = p.getString("MemberID");
                            password  = p.getString("password");
                            Log.d("MemberFromParse", "MemberID:" + ArrObj.get(i).getString("MemberID"));
                            Log.d("Sizeof ArrObj", "M" + ArrObj.size());

                            //items[i] =ArrObj.get(i).getString("MemberID");
                            temp = ArrObj.get(i).getString("MemberID") + "\n" + ArrObj.get(i).getString("Name");
                            //listItems.add(ArrObj.get(i).getString("MemberID"));
                            listItems.add(temp);
                            Log.d("text", temp);

                        }
                        //items = new String[]{ArrObj.get(0).getString("MemberID")};

                        //listItems = new ArrayList<>(Arrays.asList(items));
                        adapter =  new ArrayAdapter<String>(SearchActivity.this, R.layout.list_item, R.id.txtitem,listItems);
                        listView.setAdapter(adapter);

                    }

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                    // Alert.alertOneBtn(getActivity(),"Something went wrong!");
                }


            }

        });
        //Log.d("Out", "M" + ArrObj.size());
        //memberID = "Hi";

        /*ParseQuery<ParseObject> query = ParseQuery.getQuery("Members");
        //query.whereEqualTo("MemberID", "a");
        //query.getClassName();
        query.findInBackground(new FindCallback<ParseObject>(){
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    if(list.size()==0)
                    {
                        int x = 0;
                    }
                    else
                    {
                        ParseObject obj = list.get(0);
                         memberID= obj.getString("Password");
                    }


                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });*


        /*ParseQuery<ParseObject> query = ParseQuery.getQuery("Members");
        query.findInBackground(new FindCallback<ParseObject>() {
           public void done(List<ParseObject> objects, ParseException e) {
               if (e == null) {
                   ParseObject obj = objects.get(1);
                   listItems = objects;
               } else {
                   items = new String[]{"fail"};
               }
           }
       });*/

        Log.d("initlist: ","MemberID !");
        //Log.d("initlist: ","MemberID:" + ArrObj.get(0).getString("MemberID"));
        //items = new String[]{ArrObj1.get(0).getString("MemberID")};

        //listItems = new ArrayList<>(Arrays.asList(items));
        //adapter =  new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem,listItems);
        //listView.setAdapter(adapter);


    }



    public void refineSearchClick(View v)
    {
        //if(v.getId()==R.id.Bsignup)
        {
            Intent i = new Intent(SearchActivity.this,RefineSearch.class);
            startActivity(i);
        }
    }



}
