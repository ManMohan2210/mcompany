package com.mcompany.coupan.ui;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiManager;

import com.mcompany.coupan.data.network.firebase.FirebaseManager;
import com.mcompany.coupan.dtos.AppConfigReqModel;


public class McompApplication extends Application {
    private static volatile McompApplication mInstance;
    private Context mContext;

    public static boolean sWasInBackground;
    private static AppConfigReqModel sAppConfigObj;

    // Internet connection
    private static ConnectivityManager sManager = null;
    private static WifiManager sWifiManager = null;
    private static Uri sNotificationUriData;

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

        /*NewRelic.withApplicationToken(
                getResources().getString(R.string.new_relic)
        ).start(this);
        NewRelic.setInteractionName(this.getClass().getSimpleName());*/


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

    public static Uri getsNotificationUriData() {
        return sNotificationUriData;
    }

    public static void setsNotificationUriData(Uri sNotificationUriData) {
        McompApplication.sNotificationUriData = sNotificationUriData;
    }


}
