package com.mcompany.coupan.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mcompany.coupan.R;
import com.mcompany.coupan.appcommon.constants.Constants;
import com.mcompany.coupan.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OneFragment extends BaseFragment {

    private Unbinder mUnbinder;

    private String name = Constants.EMPTY_STRING;

    @BindView(R.id.tv_deal_type)
    TextView textView;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setFragName();
        return view;
    }

    public void setFragName(){
        textView.setText(name);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}