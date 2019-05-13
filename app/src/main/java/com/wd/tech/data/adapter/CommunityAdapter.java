package com.wd.tech.data.adapter;


import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.data.bean.CommunityBean;

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
    }

    @Override
    public int getItemCount() {
        return resultBeans.size();
    }

    public class MyView extends RecyclerView.ViewHolder{

        private final SimpleDraweeView community_list_headpic;
        private final TextView community_list_nickname;

        public MyView(@NonNull View itemView) {
            super(itemView);
            community_list_headpic = itemView.findViewById(R.id.community_list_headpic);
            community_list_nickname = itemView.findViewById(R.id.community_list_nickname);
        }
    }
}
