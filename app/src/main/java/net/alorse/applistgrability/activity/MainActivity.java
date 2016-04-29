package net.alorse.applistgrability.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;

import net.alorse.applistgrability.R;
import net.alorse.applistgrability.provider.AppsProvider;


import org.json.JSONObject;

import butterknife.InjectView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.first_text)
    TextView first_text;

    @InjectView(R.id.gridView)
    GridView gridView;

    AppsProvider appsProvider = new AppsProvider();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        JSONObject data = appsProvider.loadData();
        first_text.setText("Alfredo Ortegon");

    }
}
