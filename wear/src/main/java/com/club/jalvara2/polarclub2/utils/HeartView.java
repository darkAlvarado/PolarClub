package com.club.jalvara2.polarclub2.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.club.jalvara2.polarclub2.R;

/**
 * Created by jalvara2 on 7/02/18.
 */

public class HeartView extends AppCompatImageView {

    private Drawable heartDrawable;

    public HeartView(Context context) {
        super(context);
    }

    public HeartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        heartDrawable = ContextCompat.getDrawable(getContext(), R.drawable.heart_red);
        setImageDrawable(heartDrawable);
    }
}
