package net.alorse.applistgrability.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import net.alorse.applistgrability.R;

import org.json.JSONArray;

import static net.alorse.applistgrability.constanst.AppConstants.CATEGORY;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        try {
            JSONArray jsonObj = new JSONArray(getIntent().getStringExtra(CATEGORY));
            Log.e(getClass().getName(), jsonObj.toString());
        }catch (Exception e){}

    }
}
