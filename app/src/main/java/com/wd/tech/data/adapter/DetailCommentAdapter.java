package com.wd.tech.data.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wd.tech.R;
import com.wd.tech.data.bean.DetailCommentBean;

import java.util.List;

public class DetailCommentAdapter extends RecyclerView.Adapter<DetailCommentAdapter.COMMENT> {

    private Context context;
    private List<DetailCommentBean.ResultBean> detailCommentBean;

    public DetailCommentAdapter(Context context, List<DetailCommentBean.ResultBean> detailCommentBean) {
        this.context = context;
        this.detailCommentBean = detailCommentBean;
    }

    @NonNull
    @Override
    public COMMENT onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.detail_comment_item, viewGroup, false);
        return new COMMENT(view);
    }

    @Override
    public void onBindViewHolder(@NonNull COMMENT comment, int i) {
        Glide.with(context).load(detailCommentBean.get(i).getHeadPic()).into(comment.detail_comment_icon);
        comment.detail_comment_nickname.setText(detailCommentBean.get(i).getNickName());
        comment.detail_comment_date.setText(detailCommentBean.get(i).getCommentTime()+"");
        comment.detail_comment_summary.setText(detailCommentBean.get(i).getContent());
    }

    @Override
    public int getItemCount() {
        if(detailCommentBean == null){
            return 0;
        }else{
            return detailCommentBean.size();
        }
    }

    public class COMMENT extends RecyclerView.ViewHolder {
        private ImageView detail_comment_icon;
        private TextView detail_comment_nickname,detail_comment_date,detail_comment_summary;
        public COMMENT(@NonNull View itemView) {
            super(itemView);
            detail_comment_icon = itemView.findViewById(R.id.detail_comment_icon);
            detail_comment_nickname = itemView.findViewById(R.id.detail_comment_nickname);
            detail_comment_date = itemView.findViewById(R.id.detail_comment_date);
            detail_comment_summary = itemView.findViewById(R.id.detail_comment_summary);
        }
    }
}
