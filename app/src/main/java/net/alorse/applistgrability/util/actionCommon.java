package net.alorse.applistgrability.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by alorse on 4/29/16.
 */
public class actionCommon {
    public <T> boolean isOnline(Context cls) {
        ConnectivityManager cm = (ConnectivityManager) cls.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }

        return false;
    }
}
