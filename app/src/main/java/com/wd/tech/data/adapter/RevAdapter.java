package com.wd.tech.data.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.wd.tech.R;
import com.wd.tech.data.bean.BannerBean;
import com.wd.tech.data.bean.ShowBean;

import java.util.ArrayList;
import java.util.List;

public class RevAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ShowBean.ResultBean> showBean;
    private List<BannerBean.ResultBean> bannerBean;

    public RevAdapter(Context context) {
        this.context = context;
    }

    public void setShowAdapter(List<ShowBean.ResultBean> showBean) {
        this.showBean = showBean;
        notifyDataSetChanged();
    }

    public void setBannerAdapter(List<BannerBean.ResultBean> bannerBean) {
        this.bannerBean = bannerBean;
        notifyDataSetChanged();
    }
    private List<String> imgs = new ArrayList<>();
    private List<String> title = new ArrayList<>();
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i == 0){
            View view = LayoutInflater.from(context).inflate(R.layout.main_banner, viewGroup, false);
            Banner banner = new Banner(view);
            return banner;
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.main_show, viewGroup, false);
            Show show = new Show(view);
            return show;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof Banner){
            for (BannerBean.ResultBean bean : bannerBean) {
                imgs.add(bean.getImageUrl());
                title.add(bean.getTitle());
            }

            ((Banner) viewHolder).main_nav_banner.setData(imgs,title);
           // ((Banner) viewHolder).banner_title.setText(title+"");
            ((Banner) viewHolder).main_nav_banner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(context).load(imgs.get(position)).into((ImageView) view);
                }
            });

        }else if(viewHolder instanceof Show){

            ShowAdapter showAdapter = new ShowAdapter(context,showBean);
            ((Show) viewHolder).main_nav_rcv.setAdapter(showAdapter);
            ((Show) viewHolder).main_nav_rcv.setLayoutManager(new LinearLayoutManager(context));

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
       if(position == 0){
           return 0;
       }else{
           return 1;
       }
    }

    /**
     * banner
     */
    class Banner extends RecyclerView.ViewHolder{
        private XBanner main_nav_banner;
        public Banner(@NonNull View itemView) {
            super(itemView);
            main_nav_banner = itemView.findViewById(R.id.main_nav_banner);
        }
    }
    /**
     * show
     */
    class Show extends RecyclerView.ViewHolder{

        private RecyclerView main_nav_rcv;
        public Show(@NonNull View itemView) {
            super(itemView);
            main_nav_rcv = itemView.findViewById(R.id.main_nav_rcv);
        }
    }
}
