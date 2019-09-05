package com.example.cdj.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cdj.myapplication.Bean.SecListBean;
import com.example.cdj.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：
 * 创建人：vicwing
 * 创建时间：2019-09-04 18:01
 * 最后修改人：vicwing
 */
public class TabLayoutActivity extends AppCompatActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        ButterKnife.bind(this);
        viewpager.setAdapter(new Myadapter(makeData(), this));
        tabLayout.setupWithViewPager(viewpager);
    }

    private List<SecListBean> makeData() {
        ArrayList<SecListBean> objects = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SecListBean secListBean = new SecListBean();
            secListBean.setMessage("msg" + i);
            objects.add(secListBean);
        }
        return objects;
    }

    static class Myadapter extends PagerAdapter {

        private final List<SecListBean> list;

        private Activity activity;

        public Myadapter(List<SecListBean> list) {
            this.list = list;
        }

        public Myadapter(List<SecListBean> list, Activity activity) {
            this.list = list;
            this.activity = activity;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position).getMessage();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View mCurrentView = makeImageView(position, list.get(position).getMessage());
            container.addView(mCurrentView);
            return mCurrentView;
        }

        @NonNull
        private TextView makeImageView(final int i, String url) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            TextView textView = new TextView(activity);
            textView.setLayoutParams(params);
            textView.setText(url);
            return textView;
        }


    }
}
