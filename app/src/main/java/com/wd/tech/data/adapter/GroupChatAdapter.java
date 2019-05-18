package com.wd.tech.data.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.data.app.App;
import com.wd.tech.data.bean.GreenBean;
import com.wd.tech.data.bean.GroupChatRecordBean;
import com.wd.tech.data.utils.RsaCoder;
import com.wd.tech.gen.DaoSession;
import com.wd.tech.gen.GreenBeanDao;
import com.wd.tech.ui.activity.GroupChatActivity;

import java.util.List;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/17 19:55
 * @fileName:GroupChatAdapter
 * @packageName:com.wd.tech.data.adapter
 */
public class GroupChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<GroupChatRecordBean.ResultBean> result;
    private Context context;
    private int x = 1;
    private int y = 2;
    private int userId;
    private String sessionId;


    public GroupChatAdapter(List<GroupChatRecordBean.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        if (i == x){
            view = View.inflate(context, R.layout.left_dialog_item, null);
            holder = new ViewHolder1(view);
        }else {
            view = View.inflate(context, R.layout.right_dialog_item, null);
            holder = new ViewHolder2(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ViewHolder1 ){
            ((ViewHolder1) viewHolder).lefticon.setImageURI(result.get(i).getHeadPic());
            try {
                String decrypt = RsaCoder.decryptByPublicKey(result.get(i).getChatContent());
                ((ViewHolder1) viewHolder).lefttext.setText(decrypt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            ((ViewHolder2) viewHolder).righticon.setImageURI(result.get(i).getHeadPic());
            try {
                ((ViewHolder2) viewHolder).righttext.setText(RsaCoder.decryptByPublicKey(result.get(i).getChatContent()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        //取出userId和sessionId
        DaoSession daoSession = App.getDaoSession();
        GreenBeanDao greenBeanDao = daoSession.getGreenBeanDao();
        List<GreenBean> greenBeans = greenBeanDao.loadAll();
        userId = greenBeans.get(0).getUserId();
        sessionId = greenBeans.get(0).getSessionId();
        if (result.get(position).getUserId() == userId){
            return y;
        }else {
            return x;
        }

    }
    class ViewHolder1 extends RecyclerView.ViewHolder{

        private final SimpleDraweeView lefticon;
        private final TextView lefttext;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            lefticon = itemView.findViewById(R.id.leftDialogItem_headImg);
            lefttext = itemView.findViewById(R.id.leftDialogItem_text);
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder{

        private final SimpleDraweeView righticon;
        private final TextView righttext;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            righticon = itemView.findViewById(R.id.rightDialogItem_headImg);
            righttext = itemView.findViewById(R.id.rightDialogItem_text);
        }
    }
    @Override
    public int getItemCount() {
        return result.size();
    }
}