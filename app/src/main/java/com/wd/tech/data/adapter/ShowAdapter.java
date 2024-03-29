package com.wd.tech.data.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wd.tech.R;
import com.wd.tech.data.bean.ShowBean;
import com.wd.tech.ui.activity.DetailsActivity;

import java.util.List;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.SHOW> {

    private Context context;
    private List<ShowBean.ResultBean> resultBeanList;

    public ShowAdapter(Context context, List<ShowBean.ResultBean> resultBeanList) {
        this.context = context;
        this.resultBeanList = resultBeanList;
    }

    @NonNull
    @Override
    public SHOW onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_show_item, viewGroup, false);
        return new SHOW(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SHOW show, final int i) {
        Glide.with(context).load(resultBeanList.get(i).getThumbnail()).into(show.show_item_icon);
        show.show_item_title.setText(resultBeanList.get(i).getTitle());
        show.show_item_title2.setText(resultBeanList.get(i).getSummary());
        show.show_item_nickname.setText(resultBeanList.get(i).getSource());
        show.sellect_num.setText(resultBeanList.get(i).getCollection()+"");
        show.share_num.setText(resultBeanList.get(i).getShare()+"");

        if(resultBeanList.get(i).getWhetherPay() == 1){
            show.money.setVisibility(View.VISIBLE);
        }else{
            show.money.setVisibility(View.GONE);
        }

        //条目点击
        show.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("id",resultBeanList.get(i).getId());
                intent.putExtra("whetherPay",resultBeanList.get(i).getWhetherPay());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (resultBeanList == null){
            return 0;
        }else{
            return resultBeanList.size();
        }
    }

    public class SHOW extends RecyclerView.ViewHolder {
        private ImageView show_item_icon;
        private TextView show_item_title,show_item_title2,show_item_nickname,sellect_num,share_num;
        private CheckBox sellect_cb,share_cb;
        private ImageView money;
        public SHOW(@NonNull View itemView) {
            super(itemView);
            show_item_icon = itemView.findViewById(R.id.show_item_icon);
            show_item_title = itemView.findViewById(R.id.show_item_title);
            show_item_title2 = itemView.findViewById(R.id.show_item_title2);
            show_item_nickname = itemView.findViewById(R.id.show_item_nickname);
            sellect_cb = itemView.findViewById(R.id.sellect_cb);
            sellect_num = itemView.findViewById(R.id.sellect_num);
            share_cb = itemView.findViewById(R.id.share_cb);
            share_num = itemView.findViewById(R.id.share_num);
            money = itemView.findViewById(R.id.money);
        }
    }
}
