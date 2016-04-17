package com.example.wen.tutorwithparse.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wen.tutorwithparse.Models.CategoriesRowItem;
import com.example.wen.tutorwithparse.R;

import java.util.List;

/**
 * Created by Benson on 4/15/2016.
 */
public class CategoryAdapter extends ArrayAdapter<CategoriesRowItem> {

    private Context context;

    public CategoryAdapter(Context context, List<CategoriesRowItem> objects) {
        super(context, R.layout.categories_row, objects);
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView categoriesPic;
        TextView categoriesName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        CategoriesRowItem categoriesRowItem = getItem(position);

        LayoutInflater myInflater = LayoutInflater.from(getContext());
        if (convertView == null) {
            convertView = myInflater.inflate(R.layout.categories_row, parent, false);
            holder = new ViewHolder();
            holder.categoriesName = (TextView) convertView.findViewById(R.id.tv_categoires_name);
            holder.categoriesPic = (ImageView) convertView.findViewById(R.id.iv_categories_pic);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.categoriesName.setText(categoriesRowItem.getTitle());
        holder.categoriesPic.setImageResource(categoriesRowItem.getImageId());

        return convertView;
    }
}

