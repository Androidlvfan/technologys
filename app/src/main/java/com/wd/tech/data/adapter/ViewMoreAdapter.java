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
import com.wd.tech.data.app.DataUtils;
import com.wd.tech.data.bean.ViewMoreBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * date: 2019/3/5.
 * Created 王思敏
 * function:
 */
public class ViewMoreAdapter extends RecyclerView.Adapter<ViewMoreAdapter.ViewHolder> {

    private Context context;
    private List<ViewMoreBean.ResultBean> list;

    public ViewMoreAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.allcomment_recy, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.mSdvHead.setImageURI(Uri.parse(list.get(i).getHeadPic()));
        viewHolder.mTxtName.setText(list.get(i).getNickName());
        Date date = new Date();
        date.setTime(list.get(i).getCommentTime());
        viewHolder.mTxtTime.setText(DataUtils.getTimeFormatText(date));
        viewHolder.mTxtContent.setText(list.get(i).getContent());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setAll(List<ViewMoreBean.ResultBean> result) {
        if (result !=null){
            list.addAll(result);
        }
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView mSdvHead;
        private final TextView mTxtName;
        private final TextView mTxtTime;
        private final TextView mTxtContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mSdvHead = itemView.findViewById(R.id.sdv_head);
            mTxtName = itemView.findViewById(R.id.txt_name);
            mTxtTime = itemView.findViewById(R.id.txt_time);
            mTxtContent = itemView.findViewById(R.id.txt_content);
        }
    }
}
