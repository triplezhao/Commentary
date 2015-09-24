package com.potato.chips.common;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.potato.demo.R;
import com.potato.demo.databinding.ActivityMyBinding;
import com.potato.chips.base.BaseActivity;

public class MyActivity extends BaseActivity {


    ActivityMyBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_my);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_video_cacheing:
                PageCtrl.start2YKDownloadingPage(mContext);
                break;
            case R.id.ll_video_cached:
                PageCtrl.start2YKDownloadedPage(mContext);
                break;
        }
    }
}
