package com.example.wen.tutorwithparse.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wen.tutorwithparse.Models.Tutor;
import com.example.wen.tutorwithparse.R;

import java.util.ArrayList;


public class SearchTutorAdapter extends ArrayAdapter<Tutor> {
    public SearchTutorAdapter(Context context, ArrayList<Tutor> itemName) {
        super(context, R.layout.search_tutor_details, itemName);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.search_tutor_details, parent, false); //set it to false if not to inflate to parent

        Tutor tutor = getItem(position);
        TextView myText = (TextView) customView.findViewById(R.id.TVname);
        TextView tvSubject = (TextView) customView.findViewById(R.id.TVsubject);
        ImageView andyTheAndroid = (ImageView) customView.findViewById(R.id.tutor_pic);

        myText.setText(tutor.getName());     //sets the text dynamically
        tvSubject.setText(tutor.getSubject());
        andyTheAndroid.setImageResource(R.drawable.ic_person_black_48dp);  //same picture for all...

        return customView;
    }
}
