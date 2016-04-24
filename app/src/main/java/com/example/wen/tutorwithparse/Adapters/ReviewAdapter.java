package com.example.wen.tutorwithparse.Adapters;

/**
 * Created by Priyanka on 4/21/2016.
 */
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.wen.tutorwithparse.Models.ReviewModel;
import com.example.wen.tutorwithparse.R;

import java.util.ArrayList;
import android.content.Intent;

public class ReviewAdapter extends ArrayAdapter<ReviewModel> {
    public ReviewAdapter(Context context, ArrayList<ReviewModel> itemName) {
        super(context, R.layout.reviews_row, itemName);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.reviews_row, parent, false); //set it to false if not to inflate to parent

        ReviewModel review = getItem(position);
        TextView Tname = (TextView) customView.findViewById(R.id.Tname);
        TextView comment = (TextView) customView.findViewById(R.id.commenttxt);
        RatingBar ratingBar = (RatingBar) customView.findViewById(R.id.ratingBar2);

        Tname.setText(review.getName());
        comment.setText(review.getComment());
        ratingBar.setRating(review.getRating());
        //andyTheAndroid.setImageResource(R.drawable.ic_person_black_48dp);  //same picture for all...
        /*float numStars = getIntent().getFloatExtra("numStars");
        ratingBar.setRating(numStars);
        */
        return customView;
    }
}