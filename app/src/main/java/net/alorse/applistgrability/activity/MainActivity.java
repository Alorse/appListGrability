package net.alorse.applistgrability.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import net.alorse.applistgrability.R;
import net.alorse.applistgrability.adapter.CategoryGridAdapter;
import net.alorse.applistgrability.provider.AppsProvider;
import net.alorse.applistgrability.util.actionCommon;
import net.alorse.applistgrability.util.actionFiles;

import org.json.JSONObject;

import butterknife.InjectView;
import butterknife.ButterKnife;
import static net.alorse.applistgrability.constanst.AppConstants.CATEGORY;
import static net.alorse.applistgrability.constanst.AppConstants.FILENAME;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.gridView)
    GridView gridView;

    AppsProvider appsProvider;
    actionCommon common;
    actionFiles files;
    JSONObject data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getResources().getBoolean(R.bool.portrait_only)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        }

        ButterKnife.inject(this);
        setTitle(getResources().getString(R.string.categories));
        appsProvider = new AppsProvider();
        common = new actionCommon();
        files = new actionFiles();
        if(common.isOnline(this)){
            data = appsProvider.loadData();
            gridView.setAdapter(new CategoryGridAdapter(data));
            listenergridView();
            saveInfo();
        } else {
            Toast.makeText(getApplicationContext(), R.string.no_internet, Toast.LENGTH_LONG).show();
            if (files.exists(FILENAME, this)) {
                data = files.read(FILENAME, this);
                gridView.setAdapter(new CategoryGridAdapter(data));
                listenergridView();
            } else {
                Toast.makeText(getApplicationContext(), R.string.no_cache, Toast.LENGTH_LONG).show();
            }
        }
    }


    public void listenergridView(){
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id){
            try {
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                Bundle arguments = new Bundle();
                String CatName = parent.getItemAtPosition(pos).toString();
                arguments.putString(CATEGORY, data.getJSONArray(CatName).toString());
                arguments.putString("CAT_NAME", CatName);
                intent.putExtras(arguments);
                startActivity(intent);
            } catch (Exception e){
                Log.e("Error", e.toString());
                e.printStackTrace();
            }
            }
        });
    }
    public void saveInfo(){
        files.create(FILENAME, data, this);
    }
}
