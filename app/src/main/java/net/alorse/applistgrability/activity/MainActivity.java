package net.alorse.applistgrability.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import net.alorse.applistgrability.R;
import net.alorse.applistgrability.provider.AppsProvider;


import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.first_text)
    TextView first_text;

    AppsProvider appsProvider = new AppsProvider();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        appsProvider.load2();
//        first_text.setText("Alfredo Ortegón");

    }
}
