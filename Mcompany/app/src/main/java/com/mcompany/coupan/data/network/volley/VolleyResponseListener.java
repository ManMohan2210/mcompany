package com.mcompany.coupan.data.network.volley;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mcompany.coupan.BuildConfig;
import com.mcompany.coupan.R;
import com.mcompany.coupan.appcommon.constants.Constants;
import com.mcompany.coupan.appcommon.logger.AppLogger;
import com.mcompany.coupan.appcommon.utility.Utility;
import com.mcompany.coupan.dtos.EachErrorModel;
import com.mcompany.coupan.dtos.ErrorModel;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by manmohansingh on 13-06-2017.
 */

public class VolleyResponseListener implements NetworkVolleyListener.onUpdateListener, Response.ErrorListener {
    static final String TAG = VolleyResponseListener.class.getSimpleName();
    static final int HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR = 500;
    static final int HTTP_STATUS_CODE_BAD_REQUEST = 400;
    static final int HTTP_STATUS_CODE_INVALID_AUTH = 401;
    static final int HTTP_STATUS_CODE_BAD_AUTH = 403;
    /**
     * The below queue will hold all the request which will come when token is getting refreshed
     */
    private static final Map pendingRequests = Collections.synchronizedMap(new HashMap<String, Request>());
    private static AtomicBoolean isTokenFetching = new AtomicBoolean(false);
    public NetworkListener listener;
    private Context mContext;
    private Request request;

    public VolleyResponseListener(Context context, NetworkListener listener) {
        this.listener = listener;
        this.mContext = context;
    }


    /**
     * Callback method called after Network Operation finish
     *
     * @param response
     * @param reqType
     */
    @Override
    public void onSuccess(Object response, int reqType) {
        if (BuildConfig.DEBUG)
            AppLogger.d(Utility.NETWORK_TAG, "Success request code:: " +  reqType);
        listener.onSuccess(response, reqType);
    }

    @Override
    public void onError(VolleyError error, int reqType) {
        handleErrorResponse(error, reqType);
    }


    /**
     * Callback method that an error has been occurred with the
     * provided error code and optional user-readable message.
     *
     * @param error
     */
    @Override
    public void onErrorResponse(VolleyError error) {
        handleErrorResponse(error, NetworkRequestCode.JSON_ERROE_REQUEST_CODE);
    }

    private void handleErrorResponse(VolleyError error, int reqType) {
        ErrorModel errorResponse = null;
        if (error.networkResponse != null) {
            final int errorStatusCode = error.networkResponse.statusCode;
            if (errorStatusCode == HTTP_STATUS_CODE_BAD_REQUEST || errorStatusCode ==
                    HTTP_STATUS_CODE_INVALID_AUTH || errorStatusCode ==
                    HTTP_STATUS_CODE_BAD_AUTH) {
                if (BuildConfig.DEBUG)
                    AppLogger.d(Utility.NETWORK_TAG, "Request code::  "+reqType+"  STATUS CODE :: " + errorStatusCode +" ");
                NetworkResponse response = error.networkResponse;

                try {
                    String json = new String(
                            response.data, HttpHeaderParser.parseCharset(response.headers));
                    if (BuildConfig.DEBUG)
                        AppLogger.d(Utility.NETWORK_TAG, "Request code::  "+reqType+"  STATUS CODE :: " + errorStatusCode +"Error Json:: " +  json);
                    errorResponse = new Gson().fromJson(json, ErrorModel.class);
                } catch (UnsupportedEncodingException e) {
                    if (BuildConfig.DEBUG)
                        AppLogger.d(Utility.NETWORK_TAG, "Request code::  "+reqType+"  STATUS CODE :: " + errorStatusCode +" "+e.getMessage());
                } catch (JsonSyntaxException jsonSyntaxException) {
                    if (BuildConfig.DEBUG)
                        AppLogger.d(Utility.NETWORK_TAG, "Request code::  "+reqType+"  STATUS CODE :: " + errorStatusCode +" "+jsonSyntaxException.getMessage());
                } catch (Exception e) {
                    if (BuildConfig.DEBUG)
                        AppLogger.d(Utility.NETWORK_TAG, "Request code::  "+reqType+"  STATUS CODE :: " + errorStatusCode +" "+e.getMessage());
                }

                // handle expired authtoken
                if (errorStatusCode == HTTP_STATUS_CODE_INVALID_AUTH && (reqType == NetworkRequestCode.JSON_ERROE_REQUEST_CODE || reqType != NetworkRequestCode.POST_REFRESH_TOKEN)) {
                    String key = request.getUrl()+request.getMethod()+request.getTag() + reqType;
                    boolean isTrigringForUniqueEntry = pendingRequests.containsKey(key);
                    if (!isTrigringForUniqueEntry) {
                        pendingRequests.put(key, request);
                        if (BuildConfig.DEBUG)
                            AppLogger.d(Utility.NETWORK_TAG, "Request code::  "+reqType +" error code "+ errorStatusCode +" Requestes in Queue "+request.getUrl());
                    }
                    if (BuildConfig.DEBUG)
                        AppLogger.d(Utility.NETWORK_TAG, "isTokenFetching ::  "+isTokenFetching.get());

                    if (!isTokenFetching.get()) {
                        isTokenFetching.set(true);
                    }
                } else {
                    if (reqType == NetworkRequestCode.POST_REFRESH_TOKEN) {
                        cancelPendingRequestAndSendLogoutBroadcast(reqType, errorStatusCode);
                    } else {
                        handleError(reqType, errorResponse);
                    }
                }

            } else {
                if (NetworkRequestCode.POST_REFRESH_TOKEN == reqType && HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR == errorStatusCode) {
                    cancelPendingRequestAndSendLogoutBroadcast(reqType, errorStatusCode);
                } else {
                    handleError(reqType, errorResponse);
                }
            }
        } else {
            if (BuildConfig.DEBUG)
                AppLogger.d(Utility.NETWORK_TAG, "Request code::  "+reqType +"Error response Null ");
            handleError(reqType, errorResponse);
        }
    }

    private void cancelPendingRequestAndSendLogoutBroadcast(int reqType, int errorStatusCode) {
        cancelAllPendingRequests();
        isTokenFetching.set(false);
        mContext.sendBroadcast(new Intent(Constants.SESSION_TIME_OUT_RECEIVER));
        if (BuildConfig.DEBUG)
            AppLogger.d(Utility.NETWORK_TAG, "Request code::  "+reqType +" error code "+ errorStatusCode +"LOG OUT POPUP 3");
    }

    private void cancelAllPendingRequests() {
        Iterator it = pendingRequests.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Request networkRequest = (Request) pair.getValue();
            if (BuildConfig.DEBUG)
                AppLogger.d(Utility.NETWORK_TAG, "Request Cancel for retrying  :: "+networkRequest.getUrl());
            networkRequest.cancel();
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    private void handleError(int reqType, ErrorModel errorResponse) {
        if (errorResponse == null) {
            errorResponse = getErrorModel(mContext.getString(R.string.mcom_err_02_server));
        }
        listener.onError(errorResponse, reqType);
        if (BuildConfig.DEBUG)
            AppLogger.d(Utility.NETWORK_TAG, "Request code::  "+reqType +"  handleError Normally ");
    }


    public static void retryPendingRequest(Context context,int reqType,NetworkListener  listener) {
        Iterator it = pendingRequests.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Request networkRequest = (Request) pair.getValue();
            if (BuildConfig.DEBUG)
                AppLogger.d(Utility.NETWORK_TAG, "Request for retrying  :: "+networkRequest.getUrl());
            if(networkRequest.getRetryPolicy().getCurrentRetryCount() < 4) {
                VolleyController.getInstance(context).addToRequestQueue(networkRequest, (String) networkRequest.getTag());
            }else {
                networkRequest.cancel();
                ErrorModel errorResponse = getErrorModel(context.getString(R.string.mcom_err_02_server));
                listener.onError(errorResponse,reqType);
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    @NonNull
    public static ErrorModel getErrorModel(String response) {
        EachErrorModel eachError = new EachErrorModel();
        eachError.setMessage(response);
        ErrorModel errors = new ErrorModel();
        List<EachErrorModel> errorList = new ArrayList<>(1);
        errorList.add(eachError);
        errors.setErrors(errorList);
        return errors;
    }

    /**
     * @param context
     * @param response Response got in error model
     * @return
     */
    public static String getErrorMessage(Context context, ErrorModel response) {
        String error = null;
        if (response != null && !Utility.isCollectionNullOrEmpty(response.getErrors())) {
            EachErrorModel eachError = response.getErrors().get(0);
            error = formatError(context, eachError);
        }
        return error;
    }

    /**
     * To set different errorMessage based on error type
     *
     * @param context
     * @param eachError
     * @return String
     */
    private static String formatError(Context context, EachErrorModel eachError) {
        String errorMessage = eachError.getMessage();
        if (eachError.getType() != null) {
            if (Constants.ERR_INVALID_TOKEN.equals(eachError.getType())) {
                errorMessage = context.getString(R.string.mcom_err_05_login_again);
            } else if (Constants.ERR_INVALID_GRANT.equals(eachError.getType())) {
                errorMessage = context.getString(R.string.mcom_err_06_invalid_credential);
            } else if (Constants.ERR_USER_NOT_REG.equals(eachError.getType())) {
                errorMessage = context.getString(R.string.mcom_err_07_user_not_reg);
            } else if (Constants.ERR_DUPLICATE_UID.equals(eachError.getType())) {
                errorMessage = context.getString(R.string.mcom_err_08_username_exists);
            } else if (Constants.ERR_PASS_MISS_MATCH.equals(eachError.getType())) {
                errorMessage = context.getString(R.string.mcom_err_09_incorrect_password);
            } else if (Constants.ERR_NO_CHECK_CART.equals(eachError.getType())) {
                errorMessage = context.getString(R.string.mcom_err_10_recheck_cart_items);
            }
        }
        return errorMessage;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
