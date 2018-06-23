package com.mcompany.coupan.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;
import com.mcompany.coupan.R;
import com.mcompany.coupan.appcommon.listeners.GlideImageLoadListener;
import com.mcompany.coupan.appcommon.utility.Utility;
import com.mcompany.coupan.dtos.Deal;
import com.mcompany.coupan.views.AppTextView;

import java.util.List;

public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Deal> dealList;
    private int mActualWidth;
    private int mActualHeight = -1;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public AppTextView dealMessage, dealEndsOn;
        public ImageView dealImage;

        public MyViewHolder(View view) {
            super(view);
            dealImage = view.findViewById(R.id.iv_deals_image);
            dealMessage = view.findViewById(R.id.tv_best_deals_label);
            dealEndsOn = view.findViewById(R.id.tv_best_deals_end_label);
        }
    }


    public DealsAdapter(Context mContext, List<Deal> albumList) {
        this.mContext = mContext;
        this.dealList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_best_deals, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Deal deal = dealList.get(position);
        holder.dealMessage.setText(deal.getMessage());
        holder.dealEndsOn.setText(deal.getEndDate());

        Utility.loadImage(mContext, holder.dealImage,
                deal.getImageUrl(),
                0, new GlideImageLoadListener() {
                    @Override
                    public void onSuccess(GlideDrawable resource, String model, Target<GlideDrawable> target) {
//                        if(mActualHeight < 0){
//                            mActualHeight = Utility.getActualHeight(mActualWidth, resource.getIntrinsicHeight(), resource.getIntrinsicWidth());
//                        }
//                        holder.ivItemImage.getLayoutParams().height = mActualHeight;
                    }

                    @Override
                    public void onFailed() {

                    }
                });
    }

    @Override
    public int getItemCount() {
        return dealList.size();
    }
}