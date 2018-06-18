package com.example.zq.bingfang_pro;
import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
public class MyPagerAdapter extends PagerAdapter {
    private ArrayList<View> viewLists;
    private  View mCurrentView;
    public MyPagerAdapter() {
    }

    public MyPagerAdapter(ArrayList<View> viewLists) {
        super();
        this.viewLists = viewLists;
    }

    @Override
    public int getCount() {
        return viewLists.size();
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewLists.get(position),0);
        return viewLists.get(position);
    }
   /* @Override
    public void setPrimaryItem(ViewGroup container,int position,Object object)
    {
        mCurrentView=(View)object;
    }
    */
    public View getPrimaryItem(){return mCurrentView; }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewLists.get(position));
        // container.removeView((View) object);

    }

}