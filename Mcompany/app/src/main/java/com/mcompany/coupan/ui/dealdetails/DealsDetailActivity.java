package com.mcompany.coupan.ui.dealdetails;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;
import com.mcompany.coupan.R;
import com.mcompany.coupan.appcommon.constants.Constants;
import com.mcompany.coupan.appcommon.constants.IntentKeyConstants;
import com.mcompany.coupan.appcommon.listeners.GlideImageLoadListener;
import com.mcompany.coupan.appcommon.utility.Utility;
import com.mcompany.coupan.dtos.Deal;
import com.mcompany.coupan.ui.base.AppBaseActivity;
import com.mcompany.coupan.views.AppTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DealsDetailActivity extends AppBaseActivity {

    @BindView(R.id.iv_deal_detail)
    ImageView ivDealDtail;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    @BindView(R.id.fab_share)
    FloatingActionButton fab;

    @BindView(R.id.tv_best_deals_detail)
    AppTextView appTextViewDescription;

    @BindView(R.id.tv_coupan_code)
    AppTextView appTextViewCoupnaCode;

    @BindView(R.id.tv_ends_date)
    AppTextView atvEndDate;

    private Deal mDeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_deal_detail);
        ButterKnife.bind(this);
        setToolBar();

        mDeal = getIntent().getParcelableExtra(IntentKeyConstants.EXTRA_DEAL_DATA);
        if (mDeal != null) {
            setData();
        }
    }

    private void setToolBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Constants.EMPTY_STRING);

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);
        actionBar.show();
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick(R.id.iv_coupan_code_copy)
    public void onCodeCopy() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", appTextViewCoupnaCode.getText());
        clipboard.setPrimaryClip(clip);
        showToastInCentre(getString(R.string.code_copied));
    }

    private void setData() {
        if (mDeal == null) {
            return;
        }
        appTextViewDescription.setText(mDeal.getMessage());
        appTextViewCoupnaCode.setText(mDeal.getCode());
        atvEndDate.setText(mDeal.getEndDate());
        Utility.loadImage(this, ivDealDtail,
                mDeal.getImageUrl(),
                0, new GlideImageLoadListener() {
                    @Override
                    public void onSuccess(GlideDrawable resource, String model, Target<GlideDrawable> target) {
                    }

                    @Override
                    public void onFailed() {

                    }
                });
    }

    @OnClick(R.id.fab_share)
    public void sharePdpDetails() {
        String message = mDeal.getMessage() + "\n Use code: " + mDeal.getCode();
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        share.putExtra(android.content.Intent.EXTRA_TEXT, message);
        startActivity(share);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return false;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
