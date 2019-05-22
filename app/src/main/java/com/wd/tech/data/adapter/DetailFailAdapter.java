package com.wd.tech.data.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.wd.tech.R;
import com.wd.tech.data.bean.DetailCommentBean;
import com.wd.tech.data.bean.DetailOkBean;
import java.util.List;

public class DetailFailAdapter extends RecyclerView.Adapter<DetailFailAdapter.FAIL> {

    private Context context;
    private List<DetailCommentBean.ResultBean> detailCommentBean;

    public DetailFailAdapter(Context context,List<DetailCommentBean.ResultBean> detailCommentBean) {
        this.context = context;
        this.detailCommentBean = detailCommentBean;
    }

    @NonNull
    @Override
    public FAIL onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.detail_fail_item, viewGroup, false);
        return new FAIL(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FAIL fail, int i) {

        DetailCommentAdapter detailCommentAdapter = new DetailCommentAdapter(context,detailCommentBean);
        fail.detail_fail_comment_rcv.setAdapter(detailCommentAdapter);
        fail.detail_fail_comment_rcv.setLayoutManager(new LinearLayoutManager(context));

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class FAIL extends RecyclerView.ViewHolder {
        private Button detail_fail_but;
        private RecyclerView detail_fail_comment_rcv;
        public FAIL(@NonNull View itemView) {
            super(itemView);
            detail_fail_but = itemView.findViewById(R.id.detail_fail_but);
            detail_fail_comment_rcv = itemView.findViewById(R.id.detail_fail_comment_rcv);//评论
        }
    }
}
