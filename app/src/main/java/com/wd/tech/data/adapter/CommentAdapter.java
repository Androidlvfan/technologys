package com.wd.tech.data.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.data.bean.CommunityBean;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyView> {

    Context context ;
    List<CommunityBean.ResultBean.CommunityCommentVoListBean> list;

    public CommentAdapter(Context context, List<CommunityBean.ResultBean.CommunityCommentVoListBean> list) {
        this.context = context;
        this.list = list;
    }

    public void addAll(List<CommunityBean.ResultBean.CommunityCommentVoListBean> lists){
        list.addAll(lists);
    }

    @NonNull
    @Override
    public CommentAdapter.MyView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_recy,viewGroup,false);
        MyView myView = new MyView(view);
        return myView;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.MyView myView, int i) {

        myView.comment_txt_name.setText(list.get(i).getNickName());
        myView.comment_txt_content.setText(list.get(i).getContent());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyView extends RecyclerView.ViewHolder{

        private final TextView comment_txt_name;
        private final TextView comment_txt_content;

        public MyView(@NonNull View itemView) {
            super(itemView);

            comment_txt_name = itemView.findViewById(R.id.comment_txt_name);
            comment_txt_content = itemView.findViewById(R.id.comment_txt_content);
        }
    }
}
