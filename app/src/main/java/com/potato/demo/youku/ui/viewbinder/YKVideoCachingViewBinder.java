package com.potato.demo.youku.ui.viewbinder;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.potato.demo.R;
import com.potato.demo.databinding.ItemYkVideoDownloadingBinding;
import com.potato.chips.base.BaseViewBinder;
import com.potato.chips.base.BaseViewHolder;
import com.potato.chips.util.ImageLoaderUtil;
import com.youku.service.download.DownloadInfo;
import com.youku.service.download.DownloadManager;

/**
 * Created by ztw on 2015/9/8.
 */
public class YKVideoCachingViewBinder extends BaseViewBinder<YKVideoCachingViewBinder.ViewHolder> {

    Handler mHandler;
    DownloadManager downloadManager;

    public YKVideoCachingViewBinder(Handler handler) {
        this.mHandler = handler;
        downloadManager = DownloadManager.getInstance();
    }

    @Override
    public ViewHolder onCreateViewHolder(int position, int type, ViewGroup parent) {

        ItemYkVideoDownloadingBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_yk_video_downloading,
                parent,
                false);
        ViewHolder holder = new ViewHolder(binding.getRoot());
        holder.setBinding(binding);

        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, Object object, int position, int type) {
        final ItemYkVideoDownloadingBinding binding = (ItemYkVideoDownloadingBinding) holder.getBinding();
        final DownloadInfo info = (DownloadInfo) object;

        TextView tv_title = binding.title;                    //视频标题
        final Button btn_pause_continue = binding.pause;       //暂停/继续 按钮
        Button btn_cancel = binding.cancel;            //须消当前下载任务按钮

        //展示视频标题
        tv_title.setText(info.title);

        if (info.state == DownloadInfo.STATE_DOWNLOADING) {                            //当前视频的下载状态：正在下载
            btn_pause_continue.setText("正在下载");
        } else if (info.state == DownloadInfo.STATE_PAUSE) {                                //当前视频的下载状态：暂停中
            btn_pause_continue.setText("暂停中");
        } else if (info.state == DownloadInfo.STATE_INIT                                //当前视频的下载状态：等待中
                || info.state == DownloadInfo.STATE_EXCEPTION
                || info.state == DownloadInfo.STATE_WAITING) {
            btn_pause_continue.setText("等待中");
        }


//            ImageLoaderUtil.displayImage(info.savePath, binding.ivPic, R.drawable.def_gray_small);
        ImageLoaderUtil.displayImage("file://" + info.savePath + "1.png", binding.ivPic, R.drawable.def_gray_small);

        //单击暂停/继续按钮改变当前下载任务的状态
        btn_pause_continue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (info.state == DownloadInfo.STATE_DOWNLOADING
                        || info.state == DownloadInfo.STATE_WAITING
                        || info.state == DownloadInfo.STATE_INIT
                        || info.state == DownloadInfo.STATE_EXCEPTION) {

                    //处于以上几种状态时单击进行暂停任务
                    downloadManager.pauseDownload(info.taskId);

                } else if (info.state == DownloadInfo.STATE_PAUSE) {
                    //处于暂停状态时单击进行继续任务
                    downloadManager.startDownload(info.taskId);

                }

                mHandler.sendEmptyMessageDelayed(0, 200);
            }
        });

        //取消当前下载任务
        btn_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new AsyncTask<Void, Void, Boolean>() {

                    @Override
                    protected Boolean doInBackground(Void... params) {
                        // TODO Auto-generated method stub
                        //通过DonwloadManager类的deleteDownloading()接口函数删除所限泽的正在下载的任务
                        return downloadManager.deleteDownloading(info.taskId);
                    }

                }.execute();
                //通知更新
                mHandler.sendEmptyMessageDelayed(0, 100);
            }


        });


    }

    public static class ViewHolder extends BaseViewHolder {

        private ViewDataBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }
    }

}
