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

/**
 * Created by Benson on 4/14/2016.
 */
public class TutorListAdapter extends ArrayAdapter<Tutor> {
    public TutorListAdapter(Context context, ArrayList<Tutor> itemName) {
        super(context, R.layout.tutor_list_row, itemName);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.tutor_list_row, parent, false); //set it to false if not to inflate to parent

        Tutor tutor = getItem(position);
        TextView myText = (TextView) customView.findViewById(R.id.tv_categoires_name);
        ImageView andyTheAndroid = (ImageView) customView.findViewById(R.id.tutor_pic);

        myText.setText(tutor.getName());     //sets the text dynamically
        andyTheAndroid.setImageResource(R.drawable.ic_person_black_48dp);  //same picture for all...

        return customView;
    }
}
