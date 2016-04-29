package net.alorse.applistgrability.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import net.alorse.applistgrability.R;
import net.alorse.applistgrability.adapter.CategoryGridAdapter;
import net.alorse.applistgrability.provider.AppsProvider;

import org.json.JSONObject;

import butterknife.InjectView;
import butterknife.ButterKnife;
import static net.alorse.applistgrability.constanst.AppConstants.CATEGORY;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.gridView)
    GridView gridView;

    AppsProvider appsProvider;
    JSONObject data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        appsProvider = new AppsProvider();
        data = appsProvider.loadData();
        gridView.setAdapter(new CategoryGridAdapter(data));
        listenergridView();
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
                    intent.putExtras(arguments);
                    startActivity(intent);
                } catch (Exception e){
                    Log.e("Error", e.toString());
                    e.printStackTrace();
                }

            }
        });
    }
}
