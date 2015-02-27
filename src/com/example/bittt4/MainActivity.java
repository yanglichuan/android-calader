
package com.example.bittt4;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 5; i++) {
            View view = View.inflate(getApplicationContext(), R.layout.mycalaryview, null);
            views.add(view);
        }

        viewpager = (ViewPager) this.findViewById(R.id.viewpager);
        viewpager.setAdapter(new MyAdapter());
        viewpager.setCurrentItem(Integer.MAX_VALUE / 2);
        viewpager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    private ArrayList<View> views = new ArrayList<View>();

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position % 5));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = views.get(position % 5);
            MyCalaryView calaryView = null;
            ViewGroup vv = (ViewGroup) view;
            calaryView = (MyCalaryView) vv.getChildAt(0);
            Calendar c = Calendar.getInstance();
            if (position == Integer.MAX_VALUE / 2) {
                c.setTime(new Date());
                calaryView.setShowMonth(c);
            } else {
                c.setTime(new Date());
                int monthNums = position - Integer.MAX_VALUE / 2;
                c.add(Calendar.MONTH, monthNums);
                calaryView.setShowMonth(c);
            }
            
            Log.i("xitao", "自动更新");
            
            container.addView(view);
            return view;
        }
    }
}
