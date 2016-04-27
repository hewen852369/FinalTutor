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

import com.parse.ParseException;
import com.parse.ParseObject;

public class TutorSignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tutor Sign Up");
    }


    public void TutorsignupClick(View v)
    {
        if(v.getId()==R.id.Btutorsignup)
        {
            EditText address = (EditText)findViewById(R.id.TFaddress);
            EditText cell = (EditText)findViewById(R.id.TFcell);
            EditText subject = (EditText)findViewById(R.id.TFsubject);
            EditText price = (EditText)findViewById(R.id.TFprice);
            EditText about = (EditText)findViewById(R.id.TFabout);

            String addressstr = address.getText().toString();
            String cellstr = cell.getText().toString();
            String subjectstr = subject.getText().toString();
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
            else if(subjectstr.isEmpty())
            {
                Toast subject_check = Toast.makeText(TutorSignUpActivity.this,"Please enter subject.",Toast.LENGTH_SHORT);
                subject_check.show();
            }
            else if(Integer.valueOf(pricesstr)<0)
            {
                Toast price_check = Toast.makeText(TutorSignUpActivity.this,"Invalid price.",Toast.LENGTH_SHORT);
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
                // user.saveInBackground();


                ParseObject tutor = new ParseObject("TutorsSubjects");//tutor.put("members", user.getObjectId());
                //tutor.put("member","$"+user.getObjectId());
                tutor.put("MemberID",username);
                tutor.put("Subject",subjectstr);
                tutor.put("Price",tutorPrice);
                tutor.put("Message",aboutstr);
                tutor.put("members", user);
                tutor.saveInBackground();

                Intent i = new Intent(TutorSignUpActivity.this,LoginActivity.class);
                startActivity(i);

            }
        }
    }

}
