package net.alorse.applistgrability.util;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * Created by alorse on 4/29/16.
 */
public class actionFiles {

    public void create(String filename, JSONObject jsonObject, Context context) {
        String string = jsonObject.toString();
        FileOutputStream file;

        try {
            file = context.openFileOutput(filename, Context.MODE_PRIVATE);
            file.write(string.getBytes());
            file.close();
            Log.e("Write", "Success");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Write", e.toString());
        }
    }

    public static boolean exists(String filename, Context context) {
//        String baseFolder;
//        // check if external storage is available
//        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            baseFolder = context.getExternalFilesDir(null).getAbsolutePath();
//            Log.e("External", baseFolder);
//        } else { // revert to using internal storage
//            baseFolder = context.getFilesDir().getAbsolutePath();
//            Log.e("Internal", baseFolder);
//        }
        File file = new File(context.getFilesDir().getAbsolutePath() + File.separator + filename);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public JSONObject read(String filename, Context ctx) {
        StringBuffer buffer = new StringBuffer();
        try{
            FileInputStream fis = ctx.openFileInput(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            if (fis!=null) {
                String Read;
                while ((Read = reader.readLine()) != null) {
                    buffer.append(Read + "\n" );
                    Log.e("File Line", Read);
                }
            }
            fis.close();
            return new JSONObject(buffer.toString());
        } catch (Exception e) {
            Log.e("Read", e.toString());
            e.printStackTrace();
            return null;
        }
    }
}
