package com.mcompany.coupan.ui.application;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;

import com.mcompany.coupan.data.network.firebase.FirebaseManager;


public class McompApplication extends Application {
    private static volatile McompApplication mInstance;
    private Context mContext;

    // Internet connection
    private static ConnectivityManager sManager = null;
    private static WifiManager sWifiManager = null;

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContext = getApplicationContext();
        FirebaseManager.initialize();
    }

    public synchronized static McompApplication getInstance() {
        if (mInstance == null) { //if there is no instance available... create new one
            synchronized (McompApplication.class) {
                if (mInstance == null) mInstance = new McompApplication();
            }
        }
        return mInstance;
    }


    public Context getContext() {
        return mContext;
    }

    public static ConnectivityManager getConnectivityManager() {
        if (null == sManager)
            sManager = (ConnectivityManager) getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        return sManager;
    }

    public static WifiManager getWifiManager() {
        if (null == sWifiManager)
            sWifiManager = (WifiManager) getInstance().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        return sWifiManager;
    }

}
