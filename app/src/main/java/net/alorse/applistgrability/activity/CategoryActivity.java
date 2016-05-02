package net.alorse.applistgrability.activity;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.alorse.applistgrability.R;
import net.alorse.applistgrability.adapter.AppsAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static net.alorse.applistgrability.constanst.AppConstants.CATEGORY;

public class CategoryActivity extends AppCompatActivity {

    @InjectView(R.id.gridApps)
    GridView gridApps;
    AlertDialog.Builder builder;
    JSONArray Apps;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        if(getResources().getBoolean(R.bool.portrait_only)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        ButterKnife.inject(this);
        setTitle(getIntent().getStringExtra("CAT_NAME"));
        try {
            Apps = new JSONArray(getIntent().getStringExtra(CATEGORY));
            gridApps.setAdapter(new AppsAdapter(Apps));
            listenerAppTap();
            Log.e("NumColums", getResources().getInteger(R.integer.num_columns) + " _ " + gridApps.getNumColumns());
        }catch (Exception e){}
    }

    private void listenerAppTap() {
        builder = new AlertDialog.Builder(this);
        gridApps.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id){
                onCreateDialog(parent.getItemAtPosition(pos).toString());
            }
        });
    }

    public void onCreateDialog(String data) {
        try {
            JSONObject app = new JSONObject(data);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog_resume_app, null);

            TextView appName = (TextView) dialogView.findViewById(R.id.appName);
            appName.setText(app.getJSONObject("im:name").getString("label"));

            TextView summary = (TextView) dialogView.findViewById(R.id.summary);
            summary.setMovementMethod(new ScrollingMovementMethod());
            summary.setText(app.getJSONObject("summary").getString("label"));

            TextView rights = (TextView) dialogView.findViewById(R.id.rights);
            rights.setText(app.getJSONObject("rights").getString("label"));

            ImageView mImageView = (ImageView) dialogView.findViewById(R.id.imageApp);
            JSONArray images  = app.optJSONArray("im:image");
            JSONObject bigImage = (JSONObject) images.get(images.length()-1);
            Picasso.with(this).load(bigImage.getString("label")).into(mImageView);

            builder.setView(dialogView)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
            builder.create().show();
        }catch (Exception e){}
    }
}
