package com.wd.tech.data.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wd.tech.R;
import com.wd.tech.data.bean.MenuBean;
import com.wd.tech.ui.activity.Menu_menuActivity;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MENU> {

    private Context context;
    private List<MenuBean.ResultBean> resultBeanList;

    public MenuAdapter(Context context, List<MenuBean.ResultBean> resultBeanList) {
        this.context = context;
        this.resultBeanList = resultBeanList;
    }

    @NonNull
    @Override
    public MENU onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.menu_item, viewGroup, false);
        return new MENU(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MENU menu, final int i) {
        Glide.with(context).load(resultBeanList.get(i).getPic()).into(menu.menu_icon);
        menu.menu_title.setText(resultBeanList.get(i).getName());

       //条目点击
        menu.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Menu_menuActivity.class);
                intent.putExtra("id",resultBeanList.get(i).getId());
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

    public class MENU extends RecyclerView.ViewHolder {
        private ImageView menu_icon;
        private TextView menu_title;
        public MENU(@NonNull View itemView) {
            super(itemView);
            menu_icon = itemView.findViewById(R.id.menu_icon);
            menu_title = itemView.findViewById(R.id.menu_title);
        }
    }
}
