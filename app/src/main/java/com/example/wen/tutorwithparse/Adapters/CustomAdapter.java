package com.example.wen.tutorwithparse.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.wen.tutorwithparse.R;

/**
 * Created by Benson on 4/14/2016.
 */
public class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter(Context context, String[] itemName) {
        super(context, R.layout.custom_row, itemName);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.custom_row, parent, false); //set it to false if not to inflate to parent

        String singleItem = getItem(position);
        TextView myText = (TextView) customView.findViewById(R.id.myText);
        //ImageView image = (ImageView) customView.findViewById(R.id.ic_person_black_48dp);

        myText.setText(singleItem);     //sets the text dynamically
        //image.setImageResource(R.drawable.ic_person_black_48dp);  //same picture for all...

        return customView;
    }
}
