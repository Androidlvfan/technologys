package com.wd.tech.data.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.wd.tech.R;
import com.wd.tech.data.bean.DetailCommentBean;
import com.wd.tech.data.bean.DetailOkBean;

import java.util.List;

public class DetailOkAdapter extends RecyclerView.Adapter<DetailOkAdapter.OK> {

    private Context context;
    private List<DetailCommentBean.ResultBean> resultBean;
    private DetailOkBean detailOkBean;

    public DetailOkAdapter(Context context,List<DetailCommentBean.ResultBean> resultBean) {
        this.context = context;
        this.resultBean = resultBean;
    }
/*
    public void setResultBean(List<DetailCommentBean.ResultBean> resultBean) {
        this.resultBean = resultBean;
    }*/

    @NonNull
    @Override
    public OK onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.detail_ok_item, viewGroup, false);
        return new OK(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OK ok, int i) {
        DetailCommentAdapter detailCommentAdapter = new DetailCommentAdapter(context,resultBean);
        ok.detail_ok_comment_rcv.setAdapter(detailCommentAdapter);
        ok.detail_ok_comment_rcv.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class OK extends RecyclerView.ViewHolder {
        private WebView webView;
        private RecyclerView detail_ok_comment_rcv,detail_ok_recomment_rcv;
        public OK(@NonNull View itemView) {
            super(itemView);
            webView = itemView.findViewById(R.id.webView);
            detail_ok_comment_rcv = itemView.findViewById(R.id.detail_ok_comment_rcv);//评论
            detail_ok_recomment_rcv = itemView.findViewById(R.id.detail_ok_recomment_rcv);//推荐
        }
    }
}
