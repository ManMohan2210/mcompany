package com.mcompany.coupan.ui.base;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mcompany.coupan.R;
import com.mcompany.coupan.appcommon.utility.Utility;

/**
 * Created by manmohansingh on 10-06-2018.
 */
public abstract class AppBaseActivity extends AppCompatActivity {

    private static final long MILLISEC = 1000;
    private static final long SEC = 60 * MILLISEC;
    private static final long MIN = 60 * SEC;
    private static final long HOUR = 4 * MIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getSupportActionBar() != null && !TextUtils.isEmpty(getSupportActionBar().getTitle())) {
            String title = getSupportActionBar().getTitle().toString();
            Spannable spanText = new SpannableString(title);

            spanText.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.bw_3)), 0,
                    title.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

            getSupportActionBar().setTitle(spanText);

        }

        setupUI(getWindow().getDecorView().findViewById(android.R.id.content), this);
    }

    @Override
    protected void onStop() {
        super.onStop();


    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }

    /**
     * Show toast.
     *
     * @param msg the msg
     */
    public void showToast(String msg) {
        showToast(msg, Toast.LENGTH_SHORT);
    }

    /**
     * Show toast.
     *
     * @param resId the msg resource id.
     */
    public void showToast(int resId) {
        showToast(getString(resId), Toast.LENGTH_SHORT);
    }

    /**
     * Show toast.
     *
     * @param msg      the msg
     * @param duration the duration
     */
    public void showToast(String msg, int duration) {
        Toast.makeText(this, msg, duration).show();
    }

    /**
     * @param msg message shown in toast
     */
    public void showToastInCentre(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * Show toast.
     *
     * @param msg      the msg
     * @param duration the duration
     */
    public void showToastInCentre(String msg, int duration) {
        Toast toast = Toast.makeText(this, msg, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    /**
     * Method to be used when sub class has set his own layout and wants to add the given fragment to the passed container
     *
     * @param targetResorceId
     * @param fragment
     */
    public void addFragment(int targetResorceId, Fragment fragment, boolean needOfbackStack) {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(targetResorceId, fragment);
            if (needOfbackStack) {
                transaction.addToBackStack(null);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void replaceFragment(int targetResorceId, Fragment fragment) {

        try {
            if (fragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(targetResorceId, fragment);
                fragmentTransaction.commit();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setToolbar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
    }


    public void setupUI(View view, final Activity activity) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (!((view instanceof EditText) || (view instanceof AppCompatEditText))) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    Utility.hideSoftKeyboard(activity);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView, activity);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
