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

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class SignupActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sign Up");
    }

    public void signupClick(View v)
    {
        if(v.getId()==R.id.Bsignup)
        {
            EditText name = (EditText)findViewById(R.id.TFname);
            EditText email = (EditText)findViewById(R.id.TFemail);
            EditText uname = (EditText)findViewById(R.id.TFusername);
            EditText pass1 = (EditText)findViewById(R.id.TFpassword);
            EditText pass2 = (EditText)findViewById(R.id.TFpassword1);

            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String unamestr = uname.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();
            if(namestr.isEmpty())
            {
                Toast name_check = Toast.makeText(SignupActivity.this,"Please enter your name.",Toast.LENGTH_SHORT);
                name_check.show();
            }
            else if(!emailstr.contains("@"))
            {
                Toast email_check = Toast.makeText(SignupActivity.this,"Invalid email address.",Toast.LENGTH_SHORT);
                email_check.show();
            }
            else if(unamestr.length()<8)
            {
                Toast uname_check = Toast.makeText(SignupActivity.this,"User name length must be more than 8.",Toast.LENGTH_SHORT);
                uname_check.show();
            }
            else if(pass1str.length()<8)
            {
                Toast pass_check = Toast.makeText(SignupActivity.this,"Password length must be more than 8",Toast.LENGTH_SHORT);
                pass_check.show();
            }
            else if(pass1str.equals(unamestr))
            {
                Toast passw_check = Toast.makeText(SignupActivity.this,"Password can't be same as username",Toast.LENGTH_SHORT);
                passw_check.show();
            }
            else if(!pass1str.equals(pass2str))
            {
                //poptp message
                Toast pass_check = Toast.makeText(SignupActivity.this,"Passwords don't match.",Toast.LENGTH_SHORT);
                pass_check.show();
            }
            else
            {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Members");
                query.whereEqualTo("MemberID", unamestr);
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> list, ParseException e) {
                        if (e == null)
                        {
                            if (list.size() > 0)
                            {
                                Toast username_check = Toast.makeText(SignupActivity.this, "Username already exist", Toast.LENGTH_SHORT);
                                username_check.show();
                            }
                            else
                            {
                                EditText name = (EditText)findViewById(R.id.TFname);
                                EditText email = (EditText)findViewById(R.id.TFemail);
                                EditText uname = (EditText)findViewById(R.id.TFusername);
                                EditText pass1 = (EditText)findViewById(R.id.TFpassword);

                                String namestr = name.getText().toString();
                                String emailstr = email.getText().toString();
                                String unamestr = uname.getText().toString();
                                String pass1str = pass1.getText().toString();
                                Intent i = new Intent(SignupActivity.this, StudentSignUpActivity.class);
                                i.putExtra("username", unamestr);
                                i.putExtra("email", emailstr);
                                i.putExtra("name", namestr);
                                i.putExtra("password", pass1str);
                                startActivity(i);
                            }

                        }
                        else
                        {
                            Toast Signup_check = Toast.makeText(SignupActivity.this, "Sign fail.", Toast.LENGTH_SHORT);
                            Signup_check.show();
                        }
                    }
                });
            }
        }
    }

    public void TutorClick(View v)
    {
        if(v.getId()==R.id.Btutorsignup)
        {
            EditText name = (EditText)findViewById(R.id.TFname);
            EditText email = (EditText)findViewById(R.id.TFemail);
            EditText uname = (EditText)findViewById(R.id.TFusername);
            EditText pass1 = (EditText)findViewById(R.id.TFpassword);
            EditText pass2 = (EditText)findViewById(R.id.TFpassword1);

            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String unamestr = uname.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();

            if(namestr.isEmpty())
            {
                Toast name_check = Toast.makeText(SignupActivity.this,"Please enter your name.",Toast.LENGTH_SHORT);
                name_check.show();
            }
            else if(!emailstr.contains("@"))
            {
                Toast email_check = Toast.makeText(SignupActivity.this,"Invalid email address.",Toast.LENGTH_SHORT);
                email_check.show();
            }
            else if(unamestr.length()<8)
            {
                Toast uname_check = Toast.makeText(SignupActivity.this,"User name length must be more than 8.",Toast.LENGTH_SHORT);
                uname_check.show();
            }
            else if(pass1str.length()<8)
            {
                Toast pass_check = Toast.makeText(SignupActivity.this,"Password length must be more than 8",Toast.LENGTH_SHORT);
                pass_check.show();
            }
            else if(pass1str.equals(unamestr))
            {
                Toast passw_check = Toast.makeText(SignupActivity.this,"Password can't be same as username",Toast.LENGTH_SHORT);
                passw_check.show();
            }
            else if(!pass1str.equals(pass2str))
            {
                //poptp message
                Toast pass_check = Toast.makeText(SignupActivity.this,"Passwords don't match.",Toast.LENGTH_SHORT);
                pass_check.show();
            }
            else
            {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Members");
                query.whereEqualTo("MemberID", unamestr);
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> list, ParseException e) {
                        if (e == null)
                        {
                            if (list.size() > 0)
                            {
                                Toast username_check = Toast.makeText(SignupActivity.this, "Username already exist", Toast.LENGTH_SHORT);
                                username_check.show();
                            }
                            else
                            {
                                EditText name = (EditText)findViewById(R.id.TFname);
                                EditText email = (EditText)findViewById(R.id.TFemail);
                                EditText uname = (EditText)findViewById(R.id.TFusername);
                                EditText pass1 = (EditText)findViewById(R.id.TFpassword);

                                String namestr = name.getText().toString();
                                String emailstr = email.getText().toString();
                                String unamestr = uname.getText().toString();
                                String pass1str = pass1.getText().toString();
                                Intent i = new Intent(SignupActivity.this, TutorSignUpActivity.class);
                                i.putExtra("username", unamestr);
                                i.putExtra("email", emailstr);
                                i.putExtra("name", namestr);
                                i.putExtra("password", pass1str);
                                startActivity(i);
                            }

                        }
                        else
                        {
                            Toast Signup_check = Toast.makeText(SignupActivity.this, "Sign fail.", Toast.LENGTH_SHORT);
                            Signup_check.show();
                        }
                    }
                });
            }
        }

    }

}
