package com.example.wen.tutorwithparse;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wen.tutorwithparse.Models.Tutor;

import java.io.Serializable;

public class TutorDetailsActivity extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_details);

        Tutor tutor = (Tutor) getIntent().getSerializableExtra("Tutor");

        TextView tvName = (TextView) findViewById(R.id.TVname);
        TextView tvSubject = (TextView) findViewById(R.id.TVsubject);
        TextView tvNumStudent = (TextView) findViewById(R.id.TVnumStudents);
        TextView tvBio = (TextView) findViewById(R.id.tv_bio);

        ImageView andyTheAndroid = (ImageView) findViewById(R.id.tutor_pic);
        ImageButton imageCallButton = (ImageButton) findViewById(R.id.imageCallButton);
        ImageButton imageTextButton = (ImageButton) findViewById(R.id.imageTextButton);
        ImageButton imageEmailButton = (ImageButton) findViewById(R.id.imageEmailButton);

        tvName.setText(tutor.getName());
        tvSubject.setText(tutor.getSubject());
        tvNumStudent.setText(String.valueOf(tutor.getNumOfStudents()));
        tvBio.setText(tutor.getMessage());


        final String tutorPhone = tutor.getPhoneNumber();
        final String eMailAddress = tutor.geteMail();
        final String tutorName = tutor.getName();


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

        andyTheAndroid.setImageResource(R.drawable.ic_person_black_48dp);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tutor_details, menu);
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



    public void placeCall(String phone){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phone));
        startActivity(callIntent);

    }

    public void sendText(String phone, String name){
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse("sms:" + phone));
        sendIntent.putExtra("sms_body", "Hey " + name + ", I found you on the tutoring app!");
        sendIntent.setType("vnd.android-dir/mms-sms");

        startActivity(sendIntent);

    }

    public void sendEmail(String eMailAddress, String name){
        Intent eMail = new Intent(Intent.ACTION_SEND);
        eMail.putExtra(Intent.EXTRA_EMAIL, eMailAddress);
        eMail.putExtra(Intent.EXTRA_SUBJECT, "Tutoring Inquiry");
        eMail.putExtra(Intent.EXTRA_TEXT, "Hey " +name+ ", I found you on the tutoring app!");
        eMail.setType("message/rfc822");
        startActivity(Intent.createChooser(eMail, "Choose an Email client: "));
    }
}
