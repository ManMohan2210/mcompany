package com.mcompany.coupan.appcommon.utility;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mcompany.coupan.R;
import com.mcompany.coupan.appcommon.constants.Constants;
import com.mcompany.coupan.appcommon.listeners.GlideImageLoadListener;
import com.mcompany.coupan.appcommon.logger.AppLogger;
import com.mcompany.coupan.ui.application.McompApplication;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Utility {
    private static final String TAG = Utility.class.getSimpleName();
    public static final String NETWORK_TAG = "networklog";
    private static ColorDrawable mImagePlaceHolder = null;
    private static HashMap<String, Spannable> spannableTextHashMap = new HashMap<>();

    private static final String PATTERN_NAME = "^[a-zA-Z0-9-]+(?: +[a-zA-Z0-9-]+)+$";
    private static final String PATTERN_PHONE = "^[6-9][0-9]{9}$";
    private static final String PATTERN_FCCNAME = "[a-zA-Z][a-zA-Z ]+";
    private static final String PATTERN_ORDERID = "[0-9]+";
    public static Typeface tf = null;
    public static long backgraoundTime, foregraoundTime;
    public static String currentScreenName = Constants.EMPTY_STRING;

    //for Span text
    public static String mFontBold;
    public static String mFontRegular;
    public static Typeface mTfBold;
    public static Typeface mTfRegular;
    private static HashMap<String, Boolean> productAvailableAtStore;

    /**
     * Get width of device.
     *
     * @param context
     * @return
     */
    public static int getDeviceWidth(Activity context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }


    /**
     * Get height of device.
     *
     * @param context
     * @return
     */
    public static int getDeviceHeight(Activity context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }


    public static boolean isStringNullOrEmpty(String str) {
        if (str != null && !str.isEmpty()) {
            return false;
        }
        return true;
    }

    public static String validateStringNull(String str) {
        if (null != str) {
            return str;
        }
        return Constants.NA_STRING;
    }

    public static boolean isCollectionNullOrEmpty(Collection collection) {
        boolean isNullOrEmpty = true;
        if (collection != null && collection.size() > 0) {
            isNullOrEmpty = false;
        }
        return isNullOrEmpty;
    }

    /**
     * Returns false if field left empty and if doesn't match with Patterns.EMAIL_ADDRESS
     *
     * @param email
     * @return
     */
    public static boolean isValidEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        }
        final Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();

    }

    public static boolean isValidPassword(String pass) {
        if (TextUtils.isEmpty(pass) || pass.length() < 6) {
            return false;
        }
        return true;
    }

    /**
     * Returns false for empty field, if all the characters are not digits, if the length of field is less than 5 and greater than 15
     *
     * @param orderNo
     * @return
     */
    public static boolean isValidOrderID(String orderNo) {
        if (orderNo.length() <= 4) {
            return false;
        }
        return true;
    }

    /**
     * Returns false for empty field, if all the characters are not digits, if the length of field is less than 10
     * And if phone is starts with 0,1,2,3,4,5,6.
     *
     * @param phoneNo
     * @return
     */

    public static boolean isValidPhoneNo(String phoneNo) {
        if (TextUtils.isEmpty(phoneNo) || !Pattern.compile(PATTERN_PHONE).matcher(phoneNo).matches() || phoneNo.length() != 10) {
            return false;
        }
        return true;
    }

    public static boolean isValidNameOnCard(String strName) {
        if (TextUtils.isEmpty(strName) || !Pattern.compile(PATTERN_FCCNAME).matcher(strName.trim()).matches()) {
            return false;
        }
        return true;
    }

    /**
     * Check full name of user and return false if name is empty, name does not contain atleast one space.
     *
     * @param name
     * @return
     */
    public static boolean isValidName(String name) {
        if (TextUtils.isEmpty(name) || !Pattern.compile(PATTERN_NAME).matcher(name.trim()).matches()) {
            return false;
        }
        return true;
    }

    /**
     * Load network image using image URL with Glide.
     *
     * @param context    Activity/Application context.
     * @param targetView Target ImageView into which image has to load.
     * @param url        URL for the image.
     * @param errorImage Error image resource ID.
     * @param listener   Image load listener to get callback of loading status.
     */
    public static void loadImage(Context context, final ImageView targetView, String url, int errorImage, final GlideImageLoadListener listener) {
        ColorDrawable mImagePlaceHolder = null;
        if (mImagePlaceHolder == null) {
            mImagePlaceHolder = new ColorDrawable(ContextCompat.getColor(context, R.color.place_holder_color));
        }

        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(mImagePlaceHolder)
                .dontAnimate()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        if (listener != null) {
                            listener.onFailed();
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        AnimUtility.fadeInImageAnim(targetView);
                        if (listener != null) {
                            listener.onSuccess(resource, model, target);
                        }
                        return false;
                    }
                })
                .error(errorImage)
                .into(targetView);
    }

    /**
     * Get the color id.
     *
     * @param context Activity/Application context.
     * @param colorId colorId.
     * @return int
     */
    public static final int getColor(Context context, int colorId) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, colorId);
        } else {
            return context.getResources().getColor(colorId);
        }
    }


    public static String getDeviceDPI(Context context) {
        String screenViewPort = null;
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        switch (metrics.densityDpi) {

            case DisplayMetrics.DENSITY_LOW:
                screenViewPort = "ViewPortSmall";
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                screenViewPort = "ViewPortMedium";
                break;
            default:
                screenViewPort = "ViewPortLarge";
                break;
        }
        return screenViewPort;
    }




    public static String concat(String... str) {
        StringBuffer concatString = new StringBuffer();
        for (String error : str) {
            concatString.append(error);
        }
        return concatString.toString();
    }


    public static void hideSoftKeyboard(Activity activity) {
        if (activity.getCurrentFocus() != null && activity.getCurrentFocus().getWindowToken() != null) {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) activity.getSystemService(
                            Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * Emoticon filter for edittext
     */
    public static InputFilter EMOJI_FILTER = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            for (int index = start; index < end; index++) {

                int type = Character.getType(source.charAt(index));

                if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL) {
                    return "";
                }
            }
            return null;
        }
    };

    public static int calculatePercentValue(float percent, int deviceWidth) {
        return (percent <= 0) ? deviceWidth : (int) ((deviceWidth * percent) / 100);
    }


    public static int getActualHeight(int actualWidth, int aspectH, int aspectW) {
        return (aspectH * actualWidth) / aspectW;
    }

    public static String encodeParameterValue(String valueParameter) {
        String encodedString = valueParameter;
        try {
            encodedString = URLEncoder.encode(valueParameter, "UTF-8");
        } catch (Exception ex) {
            AppLogger.d(TAG, Log.getStackTraceString(ex));
        }
        return encodedString;
    }

    public static String decodeParameterValue(String valueParameter) {
        String encodedString = valueParameter;
        try {
            encodedString = URLDecoder.decode(valueParameter, "UTF-8");
        } catch (Exception ex) {
            AppLogger.d(TAG, Log.getStackTraceString(ex));
        }
        return encodedString;
    }

    public static boolean isLocationPermissionGraneted(){
        return (ActivityCompat.checkSelfPermission(McompApplication.getInstance().getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(McompApplication.getInstance().getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED);
    }
}
