package com.potato.demo.youku.ui.viewbinder;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.potato.demo.R;
import com.potato.demo.databinding.ItemYkVideoCachedBinding;
import com.potato.chips.base.BaseViewBinder;
import com.potato.chips.base.BaseViewHolder;
import com.potato.chips.common.PageCtrl;
import com.potato.chips.util.ImageLoaderUtil;
import com.youku.service.download.DownloadInfo;

/**
 * Created by ztw on 2015/9/8.
 */
public class YKVideoCachedViewBinder extends BaseViewBinder<YKVideoCachedViewBinder.ViewHolder> {

    public YKVideoCachedViewBinder() {
    }

    @Override
    public ViewHolder onCreateViewHolder(int position, int type, ViewGroup parent) {

        ItemYkVideoCachedBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_yk_video_cached,
                parent,
                false);
        ViewHolder holder = new ViewHolder(binding.getRoot());
        holder.setBinding(binding);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, Object object, int position, int type) {
        final ItemYkVideoCachedBinding binding = (ItemYkVideoCachedBinding) holder.getBinding();
        final DownloadInfo bean = (DownloadInfo) object;
        //展示视频标题
        binding.videoTitle.setText(bean.title);

        ImageLoaderUtil.displayImage("file://" + bean.savePath + "1.png", binding.ivPic, R.drawable.def_gray_small);

        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PageCtrl.startYKPlayerActivity(binding.getRoot().getContext(), bean.videoid, bean.title);

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
