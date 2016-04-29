package net.alorse.applistgrability.provider;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Response;

import static net.alorse.applistgrability.constanst.AppConstants.SERVER_URL;

/**
 * Created by alorse on 4/28/16.
 */
@Singleton
public class AppsProvider {
    @Inject
    HttpProvider httpProvider;

    Response data;
    public JSONObject loadData(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        httpProvider = new HttpProvider();
        try{
            data = httpProvider.get(SERVER_URL);
            JSONObject obj = new JSONObject(data.body().string()).getJSONObject("feed");
            return JSONCategories(obj);
        }catch(Exception e){
            Log.e("Error", e.toString());
            e.printStackTrace();
            return null;
        }
    }
    public JSONObject JSONCategories(JSONObject obj){
        JSONObject forCats = new JSONObject();
        try {
            JSONArray entry = obj.getJSONArray("entry");
            int length = entry.length();
            for (int i= 0; i< length;i++){
                JSONObject current = entry.getJSONObject(i).getJSONObject("category").getJSONObject("attributes");
                forCats.put(current.getString("term"), new JSONArray());
            }
            for (int i= 0; i< length;i++){
                JSONObject current = entry.getJSONObject(i).getJSONObject("category").getJSONObject("attributes");
                forCats.getJSONArray(current.getString("term")).put(entry.getJSONObject(i));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return forCats;
    }
}
