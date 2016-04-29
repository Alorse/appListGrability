package net.alorse.applistgrability.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import net.alorse.applistgrability.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by alorse on 4/28/16.
 */
public class CategoryGridAdapter extends BaseAdapter {
    private List<String> categories;

    public CategoryGridAdapter(JSONObject c){

        categories = new ArrayList<>();
        Iterator<?> keys = c.keys();
        while( keys.hasNext() ) {
            String key = (String)keys.next();
            categories.add(key);
        }
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_category, parent, false);
        }
        String categoryName = getItem(position).toString();
        ((TextView) convertView.findViewById(R.id.categoryName)).setText(categoryName);
        return convertView;
    }
}
