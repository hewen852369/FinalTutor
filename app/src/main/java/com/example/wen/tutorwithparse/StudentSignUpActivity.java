package com.example.wen.tutorwithparse;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;

public class StudentSignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Student Sign Up");
    }

    public void signupClick(View v)
    {
        if(v.getId()==R.id.Bstudentsignup)
        {
            EditText address = (EditText)findViewById(R.id.TFaddress);
            EditText cell = (EditText)findViewById(R.id.TFcell);
            String addressstr = address.getText().toString();
            String cellstr = cell.getText().toString();

            if(addressstr.isEmpty())
            {
                Toast address_check = Toast.makeText(StudentSignUpActivity.this,"Please enter address.",Toast.LENGTH_SHORT);
                address_check.show();
            }
            else if(cellstr.length()!=10)
            {
                Toast cell_check = Toast.makeText(StudentSignUpActivity.this,"Invalid cell phone number",Toast.LENGTH_SHORT);
                cell_check.show();
            }
            else
            {
                //save data to database
                String username = getIntent().getStringExtra("username");
                String email = getIntent().getStringExtra("email");
                String name = getIntent().getStringExtra("name");
                String password = getIntent().getStringExtra("password");
                ParseObject user = new ParseObject("Members");
                user.put("MemberID",username);
                user.put("Email",email);
                user.put("Name",name);
                user.put("Password",password);
                user.put("Address",addressstr);
                user.put("PhoneNumber",cellstr);
                user.put("UserType","student");
                user.saveInBackground();
                Intent i = new Intent(StudentSignUpActivity.this,LoginActivity.class);
                startActivity(i);

            }
        }
    }

}
