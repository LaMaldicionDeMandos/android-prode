package org.pasut.prode.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by marcelo on 21/04/14.
 */
public class NoSwipableViewPager extends ViewPager {
    public NoSwipableViewPager(Context context) {
        super(context);
    }

    public NoSwipableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
