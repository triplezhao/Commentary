package com.potato.demo.youku.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.potato.chips.base.BaseFragment;
import com.potato.demo.R;
import com.potato.demo.databinding.FragmentYkCachedBinding;
import com.potato.demo.youku.ui.adapter.YKVideoCachedAdapter;
import com.potato.library.adapter.BaseListAdapter;
import com.youku.service.download.DownloadInfo;
import com.youku.service.download.DownloadManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * 简单展示已经缓存的视频，用户可定制自己的界面
 */
public class YKCachedFragment extends BaseFragment {
    //展示视频信息的ListView

    //通过DownloadManager获取下载视频列表
    private DownloadManager downloadManager;

    //记录当前已经下载的视频列表
    private ArrayList<DownloadInfo> downloadedList_show = new ArrayList<DownloadInfo>();

    //数据Adapter
    private BaseListAdapter adapter;
    private FragmentYkCachedBinding mBinding;

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
                R.layout.fragment_yk_cached,
                container,
                false);

        adapter = new YKVideoCachedAdapter(mContext);

        mBinding.list.setAdapter(adapter);

        return mBinding.getRoot();

    }


    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        //获取已经下载的视频数据
        initData();
        //同志数据更新
        adapter.setDataList(downloadedList_show);
        adapter.notifyDataSetChanged();
    }

    /**
     * 获取已下载视频信息
     */
    private void initData() {
        downloadedList_show.clear();

        //通过DownloadManager类的getDownloadedData()接口函数获取已经下载的视频信息
        downloadManager = DownloadManager.getInstance();
        Iterator iter = downloadManager.getDownloadedData().entrySet().iterator();

        while (iter.hasNext()) {
            Entry entry = (Entry) iter.next();
            //视频信息实体类用DownloadInfo表示
            DownloadInfo info = (DownloadInfo) entry.getValue();
            downloadedList_show.add(info);
        }
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        downloadedList_show.clear();
        super.onDestroy();
    }


    @Override
    public void onClick(View view) {

    }
}
