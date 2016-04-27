package com.example.wen.tutorwithparse;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wen.tutorwithparse.Models.ReviewModel;
import com.example.wen.tutorwithparse.Models.Tutor;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;
import java.util.Objects;

public class TutorDetailsActivity extends AppCompatActivity implements Serializable {
    String tutorName;
    private float ratingstars;
    String userType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_details);

        Tutor tutor = (Tutor) getIntent().getSerializableExtra("Tutor");

        TextView tvName = (TextView) findViewById(R.id.TVname);
        TextView tvSubject = (TextView) findViewById(R.id.TVsubject);
        TextView tvAddress = (TextView) findViewById(R.id.tv_tutorAddress);
        TextView tvPrice = (TextView) findViewById(R.id.tv_tutorPrice);
        TextView tvNumStudent = (TextView) findViewById(R.id.TVnumStudents);
        TextView tvBio = (TextView) findViewById(R.id.tv_bio);

        tutorName=tvName.toString();

        ImageView andyTheAndroid = (ImageView) findViewById(R.id.tutor_pic);
        ImageButton imageCallButton = (ImageButton) findViewById(R.id.imageCallButton);
        ImageButton imageTextButton = (ImageButton) findViewById(R.id.imageTextButton);
        ImageButton imageEmailButton = (ImageButton) findViewById(R.id.imageEmailButton);
        ImageButton imageLocationButton = (ImageButton) findViewById(R.id.imageLocationButton);
        Button reviewButton = (Button) findViewById(R.id.Breview);
        Button viewReviewsButton = (Button) findViewById(R.id.viewReviews);
        final RatingBar ratingBar = (RatingBar) findViewById(R.id.tutorRatingBar);

        tvName.setText(tutor.getName());
        tvSubject.setText(tutor.getSubcategory());
        tvAddress.setText(tutor.getAddress());
        tvPrice.setText(tutor.priceAsAString());
        tvNumStudent.setText(String.valueOf(tutor.getNumOfStudents()));
        tvBio.setText(tutor.getMessage());

        final String tutorPhone = tutor.getPhoneNumber();
        final String eMailAddress = tutor.geteMail();
        final String tutorName = tutor.getName();
        final String address = tutor.getAddress();
        ratingstars=0;
        ParseQuery<ParseObject> query = new ParseQuery<>("Reviews");
        query.whereEqualTo("tutorPhone", tutorPhone);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> userList, ParseException e) {
                //reviewList = new ArrayList<>();

                if (e == null) {
                    if (userList.size() > 0) {
                        ParseObject review;

                        //ParseObject member;
                        for (int i = 0; i < userList.size(); i++) {
                            review = userList.get(i);

                            ratingstars = ratingstars + review.getInt("Stars");

                            //member = userList.get(i).getParseObject("members");
                        }
                        ratingstars = ratingstars / userList.size();
                    }
                    ratingBar.setRating(ratingstars);
                }
            }
        });

        imageCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(TutorDetailsActivity.this, "Phone", Toast.LENGTH_SHORT).show();
                placeCall(tutorPhone);
            }
        });

        imageTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(TutorDetailsActivity.this, "Text", Toast.LENGTH_SHORT).show();
                sendText(tutorPhone, tutorName);
            }
        });

        imageEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(TutorDetailsActivity.this, "Email", Toast.LENGTH_SHORT).show();
                sendEmail(eMailAddress, tutorName);
            }
        });

        imageLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(TutorDetailsActivity.this, "Email", Toast.LENGTH_SHORT).show();
                locateTutor(address);
            }
        });
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(TutorDetailsActivity.this, "Review", Toast.LENGTH_SHORT).show();
                String username = getIntent().getStringExtra("Username");
                ParseQuery<ParseObject> query = new ParseQuery("Members");
                query.whereEqualTo("MemberID", username);
                query.findInBackground(new FindCallback<ParseObject>() {
                                           @Override
                                           public void done(List<ParseObject> userList, ParseException e) {

                                               if (e == null) {
                                                   if (userList.size() != 0) {
                                                       ParseObject user;
                                                       for (int i = 0; i < userList.size(); i++) {
                                                           user = userList.get(0);
                                                           userType = user.getString("UserType");
                                                       }
                                                   }
                                               } else {
                                                   Log.d("score", "Error: " + e.getMessage());
                                               }
                                           }
                                       }

                );
                if (Objects.equals("tutor", userType))
                {
                    Toast tutor = Toast.makeText(TutorDetailsActivity.this,"Tutors cannot post reviews",Toast.LENGTH_SHORT);
                    tutor.show();
                }
                else {
                    Intent review = new Intent(TutorDetailsActivity.this, ReviewActivity.class);
                    review.putExtra("tutorName", tutorName);
                    review.putExtra("tutorPhone", tutorPhone);
                    review.putExtra("Username", username);
                    startActivity(review);
                }
            }
        });

        viewReviewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(TutorDetailsActivity.this, "View Reviews", Toast.LENGTH_SHORT).show();
                String username = getIntent().getStringExtra("Username");
                Intent i = new Intent(TutorDetailsActivity.this, ViewReviewsActivity.class);
                i.putExtra("tutorName", tutorName);
                i.putExtra("tutorPhone", tutorPhone);
                i.putExtra("Username", username);
                startActivity(i);
            }
        });

        andyTheAndroid.setImageResource(R.drawable.ic_person_black_48dp);
    }

    public void placeCall(String phone){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phone));
        try {
            startActivity(callIntent);
        }
        catch(SecurityException e){
            System.out.println(e);
        }
    }

    public void sendText(String phone, String name){
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse("sms:" + phone));
        sendIntent.putExtra("address", new String(phone));
        sendIntent.putExtra("sms_body", "Hey " + name + ", I found you on the tutoring app!");
        sendIntent.setType("vnd.android-dir/mms-sms");

        startActivity(sendIntent);

    }

    public void sendEmail(String eMailAddress, String name){
        Intent eMail = new Intent(Intent.ACTION_SEND);
        //eMail.putExtra(Intent.EXTRA_EMAIL, eMailAddress);
        eMail.putExtra(Intent.EXTRA_EMAIL, new String[]{eMailAddress});
        eMail.putExtra(Intent.EXTRA_SUBJECT, "Tutoring Inquiry");
        eMail.putExtra(Intent.EXTRA_TEXT, "Hey " +name+ ", I found you on the tutoring app!");
        eMail.setType("message/rfc822");
        startActivity(Intent.createChooser(eMail, "Choose an Email client: "));
    }

    public void locateTutor(String address) {
        address.replace(" ", "+");
        Log.d("address", address);
        String map = "http://maps.google.co.in/maps?q=" + address;
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
        startActivity(i);
    }
}
