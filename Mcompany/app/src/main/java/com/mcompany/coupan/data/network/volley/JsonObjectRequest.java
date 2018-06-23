package com.mcompany.coupan.data.network.volley;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mcompany.coupan.BuildConfig;
import com.mcompany.coupan.appcommon.constants.AuthConstants;
import com.mcompany.coupan.appcommon.constants.Constants;
import com.mcompany.coupan.appcommon.logger.AppLogger;
import com.mcompany.coupan.dtos.EmptyDataModel;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by manmohansingh on 13-06-2017.
 *
 * A request for retrieving a {@link JSONObject} response body at a given URL, allowing for an
 * optional {@link JSONObject} to be passed in as part of the request body.
 */
public class JsonObjectRequest<T> extends JsonRequest<T> {
    private static final String TAG = "JsonObjectRequest";
    private final Class<T> mClass;
    private final NetworkVolleyListener<T> mListener;
    private final Gson mGson = new Gson();
    private final Map<String, String> mParams;
    private Context mContext;
    private String mTAG;
    private boolean mIsNeedAuthorization;


    /**
     * Constructor which defaults to <code>GET</code> if <code>jsonRequest</code> is
     * <code>null</code>, <code>POST</code> otherwise.
     *
     * @see #JsonObjectRequest(Context, int, String, Class, JSONObject, NetworkVolleyListener, ErrorListener, Map,String,boolean)
     */

    public JsonObjectRequest(Context context, int method, String url, Class<T> tClass, JSONObject jsonRequest, NetworkVolleyListener listener, Response.ErrorListener errorListener, Map<String, String> params, String tag, boolean isNeedAuthorization) {
        super(method, url, (jsonRequest == null) ? null : jsonRequest.toString(), listener, errorListener);
        mContext = context;
        mClass = tClass;
        mListener = listener;
        mParams = params;
        this.mTAG = tag;
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
            if (response.statusCode == NetworkHelper.HTTP_STATUS_CODE_OK || response.statusCode == NetworkHelper.HTTP_STATUS_CODE_CREATED || response.statusCode == NetworkHelper.HTTP_STATUS_CODE_ACCEPTED && Constants.EMPTY_STRING.equalsIgnoreCase(json)) {
                emptyDataModel = new EmptyDataModel();
                emptyDataModel.setStatusCode(response.statusCode);
                emptyDataModel.setResponseObject(response.data);
            }
            if (BuildConfig.DEBUG)
                AppLogger.d(TAG, "Response :: " + response.statusCode == null ? null :
                        new String(response.data));
            if (Constants.EMPTY_STRING.equalsIgnoreCase(json)) {
                return (Response<T>) Response.success(emptyDataModel, HttpHeaderParser.parseCacheHeaders(response));
            } else {
                return Response.success(mGson.fromJson(json, mClass), HttpHeaderParser
                        .parseCacheHeaders(response));
            }
        } catch (UnsupportedEncodingException ex) {
            if (BuildConfig.DEBUG) AppLogger.d(TAG, ex.getMessage());
            return Response.error(new ParseError(ex));
        } catch (JsonSyntaxException ex) {
            if (BuildConfig.DEBUG) AppLogger.d(TAG, ex.getMessage());
            return Response.error(new ParseError(ex));
        }
    }
}
