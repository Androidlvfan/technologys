package com.wd.tech.data.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.data.bean.SerachByTitleBean;
import com.wd.tech.ui.activity.DetailsActivity;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SEARCH> {

   private Context context;
   private List<SerachByTitleBean.ResultBean> resultBeanList;
   private int whetherPay = 1;

    public SearchAdapter(Context context, List<SerachByTitleBean.ResultBean> resultBeanList) {
        this.context = context;
        this.resultBeanList = resultBeanList;
    }

    @NonNull
    @Override
    public SEARCH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_item, viewGroup, false);
        return new SEARCH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SEARCH search, final int i) {
        search.search_item_title.setText(resultBeanList.get(i).getTitle());
        search.search_item_source.setText(resultBeanList.get(i).getSource());
        search.search_item_time.setText(resultBeanList.get(i).getReleaseTime()+"");

        search.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
               intent.putExtra("id",resultBeanList.get(i).getId());
               intent.putExtra("whetherPay",whetherPay);
                Log.i("searchId", "" + resultBeanList.get(i).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(resultBeanList == null){
            return 0;
        }else {
            return resultBeanList.size();
        }
    }

    public class SEARCH extends RecyclerView.ViewHolder {
        private TextView search_item_title,search_item_source,search_item_time;
        public SEARCH(@NonNull View itemView) {
            super(itemView);
            search_item_title = itemView.findViewById(R.id.search_item_title);
            search_item_source = itemView.findViewById(R.id.search_item_source);
            search_item_time = itemView.findViewById(R.id.search_item_time);
        }
    }
}
