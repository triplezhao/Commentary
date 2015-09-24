package com.potato.chips.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.potato.chips.common.MyActivity;
import com.potato.chips.common.WebViewActivity;
import com.potato.demo.R;
import com.potato.demo.youku.ui.act.YKActivity;
import com.youku.player.YoukuPlayerBaseConfiguration;

public class MainTabActivity extends BaseTabHostActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected TabItem getTabItemView(int position) {

        TabItem tabItem = new TabItem();
        View tabItemView = mLayoutflater.inflate(R.layout.item_main_tab, null);
        ImageView iv_icon = (ImageView) tabItemView.findViewById(R.id.iv_icon);
        TextView tv_title = (TextView) tabItemView.findViewById(R.id.tv_tab);
        switch (position) {
            case 0:
                iv_icon.setImageResource(R.drawable.selector_nav_home);
                tabItem.setTitle("视频");
                tabItem.setView(tabItemView);
                tabItem.setIntent(new Intent(getApplication(), YKActivity.class));
                break;

            case 1:

                iv_icon.setImageResource(R.drawable.selector_nav_explore);
                tabItem.setTitle("发现");
                tabItem.setView(tabItemView);
                tabItem.setIntent(new Intent(getApplication(), YKActivity.class));
                break;
            case 2:

                iv_icon.setImageResource(R.drawable.selector_nav_contact);

                tabItem.setTitle("野店");
                tabItem.setView(tabItemView);
                tabItem.setIntent(new Intent(getApplication(), WebViewActivity.class));
                break;
            case 3:

                iv_icon.setImageResource(R.drawable.selector_nav_profile);
                tabItem.setTitle("我");
                tabItem.setView(tabItemView);
                tabItem.setIntent(new Intent(getApplication(), MyActivity.class));
                break;
        }
        tv_title.setText(tabItem.getTitle());
        return tabItem;
    }

    @Override
    protected int getTabItemCount() {
        return 4;
    }

    @Override
    public void onTabChanged(String s) {

    }

    /**
     * 应用退出时调用此方法
     */

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        YoukuPlayerBaseConfiguration.exit();        //退出应用时请调用此方法

        super.onBackPressed();

    }
}
