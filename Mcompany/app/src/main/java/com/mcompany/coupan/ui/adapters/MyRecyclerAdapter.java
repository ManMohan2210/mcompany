package com.mcompany.coupan.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;
import com.mcompany.coupan.R;
import com.mcompany.coupan.appcommon.listeners.GlideImageLoadListener;
import com.mcompany.coupan.appcommon.listeners.SearchQueryListener;
import com.mcompany.coupan.appcommon.utility.Utility;
import com.mcompany.coupan.dtos.Deal;
import com.mcompany.coupan.views.AppTextView;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> implements Filterable {

    private List<Deal> listItems, filterList;
    private Context mContext;
    private CustomFilter filter;
    private SearchQueryListener searchQueryListener;

    public MyRecyclerAdapter(Context context, List<Deal> listItems, SearchQueryListener listener) {
        this.listItems = listItems;
        this.mContext = context;
        this.filterList = new ArrayList<Deal>();
        this.searchQueryListener = listener;
        // we copy the original list to the filter list and use it for setting row values
        this.filterList.addAll(this.listItems);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_best_deals, null);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder customViewHolder, int position) {

        Deal listItem = filterList.get(position);
        customViewHolder.dealMessage.setText(listItem.getMessage());
        customViewHolder.dealEndsOn.setText(listItem.getEndDate());
        Utility.loadImage(mContext, customViewHolder.dealImage,
                listItem.getImageUrl(),
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
        return (null != filterList ? filterList.size() : 0);
    }

//    // Do Search...
//    public void filter(final String text) {
//
//        // Searching could be complex..so we will dispatch it to a different thread...
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                // Clear the filter list
//                filterList.clear();
//
//                // If there is no search value, then add all original list items to filter list
//                if (TextUtils.isEmpty(text)) {
//
//                    filterList.addAll(listItems);
//
//                } else {
//                    // Iterate in the original List and add it to filter list...
//                    for (Deal item : listItems) {
//                        if (item.getMessage().toLowerCase().contains(text.toLowerCase()) ||
//                                item.getMessage().toLowerCase().contains(text.toLowerCase())) {
//                            // Adding Matched items
//                            filterList.add(item);
//                        }
//                    }
//                }
//
//                // Set on UI Thread
//                ((Activity) mContext).runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        // Notify the List that the DataSet has changed...
//                        notifyDataSetChanged();
//                    }
//                });
//
//            }
//        }).start();
//
//    }

    @Override
    public Filter getFilter() {
        // TODO Auto-generated method stub
        if (filter == null) {
            filter = new CustomFilter();
        }

        return filter;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public AppTextView dealMessage, dealEndsOn;
        public ImageView dealImage;

        public MyViewHolder(View view) {
            super(view);
            dealImage = view.findViewById(R.id.iv_deals_image);
            dealMessage = view.findViewById(R.id.tv_best_deals_label);
            dealEndsOn = view.findViewById(R.id.tv_best_deals_end_label);
        }
    }

    class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            // TODO Auto-generated method stub

            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                //CONSTARINT TO UPPER
                constraint = constraint.toString().toUpperCase();

                ArrayList<Deal> filters = new ArrayList<Deal>();

                //get specific items
                for (int i = 0; i < listItems.size(); i++) {
                    Deal deal = listItems.get(i);
                    if (deal.getMessage().toUpperCase().contains(constraint)) {
                        filters.add(deal);
                    }
                }

                results.count = filters.size();
                results.values = filters;

            } else {
                results.count = listItems.size();
                results.values = listItems;

            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filterList = (ArrayList<Deal>) results.values;
            if (Utility.isCollectionNullOrEmpty(filterList)) {
                searchQueryListener.handleEmptyView(false);
            } else {
                searchQueryListener.handleEmptyView(true);
                notifyDataSetChanged();
            }
        }

    }
}
