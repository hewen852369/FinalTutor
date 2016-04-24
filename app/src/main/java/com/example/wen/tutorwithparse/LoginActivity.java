package com.example.wen.tutorwithparse;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


import java.util.List;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tutors");
    }

    public void loginClick(View v)
    {
        if(v.getId()==R.id.Blogin)
        {
            final Intent i = new Intent(LoginActivity.this,HomeActivity.class);

            EditText a = (EditText)findViewById(R.id.TFusername);
            EditText b = (EditText)findViewById(R.id.TFpassword);
            final String username = a.getText().toString();
            final String password = b.getText().toString();

            //code for check user login info
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Members");
            query.whereEqualTo("MemberID", username);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if (e == null)
                    {
                        if(list.size()==0)
                        {
                            Toast password_check = Toast.makeText(LoginActivity.this,"Username or Password incorrect.",Toast.LENGTH_SHORT);
                            password_check.show();
                        }
                        else
                        {
                            ParseObject obj = list.get(0);
                            if (obj.getString("Password").equals(password)) {
                                i.putExtra("Username", username);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);

                            }
                            else
                            {
                                Toast password_check = Toast.makeText(LoginActivity.this,"Username or Password incorrect.",Toast.LENGTH_SHORT);
                                password_check.show();
                            }
                        }
                    }
                    else
                    {
                        Toast password_check = Toast.makeText(LoginActivity.this,"Login fail.",Toast.LENGTH_SHORT);
                        password_check.show();
                    }
                }
            });

        }
    }

    public void signupClick(View v)
    {
        if(v.getId()==R.id.Bsignup)
        {
            Intent i = new Intent(LoginActivity.this,SignupActivity.class);
            startActivity(i);
        }
    }

    public void resetClick(View v)
    {
        if (v.getId()==R.id.Breset)
        {
            Toast.makeText(LoginActivity.this, "Please contact our team via tutor@support.edu", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
