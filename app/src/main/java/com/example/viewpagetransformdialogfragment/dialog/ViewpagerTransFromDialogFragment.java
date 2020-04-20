package com.example.viewpagetransformdialogfragment.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.viewpagetransformdialogfragment.R;

import java.util.ArrayList;

public class ViewpagerTransFromDialogFragment extends DialogFragment {

    public static final String TAG = "ViewpagerTicketDialogFr";
    public static ViewpagerTransFromDialogFragment newInstance() {

        Bundle args = new Bundle();
        ViewpagerTransFromDialogFragment fragment = new ViewpagerTransFromDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private ArrayList<View> viewList = new ArrayList<>();
    private ArrayList<RadioButton> radioButtonArrayList = new ArrayList<>();
    private ViewPager mViewPager;
    private volatile int childrenHeight = 9999999 ;

    public void show(FragmentManager manager) {
        if (!isAdded()) {
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.add(this,TAG);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_viewpager, null);
        mViewPager = view.findViewById(R.id.view_pager);
        RadioGroup radioGroup = view.findViewById(R.id.rg_icon_dot);
        ImageView iv_ticket_viewpager_close = view.findViewById(R.id.iv_ticket_viewpager_close);

        for (int i = 0; i < 6; i++) {
            //动态添加viewpager
            View viewPagerView = LayoutInflater.from(getActivity()).inflate(R.layout.item_viewpager_dialog ,null);
            viewList.add(viewPagerView);
            //动态添加dot
            RadioButton radioButton = (RadioButton) LayoutInflater.from(getActivity()).inflate(R.layout.radio_button_viewpager,radioGroup ,false);
            RadioGroup.LayoutParams lp = (RadioGroup.LayoutParams) radioButton.getLayoutParams();
            lp.width = Utils.dip2px(getActivity() ,8);
            lp.height = Utils.dip2px(getActivity() ,8);
            //设置RadioButton边距 (int left, int top, int right, int bottom)
            if(i != 0){
                lp.setMargins(Utils.dip2px(getActivity() ,10),0,0,0);
            }else {
                lp.setMargins(Utils.dip2px(getActivity() ,0),0,0,0);
            }
            radioButton.setLayoutParams(lp);
            radioGroup.addView(radioButton);
            radioButtonArrayList.add(radioButton);


        }
        radioGroup.check(radioButtonArrayList.get(0).getId());
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(new MyViewPagerAdapter());
        mViewPager.setCurrentItem(0);
        // 设置切换动画
        mViewPager.setPageTransformer(true, new PageTransformer());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                radioGroup.check(radioButtonArrayList.get(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Dialog dialog = new Dialog(getActivity(), R.style.view_pager_trans_dialog_fragment);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        iv_ticket_viewpager_close.setOnClickListener(v -> dismiss());
//        dialog.setCancelable(cancelable);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    class MyViewPagerAdapter extends PagerAdapter {

        // viewpager中的要显示的View的总数量
        @Override
        public int getCount() {
            return viewList.size();
        }

        // 滑动切换的时候销毁当前的View
        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView(viewList.get(position));
        }

        // 每次滑动的时候生成的View
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
    }

}
