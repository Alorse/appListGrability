package net.alorse.applistgrability.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import net.alorse.applistgrability.R;
import net.alorse.applistgrability.adapter.AppsAdapter;

import org.json.JSONArray;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static net.alorse.applistgrability.constanst.AppConstants.CATEGORY;

public class CategoryActivity extends AppCompatActivity {

    @InjectView(R.id.gridApps)
    GridView gridApps;

    JSONArray Apps;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.inject(this);
        setTitle(getIntent().getStringExtra("CAT_NAME"));
        try {
            Apps = new JSONArray(getIntent().getStringExtra(CATEGORY));
            gridApps.setAdapter(new AppsAdapter(Apps));
        }catch (Exception e){}
    }
}
