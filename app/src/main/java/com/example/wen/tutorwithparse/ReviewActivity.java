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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wen.tutorwithparse.Adapters.ReviewAdapter;
import com.example.wen.tutorwithparse.Models.ReviewModel;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReviewActivity extends AppCompatActivity {
    String[] items;
    ArrayList<String> listItems = new ArrayList<>();
    ArrayList<ParseObject> ArrObj;
    ParseObject p;
    private ArrayList<ReviewModel> reviewList;

    String comment;
    EditText commentText;
    RatingBar ratingBar;
    Button btn;
    float ratingStars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        commentText = (EditText) findViewById(R.id.editText2);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP);
        ArrObj = new ArrayList<ParseObject>();
        String username = getIntent().getStringExtra("Username");
        final String tutorPhone = getIntent().getStringExtra("tutorPhone");
        ParseQuery<ParseObject> query = new ParseQuery("Reviews");
        query.whereEqualTo("studentMemberID", username);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> userList, ParseException e) {
                reviewList = new ArrayList<>();

                if (e == null) {
                    if (userList.size() != 0) {
                        ParseObject review;
                        for (int i = 0; i < userList.size(); i++) {
                            review = userList.get(i);
                            /*reviewList.add(new ReviewModel(review.getString("studentMemberID"),
                                    review.getString("Comment"),
                                    review.getInt("Stars")));*/
                            if (Objects.equals(tutorPhone, review.getString("tutorPhone"))) {
                                //Intent i = new Intent(ReviewActivity.this, TutorDetailsActivity.class);
                                comment = review.getString("Comment");
                                ratingStars = review.getInt("Stars");
                                commentText.setText(comment, TextView.BufferType.EDITABLE);
                                ratingBar.setRating(ratingStars);
                                review.deleteInBackground();
                            }


                        }
                    }
                }
                else {
                    Log.d("score", "Error: " + e.getMessage());
                }


            }

        });
    }



    public void submitReview(View view) {

        if (view.getId()==R.id.submitReview){
            ratingBar = (RatingBar) findViewById(R.id.ratingBar);
            commentText = (EditText) findViewById(R.id.editText2);
            ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
                public void onRatingChanged(RatingBar ratingBar, float rating,
                                            boolean fromUser) {

                    Toast.makeText(getApplicationContext(), "Your Selected Ratings  : " + String.valueOf(rating), Toast.LENGTH_LONG).show();

                }
            });
        /*btn.setOnClickListener(new View.OnClickListener() {

                        }

            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                float rating = ratingBar.getRating();
                Toast.makeText(getApplicationContext(), "Your Selected Ratings  : " + String.valueOf(rating), Toast.LENGTH_LONG).show();
            }
        });*/
            String commentstr = commentText.getText().toString();
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
                username = getIntent().getStringExtra("Username");
                /*Intent i = new Intent(ReviewActivity.this, TutorDetailsActivity.class);                                                                                                                             Intent i = new Intent(TutorDetailsActivity.this, ReviewActivity.class);
                i.putExtra("tutorName", tutorName);
                i.putExtra("tutorPhone", tutorPhone);
                i.putExtra("Username", username);
                startActivity(i);*/
                finish();
            }
        }

                    }

    }

