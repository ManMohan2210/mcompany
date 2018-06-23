package com.mcompany.coupan.ui.neardealfragment;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.mcompany.coupan.R;
import com.mcompany.coupan.appcommon.listeners.GlideImageLoadListener;
import com.mcompany.coupan.appcommon.logger.AppLogger;
import com.mcompany.coupan.appcommon.utility.Utility;
import com.mcompany.coupan.dtos.Deal;
import com.mcompany.coupan.views.AppTextView;

public class AppInfoWindowGoogleMap implements GoogleMap.InfoWindowAdapter {
    private static final String TAG = AppInfoWindowGoogleMap.class.getSimpleName();
    private Context context;
    public AppTextView dealMessage, dealEndsOn;
    public ImageView dealImage;

    public AppInfoWindowGoogleMap(Context ctx) {
        context = ctx;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = ((Activity) context).getLayoutInflater()
                .inflate(R.layout.item_best_deals, null);

        dealImage = view.findViewById(R.id.iv_deals_image);
        dealMessage = view.findViewById(R.id.tv_best_deals_label);
        dealEndsOn = view.findViewById(R.id.tv_best_deals_end_label);


        final Deal deal = (Deal) marker.getTag();
        if (deal != null) {
            if (!TextUtils.isEmpty(deal.getMessage())) {
                dealMessage.setText(deal.getMessage());
            }
            if (!TextUtils.isEmpty(deal.getEndDate())) {
                dealEndsOn.setText(deal.getEndDate());
            }

            if (!TextUtils.isEmpty(deal.getImageUrl())) {
                Utility.loadImage(context, dealImage,
                        deal.getImageUrl(),
                        0, new GlideImageLoadListener() {
                            @Override
                            public void onSuccess(GlideDrawable resource, String model, Target<GlideDrawable> target) {
                                AppLogger.d(TAG, "Image success");
                            }

                            @Override
                            public void onFailed() {
                                AppLogger.d(TAG, "Fails to load image");
                            }
                        });
            }

        }else {
            dealImage.setVisibility(View.GONE);
            dealEndsOn.setVisibility(View.GONE);
            dealMessage.setText(context.getString(R.string.you_are_here));
        }


        return view;
    }
}
