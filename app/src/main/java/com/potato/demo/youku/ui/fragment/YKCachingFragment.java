package com.potato.demo.youku.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.potato.chips.base.BaseFragment;
import com.potato.chips.base.BaseListAdapter;
import com.potato.demo.R;
import com.potato.demo.databinding.FragmentYkCachingBinding;
import com.potato.demo.youku.ui.adapter.YKVideoCachingAdapter;
import com.youku.service.download.DownloadInfo;
import com.youku.service.download.DownloadManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;


/**
 * 简单展示正在的视频，用户可定制自己的界面
 */

public class YKCachingFragment extends BaseFragment {

    //通过DownloadManager获取下载视频列表
    private DownloadManager downloadManager;

    //记录当前下载中的视频列表
    private ArrayList<DownloadInfo> downloadingList_show = new ArrayList<DownloadInfo>();

    //数据Adapter
    private BaseListAdapter adapter;
    private FragmentYkCachingBinding mBinding;
    private static final int MSG_STATE_CHANGE = 0;

    @Override
    public boolean handleMessage(Message message) {
        if (message.what == MSG_STATE_CHANGE) {
            readData();
            //更改界面现实状态
            adapter.setDataList(downloadingList_show);
            adapter.notifyDataSetChanged();
        }
        return false;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(container.getContext()),
                R.layout.fragment_yk_caching,
                container,
                false);
        readData();

        adapter = new YKVideoCachingAdapter(getActivity(), mHandler);
        adapter.setDataList(downloadingList_show);
        mBinding.list.setAdapter(adapter);

        return mBinding.getRoot();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }

    /**
     * 获取当前正在下载的视频信息
     */
    private void readData() {
        downloadingList_show.clear();

        //通过DownloadManager类的getDownloadingData()接口函数获取已经下载的视频信息
        downloadManager = DownloadManager.getInstance();
        Iterator iter = downloadManager.getDownloadingData().entrySet().iterator();

        while (iter.hasNext()) {
            Entry entry = (Entry) iter.next();
            //视频信息实体类用DownloadInfo表示
            DownloadInfo info = (DownloadInfo) entry.getValue();
            downloadingList_show.add(info);
        }

    }


}
