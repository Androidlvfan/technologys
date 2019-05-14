package com.wd.tech.data.adapter;


import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.data.app.DataUtils;
import com.wd.tech.data.bean.CommunityBean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.MyView> {

    private Context context;
    private List<CommunityBean.ResultBean> resultBeans ;

    public CommunityAdapter(Context context, List<CommunityBean.ResultBean> resultBeans) {
        this.context = context;
        this.resultBeans = resultBeans;
    }

    @NonNull
    @Override
    public CommunityAdapter.MyView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.iteam_community,viewGroup,false);
        MyView myView = new MyView(view);
        return myView;
    }

    @Override
    public void onBindViewHolder(@NonNull CommunityAdapter.MyView myView, int i) {

        Uri parse = Uri.parse(resultBeans.get(i).getHeadPic());
        myView.community_list_headpic.setImageURI(parse);
        myView.community_list_nickname.setText(resultBeans.get(i).getNickName());

        Date date = new Date();
        date.setTime(resultBeans.get(i).getPublishTime());
        myView.community_list_publishtime.setText(DataUtils.getTimeFormatText(date));
        myView.community_list_signature.setText(resultBeans.get(i).getSignature());

        String file = resultBeans.get(i).getFile();
        String[] split = file.split(",");
        //判断图片是否为空 如果是，就隐藏图片的recyclerview
        if (file.isEmpty()) {
            myView.community_list_grid_view.setVisibility(View.GONE);
        } else {
            myView.community_list_grid_view.setVisibility(View.VISIBLE);
            ImageAdapter imageAdapter = new ImageAdapter(context);

            int imageCount = split.length;
            int colNum;//列数
            if (imageCount == 1) {
                colNum = 1;
            } else if (imageCount == 2 || imageCount >= 4) {
                colNum = 2;
            } else {
                colNum = 3;
            }

            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);


            gridLayoutManager.setSpanCount(colNum);
            imageAdapter.notifyDataSetChanged();
            imageAdapter.addAll(Arrays.asList(split));
            myView.community_list_grid_view.setLayoutManager(gridLayoutManager);
            myView.community_list_grid_view.setAdapter(imageAdapter);


        }
    }

    @Override
    public int getItemCount() {
        return resultBeans.size();
    }

    public class MyView extends RecyclerView.ViewHolder{

        private final SimpleDraweeView community_list_headpic;
        private final TextView community_list_nickname;
        private final TextView community_list_publishtime;
        private final TextView community_list_signature;
        private final ImageView community_list_comment;
        private final RecyclerView community_list_grid_view;


        public MyView(@NonNull View itemView) {
            super(itemView);
            community_list_headpic = itemView.findViewById(R.id.community_list_headpic);
            community_list_nickname = itemView.findViewById(R.id.community_list_nickname);
            community_list_publishtime = itemView.findViewById(R.id.community_list_publishtime);
            community_list_signature = itemView.findViewById(R.id.community_list_signature);
            community_list_comment = itemView.findViewById(R.id.community_list_comment);
            community_list_grid_view = itemView.findViewById(R.id.community_list_grid_view);

        }
    }
}
