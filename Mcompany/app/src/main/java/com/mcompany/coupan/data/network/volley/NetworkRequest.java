package com.mcompany.coupan.data.network.volley;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mcompany.coupan.BuildConfig;
import com.mcompany.coupan.appcommon.constants.AuthConstants;
import com.mcompany.coupan.appcommon.constants.Constants;
import com.mcompany.coupan.appcommon.logger.AppLogger;
import com.mcompany.coupan.appcommon.utility.Utility;
import com.mcompany.coupan.dtos.EmptyDataModel;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by manmohansingh on 13-06-2017.
 * Volley adapter for JSON requests that will be parsed into Java objects by
 * Gson.
 */
public class NetworkRequest<T> extends Request<T> {
    private static final String TAG = NetworkRequest.class.getSimpleName();
    private final Class<T> mClass;
    private final NetworkVolleyListener<T> mListener;
    private final Gson mGson = new Gson();
    private final Map<String, String> mParams;
    private Context mContext;
    private String mTAG;
    private boolean mIsNeedAuthorization;

    /**
     * Make a POST request and return a parsed object from JSON.
     *
     * @param url      for request
     * @param tClass   Gson reflection class object
     * @param listener response listener
     */
    public NetworkRequest(Context context, int method, String url, Class<T> tClass, NetworkVolleyListener listener, Map<String, String> params,String tag,boolean isNeedAuthorization) {
        super(method, url, listener);
        mContext = context;
        mClass = tClass;
        mListener = listener;
        mParams = params;
        mTAG = tag;
        this.mIsNeedAuthorization = isNeedAuthorization;
    }


    @Override
    protected Map<String, String> getParams() {
        return mParams;
    }

    @Override
    public Map<String, String> getHeaders() {
        Map<String, String>  headers = null;
        if(mIsNeedAuthorization && !TextUtils.isEmpty(OauthManager.getInstance().getToken())){
            headers = new HashMap<String, String>(2);
            headers.put(AuthConstants.AUTHORIZATION, AuthConstants.BEARER + OauthManager.getInstance().getToken());
            headers.put(AuthConstants.USER_AGENT_KEY, AuthConstants.USER_AGENT_VALUE);
            if (BuildConfig.DEBUG)
                AppLogger.d(Utility.NETWORK_TAG, "Authenticated call with TOKEN :: " +  OauthManager.getInstance().getToken());
        }else{
            headers = new HashMap<String, String>(1);
            headers.put(AuthConstants.USER_AGENT_KEY, AuthConstants.USER_AGENT_VALUE);
        }
        return headers;
    }


    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = null;
            EmptyDataModel emptyDataModel = null;
            json = new String(response.data, "UTF-8");
            if((response.statusCode == NetworkHelper.HTTP_STATUS_CODE_OK || response.statusCode == NetworkHelper.HTTP_STATUS_CODE_CREATED || response.statusCode == NetworkHelper.HTTP_STATUS_CODE_ACCEPTED )&& Constants.EMPTY_STRING.equalsIgnoreCase(json))
            {
                emptyDataModel = new EmptyDataModel();
                emptyDataModel.setStatusCode(response.statusCode);
                emptyDataModel.setResponseObject(response.data);
            }
            if (BuildConfig.DEBUG)
                AppLogger.d(Utility.NETWORK_TAG, "Response :: " + response.statusCode == null ? null :
                        new String(response.data));
            if(Constants.EMPTY_STRING.equalsIgnoreCase(json)) {
                return (Response<T>) Response.success(emptyDataModel, HttpHeaderParser.parseCacheHeaders(response));
            }
            else
            {
                return Response.success(mGson.fromJson(json, mClass), HttpHeaderParser
                        .parseCacheHeaders(response));
            }
        } catch (UnsupportedEncodingException ex) {
            if (BuildConfig.DEBUG) AppLogger.d(Utility.NETWORK_TAG, ex.getMessage());
            return Response.error(new ParseError(ex));
        } catch (JsonSyntaxException ex) {
            if (BuildConfig.DEBUG) AppLogger.d(Utility.NETWORK_TAG, ex.getMessage());
            return Response.error(new ParseError(ex));
        }
    }



    public String getmTAG() {
        return mTAG;
    }

    public void setmTAG(String mTAG) {
        this.mTAG = mTAG;
    }

    



}
