package com.mcompany.coupan.appcommon.utility;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.util.Log;
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

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collection;

public class Utility {
    private static final String TAG = Utility.class.getSimpleName();
    public static final String NETWORK_TAG = "networklog";

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
        return str == null || str.isEmpty();
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

}
