package com.wd.tech.data.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.data.app.App;
import com.wd.tech.data.bean.FriendChatRecordBean;
import com.wd.tech.data.bean.GreenBean;
import com.wd.tech.data.utils.RsaCoder;
import com.wd.tech.gen.DaoSession;
import com.wd.tech.gen.GreenBeanDao;
import com.wd.tech.ui.activity.FriendChatActivity;

import java.util.List;

import cn.jiguang.imui.chatinput.emoji.adapter.EmoticonsAdapter;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/17 21:52
 * @fileName:FriendChatAdapter
 * @packageName:com.wd.tech.data.adapter
 */
public class FriendChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<FriendChatRecordBean.ResultBean> result;
    private Context context;
    private int x =1;
    private int y =2;
    private int userId;
    private String sessionId;
    public FriendChatAdapter(List<FriendChatRecordBean.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =null;
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
        if (viewHolder instanceof  ViewHolder1){
            try {
                ((ViewHolder1) viewHolder).lefttext.setText(RsaCoder.decryptByPublicKey(result.get(i).getContent()));
                ((ViewHolder1) viewHolder).leftimg.setImageURI(result.get(i).getHeadPic());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {

            try {
                ((ViewHolder2) viewHolder).righttext.setText(RsaCoder.decryptByPublicKey(result.get(i).getContent()));
                ((ViewHolder2) viewHolder).rightimg.setImageURI(result.get(i).getHeadPic());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    @Override
    public int getItemViewType(int position) {
        //取出userId和sessionId
        DaoSession daoSession = App.getDaoSession();
        GreenBeanDao greenBeanDao = daoSession.getGreenBeanDao();
        List<GreenBean> greenBeans = greenBeanDao.loadAll();
        userId = greenBeans.get(0).getUserId();
        sessionId = greenBeans.get(0).getSessionId();
        if (result.get(position).getDirection() == 1){
            return y;
        }else{
            return x;
        }
    }


    class  ViewHolder1  extends  RecyclerView.ViewHolder {

        private final TextView lefttext;
        private final SimpleDraweeView leftimg;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            leftimg = itemView.findViewById(R.id.leftDialogItem_headImg);
            lefttext = itemView.findViewById(R.id.leftDialogItem_text);
        }
    }
    class  ViewHolder2  extends  RecyclerView.ViewHolder {

        private final TextView righttext;
        private final SimpleDraweeView rightimg;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            rightimg = itemView.findViewById(R.id.rightDialogItem_headImg);
            righttext = itemView.findViewById(R.id.rightDialogItem_text);
        }
    }

}
