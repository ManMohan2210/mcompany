package com.mcompany.coupan.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.mcompany.coupan.R;
import com.mcompany.coupan.appcommon.constants.Constants;
import com.mcompany.coupan.appcommon.utility.TypefaceUtil;

/**
 * Created by manmohansingh on 03-06-2018.
 */

public class AppTextView extends AppCompatTextView {
	Context mContext;
	private String mCustomFont;


	public AppTextView(Context context) {
		super(context);
		init(context, null);
	}

	public AppTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}

	public AppTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {
		this.mContext = context;
		if(attrs == null) return;

		TypedArray tvAttrs = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
		int cf = tvAttrs.getInteger(R.styleable.CustomTextView_fontName, 0);
		int fontName = TypefaceUtil.getFontName(cf);

		mCustomFont = getResources().getString(fontName);

		if(mCustomFont != null && mCustomFont.equals(mContext.getResources().getString(R.string.italic))){
			setText(getText()+ Constants.SPACE_STRING);
		}

		Typeface tf = TypefaceUtil.getFont(getContext(), fontName);//Typeface.createFromAsset(context.getAssets(), "font/" + mCustomFont + ".ttf");
		setTypeface(tf);
		tvAttrs.recycle();
	}

	@Override
	public void setText(CharSequence text, BufferType type) {
		if(mCustomFont != null && mCustomFont.equals(mContext.getResources().getString(R.string.italic))){
			text = text + Constants.SPACE_STRING;
		}
		super.setText(text, type);
	}

	public void setFontName(int fontName){
		mCustomFont = getResources().getString(fontName);
		Typeface tf = TypefaceUtil.getFont(getContext(), fontName);//Typeface.createFromAsset(mContext.getAssets(), "font/" + mCustomFont + ".ttf");
		setTypeface(tf);
	}

}
