package com.potato.demo.youku.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.potato.chips.common.PageCtrl;
import com.potato.chips.util.ImageLoaderUtil;
import com.potato.demo.R;
import com.potato.demo.databinding.ItemYkVideoCachedBinding;
import com.potato.library.adapter.BaseListAdapter;
import com.potato.library.adapter.BaseViewHolder;
import com.youku.service.download.DownloadInfo;

/**
 * Created by ztw on 2015/9/8.
 */
public  class YKVideoCachedAdapter extends BaseListAdapter {


    public YKVideoCachedAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemYkVideoCachedBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_yk_video_cached,
                parent,
                false);
        VH holder = new VH(binding.getRoot());
        holder.setBinding(binding);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ItemYkVideoCachedBinding binding = (ItemYkVideoCachedBinding) ((VH)holder).getBinding();
        final DownloadInfo bean = (DownloadInfo) mData.get(position);
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


    public static class VH extends BaseViewHolder {

        private ViewDataBinding binding;

        public VH(View itemView) {
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
