package com.mcompany.coupan.appcommon.listeners;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;


public interface GlideImageLoadListener  {
    void onSuccess(GlideDrawable resource, String model, Target<GlideDrawable> target);
    void onFailed();
}
