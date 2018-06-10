package com.mcompany.coupan.data.network.volley;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.BuildConfig;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.VolleyLog;
import com.mcompany.coupan.appcommon.logger.AppLogger;
import com.mcompany.coupan.appcommon.utility.Utility;
import com.mcompany.coupan.dtos.EachErrorModel;
import com.mcompany.coupan.dtos.ErrorModel;
import com.mcompany.coupan.dtos.FileUploadData;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 * Created by manmohansingh on 13-06-2017.
 * <p/>
 * Helper Class which makes volley requests
 */
public class NetworkHelper<T> {
    /**
     * The default number of retries
     */
    private static final int DEFAULT_MAX_RETRIES = 0;

    // general error status codes
    private static final int HTTP_REQ_TIMEOUT = 120000;
    public static final int HTTP_STATUS_CODE_OK = 200;
    public static final int HTTP_STATUS_CODE_CREATED = 201;
    public static final int HTTP_STATUS_CODE_ACCEPTED = 202;

    private static final boolean AUTHORIZATION_REQUIRED = true;
    private static final boolean AUTHORIZATION_NOT_REQUIRED = false;


    /**
     * @param context
     * @param listener   Listener is used to check the network call is successfully finished or not
     * @param methodType for the request type either it is get,post,delete...
     * @param url        of server end point for particular service
     * @param reqCode    int value used for differentiate a request from other requests
     * @param clazz      Gson reflection class object
     * @param params     values which need to be send with the request
     * @param TAG        String value which can be used for the cancelling the request
     * @param <T>        used for return type objects
     */
    private static <T> void commonRequest(final Context context, final NetworkListener listener, final int methodType, final String url, final int reqCode, final Class<T> clazz,
                                          final Map<String, String> params, final String TAG, boolean isNeedAuthorization) {

        if (BuildConfig.DEBUG)
        AppLogger.d(Utility.NETWORK_TAG, "RquestUrl :: " + url);
        VolleyResponseListener volleyResponseListener = new VolleyResponseListener(context, listener);
        NetworkRequest networkRequest = new NetworkRequest(context, methodType, url, clazz, new
                NetworkVolleyListener(context, volleyResponseListener, reqCode), params, TAG, isNeedAuthorization);
        networkRequest.setRetryPolicy(new DefaultRetryPolicy(HTTP_REQ_TIMEOUT, DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        volleyResponseListener.setRequest(networkRequest);
        VolleyController.getInstance(context).addToRequestQueue(networkRequest, TAG);
    }


    /**
     * A request for retrieving a response body at a given URL, allowing for an JSON OBJECT
     * to be passed in as part of the request body.
     *
     * @param context     under which this request is sent
     * @param methodType  for the request type either it is get,post,delete...
     * @param url         of server end point for particular service
     * @param clazz       Gson reflection class object
     * @param jsonRequest request parameter in JSON format
     * @param listener    Listener is used to check the network call is successfully finished or not
     * @param params      values which need to be send with the request
     * @param TAG         String value which can be used for the cancelling the request
     * @param <T>         used for return type objects
     */

    private static <T> void commonJsonObjectRequest(final Context context, final NetworkListener listener, int methodType, String url, int reqCode, Class<T> clazz, JSONObject jsonRequest, Map<String, String> params, final String TAG, boolean isNeedAuthorization) {
        if (BuildConfig.DEBUG)
            AppLogger.d(Utility.NETWORK_TAG, "JosnRquestUrl :: " + url);
        VolleyResponseListener volleyResponseListener = new VolleyResponseListener(context, listener);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(context, methodType, url, clazz, jsonRequest, new NetworkVolleyListener(context, volleyResponseListener, reqCode), volleyResponseListener, params, TAG, isNeedAuthorization);
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(HTTP_REQ_TIMEOUT, DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        volleyResponseListener.setRequest(jsonObjectRequest);
        VolleyController.getInstance(context).addToRequestQueue(jsonObjectRequest, TAG);


    }


    /**
     * A request for retrieving a response body at a given URL, allowing for an JSON OBJECT
     * to be passed in as part of the request body.
     *
     * @param context     under which this request is sent
     * @param methodType  for the request type either it is get,post,delete...
     * @param url         of server end point for particular service
     * @param clazz       Gson reflection class object
     * @param jsonRequest request parameter in JSON format
     * @param listener    Listener is used to check the network call is successfully finished or not
     * @param params      values which need to be send with the request
     * @param TAG         String value which can be used for the cancelling the request
     * @param <T>         used for return type objects
     */

    private static <T> void hashMapRequestForImage(final Context context, final NetworkListener listener, int methodType, String url, int reqCode, Class<T> clazz, JSONObject jsonRequest, Map<String, String> params, ArrayList<FileUploadData> fileUploadDataArrayList, final String TAG) {

        VolleyResponseListener volleyResponseListener = new VolleyResponseListener(context, listener);
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(context, methodType, url, clazz, new NetworkVolleyListener(context, volleyResponseListener, reqCode), params, fileUploadDataArrayList, TAG);

        volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(HTTP_REQ_TIMEOUT, DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        volleyResponseListener.setRequest(volleyMultipartRequest);
        VolleyController.getInstance(context).addToRequestQueue(volleyMultipartRequest, TAG);
    }


    public void cancelPendingRequests(Context context, Object tag) {
        VolleyController.getInstance(context).cancelPendingRequests(tag);
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
     * To make Authorize requests, Here its mandatory to send the access token in header
     *
     * @param context     the component under which this request is sent
     * @param listener    Listener is used to check the network call is successfully finished or not
     * @param methodType  Request type e.g.(GET,POST,PUT,DELETE,PATCH...)
     * @param url         server end point for particular service
     * @param reqCode     int value used for differentiate a request from other requests
     * @param clazz       Gson reflection class object
     * @param jsonRequest json object which the request required,if no JSON object required for any request send it null
     * @param params      values which need to be send with the request parameters. If request not expecting any parameters then send params as null
     * @param TAG         String value which can be used for the cancelling the request
     * @param <T>         used for return type objects
     */
    public static <T> void authorizedRequest(Context context, final NetworkListener listener, final int methodType, String url, int reqCode, Class<T> clazz, JSONObject jsonRequest, Map<String, String> params, final String TAG) {
        VolleyLog.DEBUG = false;
        if (jsonRequest == null) {
            commonRequest(context, listener, methodType, url, reqCode, clazz, params, TAG, AUTHORIZATION_REQUIRED);
        } else {
            commonJsonObjectRequest(context, listener, methodType, url, reqCode, clazz, jsonRequest, params, TAG, AUTHORIZATION_REQUIRED);

        }
    }


    /**
     * To make Authorize requests, Here its mandatory to send the access token in header
     *
     * @param context     the component under which this request is sent
     * @param listener    Listener is used to check the network call is successfully finished or not
     * @param methodType  Request type e.g.(GET,POST,PUT,DELETE,PATCH...)
     * @param url         server end point for particular service
     * @param reqCode     int value used for differentiate a request from other requests
     * @param clazz       Gson reflection class object
     * @param jsonRequest json object which the request required,if no JSON object required for any request send it null
     * @param params      values which need to be send with the request parameters. If request not expecting any parameters then send params as null
     * @param TAG         String value which can be used for the cancelling the request
     * @param <T>         used for return type objects
     */
    public static <T> void authorizedRequestForImage(Context context, final NetworkListener listener, final int methodType, String url, int reqCode, Class<T> clazz, JSONObject jsonRequest, Map<String, String> params, ArrayList<FileUploadData> fileUploadData, final String TAG) {
        hashMapRequestForImage(context, listener, methodType, url, reqCode, clazz, jsonRequest, params, fileUploadData, TAG);
    }


    /**
     * To make UnAuthorize requests, Here its NOT mandatory to send the access token in header
     *
     * @param context     the component under which this request is sent
     * @param listener    Listener is used to check the network call is successfully finished or not
     * @param methodType  Request type e.g.(GET,POST,PUT,DELETE,PATCH...)
     * @param url         server end point for particular service
     * @param reqCode     int value used for differentiate a request from other requests
     * @param clazz       Gson reflection class object
     * @param jsonRequest json object whcih the request required
     * @param params      values which need to be send with the request if no params send it null
     * @param TAG         String value which can be used for the cancelling the request
     * @param <T>         used for return type objects
     */

    public static <T> void unAuthorizedRequest(Context context, final NetworkListener listener, final int methodType, String url, int reqCode, Class<T> clazz, JSONObject jsonRequest, Map<String, String> params, final String TAG) {
        VolleyLog.DEBUG = false;
        if (jsonRequest == null) {
            commonRequest(context, listener, methodType, url, reqCode, clazz, params, TAG, AUTHORIZATION_NOT_REQUIRED);
        } else {
            commonJsonObjectRequest(context, listener, methodType, url, reqCode, clazz, jsonRequest, params, TAG, AUTHORIZATION_NOT_REQUIRED);

        }
    }

    /**
     * @param context
     * @param response Response got in error model
     * @return
     */
    public static String getErrorMessage(Context context, ErrorModel response) {
        String error = null;
        if (response != null && response.getErrors().size() > 0) {
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
        /*if (eachError.getType() != null) {
            if (Constants.ERR_INVALID_TOKEN.equals(eachError.getType())) {
                errorMessage = context.getString(R.string.ss_err_05_login_again);
            } else if (Constants.ERR_INVALID_GRANT.equals(eachError.getType())) {
                errorMessage = context.getString(R.string.ss_err_06_invalid_credential);
            } else if (Constants.ERR_USER_NOT_REG.equals(eachError.getType())) {
                errorMessage = context.getString(R.string.ss_err_07_user_not_reg);
            } else if (Constants.ERR_DUPLICATE_UID.equals(eachError.getType())) {
                errorMessage = context.getString(R.string.ss_err_08_username_exists);
            } else if (Constants.ERR_PASS_MISS_MATCH.equals(eachError.getType())) {
                errorMessage = context.getString(R.string.ss_err_09_incorrect_password);
            } else if (Constants.ERR_NO_CHECK_CART.equals(eachError.getType())) {
                errorMessage = context.getString(R.string.ss_err_10_recheck_cart_items);
            }
        }*/
        return errorMessage;
    }


}
