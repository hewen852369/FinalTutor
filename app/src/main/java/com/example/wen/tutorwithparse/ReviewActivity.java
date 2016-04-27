package com.example.wen.tutorwithparse;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.List;

public class ReviewActivity extends AppCompatActivity {
    RatingBar ratingBar;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP);
    }



    public void submitReview(View view) {

        if (view.getId()==R.id.submitReview){
            RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
            EditText comment = (EditText) findViewById(R.id.editText2);
            ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
                public void onRatingChanged(RatingBar ratingBar, float rating,
                                            boolean fromUser) {

                    Toast.makeText(getApplicationContext(), "Your Selected Ratings  : " + String.valueOf(rating), Toast.LENGTH_LONG).show();

                }
            });
        /*btn.setOnClickListener(new View.OnClickListener() {


            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                float rating = ratingBar.getRating();
                Toast.makeText(getApplicationContext(), "Your Selected Ratings  : " + String.valueOf(rating), Toast.LENGTH_LONG).show();
            }
        });*/
            String commentstr = comment.getText().toString();
            ratingBar = (RatingBar) findViewById(R.id.ratingBar);
            float rating = ratingBar.getRating();
            //System.out.println(rating);
            if(commentstr.isEmpty())
            {
                Toast comment_check = Toast.makeText(ReviewActivity.this,"Please enter comment.",Toast.LENGTH_SHORT);
                comment_check.show();
            }
            else {
                String tutorName = getIntent().getStringExtra("tutorName");
                String username= getIntent().getStringExtra("Username");
                String tutorPhone=getIntent().getStringExtra("tutorPhone");
                ParseObject user = new ParseObject("Reviews");
//                Log.d("username: ", username);
                user.put("tutorName", tutorName);
                user.put("Comment",commentstr);
                user.put("Stars", rating);
                user.put("studentMemberID", username);
                user.put("tutorPhone", tutorPhone);
                user.saveInBackground();
                finish();
            }
        }


    }
}
