package com.potato.demo.youku.ui.act;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.potato.chips.base.BaseActivity;
import com.potato.chips.base.BaseListAdapter;
import com.potato.demo.R;
import com.potato.demo.databinding.ActivityYkCachedBinding;
import com.potato.demo.youku.ui.adapter.YKVideoCachedAdapter;
import com.youku.service.download.DownloadInfo;
import com.youku.service.download.DownloadManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * 简单展示已经缓存的视频，用户可定制自己的界面
 *
 */
public class YKCachedActivity extends BaseActivity {
	//展示视频信息的ListView

	//通过DownloadManager获取下载视频列表
	private DownloadManager downloadManager;
	
	//记录当前已经下载的视频列表
	private ArrayList<DownloadInfo> downloadedList_show = new ArrayList<DownloadInfo>();

	//数据Adapter
	private BaseListAdapter adapter;
	private ActivityYkCachedBinding mBinding;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		mBinding = DataBindingUtil.setContentView(
				this, R.layout.activity_yk_cached);


		adapter = new YKVideoCachedAdapter(this);

		mBinding.list.setAdapter(adapter);

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.iv_back:
				finish();
				break;
		}
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
	private void initData(){
		downloadedList_show.clear();
		
		//通过DownloadManager类的getDownloadedData()接口函数获取已经下载的视频信息
		downloadManager = DownloadManager.getInstance();
		Iterator iter = downloadManager.getDownloadedData().entrySet().iterator();
		
		while(iter.hasNext()){
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
		finish();
		super.onDestroy();
	}


}
