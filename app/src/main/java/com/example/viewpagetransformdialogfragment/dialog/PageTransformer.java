package com.example.viewpagetransformdialogfragment.dialog;

import android.view.View;

import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;


public class PageTransformer implements ViewPager.PageTransformer {

    private static final float ROTATING_MAX = 30.0f;
    private float mRotating;

    /**
     * @param view     滑动中的那个view
     * @param position
     */
    public void transformPage(View view, float position) {
        //界面不可见
        if (position < -1) {
            ViewCompat.setRotation(view, 0);
        }else if(position <= 0){
            ViewCompat.setPivotX(view, view.getMeasuredWidth() * 0.1f);
            ViewCompat.setPivotY(view, view.getMeasuredHeight() );
            view.setRotation( 0);
        } else if (position <= 1) {
            mRotating = (ROTATING_MAX * position);
            ViewCompat.setPivotX(view, view.getMeasuredWidth() * 0.1f);
            ViewCompat.setPivotY(view, view.getMeasuredHeight());
            view.setRotation( mRotating);

        } else { //界面不可见
            ViewCompat.setRotation(view, 0);
        }
    }
}