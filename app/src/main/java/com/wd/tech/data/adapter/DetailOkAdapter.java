package com.wd.tech.data.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.wd.tech.R;
import com.wd.tech.data.bean.DetailCommentBean;
import com.wd.tech.data.bean.DetailOkBean;

import java.util.List;

public class DetailOkAdapter extends RecyclerView.Adapter<DetailOkAdapter.OK> {

    private Context context;
    private List<DetailCommentBean.ResultBean> resultBean;
    private DetailOkBean detailOkBean;

    public DetailOkAdapter(Context context) {
        this.context = context;

    }
    public void setComentBean(List<DetailCommentBean.ResultBean> resultBean){
        this.resultBean = resultBean;//评论的构造器
    }

    public void setResultBean(DetailOkBean detailOkBean) {
        this.detailOkBean = detailOkBean;//请求成功的详情页的构造器
    }

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

        WebSettings settings = ok.webView.getSettings();
        settings.setSupportZoom(true);//支持缩放
        settings.setUseWideViewPort(true);//支持扩大比例的缩放
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//自适应屏幕
        settings.setLoadWithOverviewMode(true);

        //设置webView
        ok.webView.setWebChromeClient(new WebChromeClient());
        settings.setJavaScriptEnabled(true);

        settings.setTextSize(WebSettings.TextSize.NORMAL);

        ok.webView.loadDataWithBaseURL(null,detailOkBean.getResult().getContent(),"text/html","utf-8",null);

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
