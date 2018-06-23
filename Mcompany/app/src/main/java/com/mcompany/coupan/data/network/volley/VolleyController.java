package com.mcompany.coupan.data.network.volley;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by manmohansingh on 13-06-2018.
 */

public class VolleyController {
    private static final String TAG = VolleyController.class.getSimpleName();
    private static final int REQ_TIMEOUT_MS_2G = 120000;
    private static final int MAX_RETRIES_2G= 2;

    private static volatile VolleyController sVolleyControllerInstance;
    private RequestQueue mRequestQueue;
    private Context mContext;

    private VolleyController(Context context) {
        mContext = context;
        if (sVolleyControllerInstance != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static synchronized VolleyController getInstance(Context context) {
        if (sVolleyControllerInstance == null) { //if there is no instance available... create new one
            synchronized (VolleyController.class) {
                if (sVolleyControllerInstance == null) sVolleyControllerInstance = new VolleyController(context);
            }
        }
        return sVolleyControllerInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        if (is2gNetwork()) {
            req.setRetryPolicy(new DefaultRetryPolicy(REQ_TIMEOUT_MS_2G,
                    MAX_RETRIES_2G, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        }
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    private boolean is2gNetwork() {
        ConnectivityManager conMgr = (ConnectivityManager) mContext.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo network = conMgr.getActiveNetworkInfo();

        // Check we're connected with a mobile network
        if (null != network && network.isConnectedOrConnecting() && network.getType() == 
                ConnectivityManager.TYPE_MOBILE) {

            // Check we're on GPRS or EDGE
            final int subType = network.getSubtype();
            return subType == TelephonyManager.NETWORK_TYPE_EDGE || subType == TelephonyManager
                    .NETWORK_TYPE_GPRS;
        }

        return false;
    }
}
