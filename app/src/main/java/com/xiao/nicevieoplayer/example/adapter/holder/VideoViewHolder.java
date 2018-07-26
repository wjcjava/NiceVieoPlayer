package com.xiao.nicevieoplayer.example.adapter.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.xiao.nicevideoplayer.Clarity;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.TxVideoPlayerController;
import com.xiao.nicevieoplayer.R;
import com.xiao.nicevieoplayer.example.bean.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoJianjun on 2017/5/21.
 */

public class VideoViewHolder extends RecyclerView.ViewHolder {


    public TxVideoPlayerController mController;
    public NiceVideoPlayer mVideoPlayer;



    public VideoViewHolder(View itemView) {
        super(itemView);

        mVideoPlayer = (NiceVideoPlayer) itemView.findViewById(R.id.nice_video_player);
        mVideoPlayer.setPlayerType(NiceVideoPlayer.TYPE_IJK);
       //  将列表中的每个视频设置为默认16:9的比例
        ViewGroup.LayoutParams params = mVideoPlayer.getLayoutParams();
        params.width = itemView.getResources().getDisplayMetrics().widthPixels; // 宽度为屏幕宽度
        params.height = (int) (params.width * 9f / 16f);    // 高度为宽度的9/16
        mVideoPlayer.setLayoutParams(params);
    }

    public void setController(TxVideoPlayerController controller) {
        mController = controller;

        mVideoPlayer.setController(mController);
    }

    public void bindData(Video video) {

        mController.setTitle(video.getTitle());
        mController.setLenght(video.getLength());
        mController.setClarity(getClarites(video), 0);
        Glide.with(itemView.getContext())
                .load(video.getImageUrl())
                .placeholder(R.drawable.img_default)
                .crossFade()
                .into(mController.imageView());
        mVideoPlayer.setController(mController);
       // mVideoPlayer.setUp(video.getVideoUrl(), null);
    }

    public List<Clarity> getClarites(Video video) {
            List<Clarity> clarities = new ArrayList<>();
            clarities.add(new Clarity("标清", "270P", video.getVideoUrl()));
            clarities.add(new Clarity("高清", "480P", video.getVideoUrl()));
            clarities.add(new Clarity("超清", "720P", video.getVideoUrl()));
            clarities.add(new Clarity("蓝光", "1080P", video.getVideoUrl()));

        return clarities;
    }

}
