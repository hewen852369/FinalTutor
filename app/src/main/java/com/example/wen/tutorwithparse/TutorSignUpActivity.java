package com.example.wen.tutorwithparse;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;

public class TutorSignUpActivity extends AppCompatActivity {

    private String subject;
    private EditText address;
    private EditText cell;
    private EditText subcategory;
    private Spinner subjectList;
    private EditText price;
    private EditText about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tutor Sign Up");

        subject = "";
        address = (EditText)findViewById(R.id.TFaddress);
        cell = (EditText)findViewById(R.id.TFcell);
        subcategory = (EditText)findViewById(R.id.et_subCategory);
        subjectList = (Spinner) findViewById(R.id.subjectList);
        price = (EditText)findViewById(R.id.TFprice);
        about = (EditText)findViewById(R.id.TFabout);

        assert subjectList != null;
        subjectList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String subject = String.valueOf(parentView.getItemAtPosition(position));
                setSubject(subject);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                setSubject("Others");
            }
        });
    }

    public void setSubject(String str) {
        subject = str;
    }


    public void TutorsignupClick(View v)
    {
        if(v.getId()==R.id.Btutorsignup)
        {

            String subcategorystr = subcategory.getText().toString();
            String addressstr = address.getText().toString();
            String cellstr = cell.getText().toString();
            String pricesstr = price.getText().toString();
            String aboutstr = about.getText().toString();

            if(addressstr.isEmpty())
            {
                Toast address_check = Toast.makeText(TutorSignUpActivity.this,"Please enter address.",Toast.LENGTH_SHORT);
                address_check.show();
            }
            else if(cellstr.length()!=10)
            {
                Toast cell_check = Toast.makeText(TutorSignUpActivity.this,"Invalid cell phone number",Toast.LENGTH_SHORT);
                cell_check.show();
            }
            else if(subject.isEmpty())
            {
                Toast subject_check = Toast.makeText(TutorSignUpActivity.this,"Please enter subject.",Toast.LENGTH_SHORT);
                subject_check.show();
            }
            else if(Integer.valueOf(pricesstr)<0)
            {
                Toast price_check = Toast.makeText(TutorSignUpActivity.this,"Invalid price.",Toast.LENGTH_SHORT);
                price_check.show();
            }
            else if (subcategorystr.isEmpty())
            {
                Toast price_check = Toast.makeText(TutorSignUpActivity.this,"Please enter a course.",Toast.LENGTH_SHORT);
                price_check.show();
            }
            else
            {
                //save data to database
                String username = getIntent().getStringExtra("username");
                String email = getIntent().getStringExtra("email");
                String name = getIntent().getStringExtra("name");
                String password = getIntent().getStringExtra("password");
                ParseObject user = new ParseObject("Members");

                int tutorPrice = Integer.parseInt(pricesstr);
                user.put("MemberID",username);
                user.put("Email",email);
                user.put("Name",name);
                user.put("Password",password);
                user.put("Address",addressstr);
                user.put("PhoneNumber", cellstr);
                user.put("UserType", "tutor");



                ParseObject tutor = new ParseObject("TutorsSubjects");

                tutor.put("MemberID",username);
                tutor.put("Subject",subject);
                tutor.put("Subcategory", subcategorystr);
                tutor.put("Price",tutorPrice);
                tutor.put("Message",aboutstr);
                tutor.put("members", user);
                tutor.put("numStudents",0);
                tutor.saveInBackground();

                Intent i = new Intent(TutorSignUpActivity.this,LoginActivity.class);
                startActivity(i);

            }
        }
    }



}
