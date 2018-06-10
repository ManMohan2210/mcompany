package com.mcompany.coupan.appcommon.listeners;

import android.support.v7.widget.SearchView;

public class SearchViewQueryListener implements SearchView.OnQueryTextListener {
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
