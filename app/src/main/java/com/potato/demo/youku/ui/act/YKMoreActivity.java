package com.potato.demo.youku.ui.act;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.potato.demo.R;
import com.potato.demo.youku.ui.fragment.YKCachedFragment;
import com.potato.demo.youku.ui.fragment.YKCachingFragment;
import com.potato.chips.base.BaseActivity;
import com.potato.library.util.L;
import com.potato.library.view.slidingtab.SlidingTabLayout;

/**
 * Created by ztw on 2015/7/3.
 */
public class YKMoreActivity extends BaseActivity {

    public static final String TAG = "YKMoreActivity";
    //data
    //logic
    private String[] titles;

    /**
     * A custom {@link ViewPager} title strip which looks much like Tabs present in Android v4.0 and
     * above, but is designed to give continuous feedback to the user when scrolling.
     */
    private SlidingTabLayout mSlidingTabLayout;

    /**
     * A {@link ViewPager} which will be used in conjunction with the {@link SlidingTabLayout} above.
     */
    private ViewPager mViewPager;
    private SectionsPagerAdapter sectionsPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yk_more);
        titles = new String[]{"下载完成", "正在下载", "收藏"};
        L.i(TAG, "onCreateView");

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        sectionsPagerAdapter = new SectionsPagerAdapter(this.getSupportFragmentManager());
//        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(sectionsPagerAdapter);
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs_dyou);
        //设置选中文字颜色
        mSlidingTabLayout.setSelectedColor(mContext.getResources().getColor(R.color.text_title_color_press));
        mSlidingTabLayout.setUnSelectedColor(mContext.getResources().getColor(R.color.white));
//      //设置分割线的颜色
//        mSlidingTabLayout.setDividerColors(getResources().getColor(R.color.transparent));
//      //设置下划线的颜色
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.transparent));
        //设置title字体
        mSlidingTabLayout.setTextSize(20);
        mSlidingTabLayout.setSelectedTextsize(20);
        mSlidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub
//                titles[0]=""+System.currentTimeMillis()/1000*60*60*24;
                L.i(TAG, "onPageSelected:" + arg0);

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
//                L.i(TAG, "onPageScrollStateChanged:"+arg0 );
                // TODO Auto-generated method stub
            }
        });
//        mSlidingTabLayout.setTabCreater(new SlidingTabLayout.TabCreater() {
//            @Override
//            public View getTabView(int position) {
//                return null;
//            }
//
//            @Override
//            public void setTabView(View view, int position, boolean isSelect) {
//
//            }
//        });
        mSlidingTabLayout.setViewPager(mViewPager);
        mViewPager.setCurrentItem(0);

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages. This provides the data for the {@link ViewPager}.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        // END_INCLUDE (fragment_pager_adapter)

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public int getCount() {
            // Show 3 total pages.
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }


        // BEGIN_INCLUDE (fragment_pager_adapter_getitem)
        public Fragment getItem(int position) {
            L.i(TAG, "getItem:" + position);
            Fragment fpage = null;
            switch (position) {
                case 0:
                    fpage = new YKCachedFragment();
                    break;
                case 1:
                    fpage =  new YKCachingFragment();
                    break;
                case 2:
                    fpage =  new YKCachedFragment();
                    break;
            }
            return fpage;
        }

        @Override
        public int getItemPosition(Object object) {
            // TODO Auto-generated method stub
            return POSITION_NONE;
        }
        // END_INCLUDE (fragment_pager_adapter_getpagetitle)
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
//        case R.id.iv_user:
//
//            break;
        }
    }
}
