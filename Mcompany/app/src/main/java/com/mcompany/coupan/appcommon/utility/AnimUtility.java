package com.mcompany.coupan.appcommon.utility;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.mcompany.coupan.R;

/**
 * Created by manmohansingh on 03-06-2018.
 */

public class AnimUtility {
    private static final int TIME = 300;

    /**
     * @param view       which is under animation
     * @param fromDegree in float
     * @param toDegree   in float
     */
    public static void rotateView(View view, float fromDegree, float toDegree) {
        AnimationSet animSet = new AnimationSet(true);
        animSet.setInterpolator(new DecelerateInterpolator());
        animSet.setFillAfter(true);
        animSet.setFillEnabled(true);

        final RotateAnimation animRotate = new RotateAnimation(fromDegree, toDegree, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        animRotate.setDuration(TIME);
        animRotate.setFillAfter(true);
        animSet.addAnimation(animRotate);

        view.startAnimation(animSet);
    }

    /**
     * @param view     which is unser animation
     * @param from     in float
     * @param to       in float
     * @param duration animation complete duration
     */

    public static void fadeView(View view, float from, float to, long duration) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, from, to);
        objectAnimator.setDuration(duration);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            objectAnimator.setAutoCancel(true);
        }
        objectAnimator.start();
    }

    public static void fadeInImageAnim(ImageView target) {
        target.setAlpha(0f);
        ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(target, "alpha", 0f, 1f);
        fadeAnim.setDuration(600);
        fadeAnim.start();
    }

    public static void translateXView(View view, float from, float to, long duration) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, from, to);
        objectAnimator.setDuration(duration);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            objectAnimator.setAutoCancel(true);
        }
        objectAnimator.start();
    }

    public static void translateYView(View view, float from, float to, long duration) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, from, to);
        objectAnimator.setDuration(duration);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            objectAnimator.setAutoCancel(true);
        }
        objectAnimator.start();
    }

    public static void slideUp(View view, Context context) {
        view.startAnimation(AnimationUtils.loadAnimation(context,
                R.anim.pop_up_slide_up));
    }

    public static void slideDown(View view, Context context) {
        view.startAnimation(AnimationUtils.loadAnimation(context,
                R.anim.pop_up_slide_down));
    }


    public static void slidingUpAnim(RecyclerView.ViewHolder holder, boolean goesDown) {
        ObjectAnimator translateLeftAnim = ObjectAnimator.ofFloat(holder.itemView, "translationY", goesDown ? 100 : -100, 0);
        translateLeftAnim.setDuration(500);
        translateLeftAnim.start();
    }


}

