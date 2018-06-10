package com.mcompany.coupan.data.network.volley;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;


/**
 * Created by manmohansingh on 13-06-2017.
 * Generic Volley Listener
 */
public class NetworkVolleyListener<T> implements Response.Listener<T>,
        Response.ErrorListener {
    private static final String TAG = NetworkVolleyListener.class.getSimpleName();

    private int mReqType;
    private Context mContext;
    private onUpdateListener mUpdateListener;

    public NetworkVolleyListener(Context context, onUpdateListener listener,
                                 int reqType) {
        mContext = context;
        mUpdateListener = listener;
        mReqType = reqType;
    }



    @Override
    public void onResponse(T response) {
        mUpdateListener.onSuccess(response, mReqType);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        mUpdateListener.onError(error, mReqType);


    }

    /**
     * Interface which is used to update UI after Network operation finish
     */
    public interface onUpdateListener<T> {
        /**
         * Callback method called after Network Operation finish
         *
         * @param response
         * @param reqType
         */
        public void onSuccess(T response, int reqType);

        public void onError(VolleyError volleyError, int reqType);
    }
}
