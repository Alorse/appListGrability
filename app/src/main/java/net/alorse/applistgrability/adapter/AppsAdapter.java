package net.alorse.applistgrability.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.alorse.applistgrability.R;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by alorse on 4/29/16.
 */
public class AppsAdapter extends BaseAdapter {

    private JSONObject app = new JSONObject();
    private JSONArray apps = new JSONArray();

    public AppsAdapter(JSONArray data){
        try{
            this.apps = data;
            int length = apps.length();
            for (int i=0; i<length;i++){
                String appName = apps.getJSONObject(i).getJSONObject("im:name").getString("label");
                app.put(appName, apps.getJSONObject(i));
            }
        }catch (Exception e){}

    }
    @Override
    public int getCount() {
        return apps.length();
    }

    @Override
    public JSONObject getItem(int position) {
        try {
            return apps.getJSONObject(position);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_app, parent, false);
        }
        try {
            String appName = getItem(position).getJSONObject("im:name").getString("label");
            ((TextView) convertView.findViewById(R.id.appName)).setText(appName);

            String companyName = getItem(position).getJSONObject("rights").getString("label");
            ((TextView) convertView.findViewById(R.id.companyName)).setText(companyName);

            ImageView mImageView = (ImageView) convertView.findViewById(R.id.imageApp);
            JSONArray images  = getItem(position).getJSONArray("im:image");
            JSONObject bigImage = (JSONObject) images.get(images.length()-1);
            Picasso.with(parent.getContext()).load(bigImage.getString("label")).into(mImageView);
        }catch (Exception e){
            Log.e("getView AppsAdapter", e.toString());
        }

        return convertView;
    }
}
