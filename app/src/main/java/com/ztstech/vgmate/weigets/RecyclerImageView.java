package com.ztstech.vgmate.weigets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RecyclerImageView extends ImageView {

	
    public RecyclerImageView(Context context) {
		super(context);
	}

	public RecyclerImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public RecyclerImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setImageDrawable(null);   
    }
	
	
}
