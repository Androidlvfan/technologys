package com.wd.tech.data.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.data.bean.FriendNotifyBean;
import com.wd.tech.data.utils.TimeUtil;

import java.util.List;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/15 19:45
 * @fileName:FriendNotifyAdapter
 * @packageName:com.wd.tech.data.adapter
 */
public class FriendNotifyAdapter extends BaseQuickAdapter<FriendNotifyBean.ResultBean,BaseViewHolder> {


    public FriendNotifyAdapter(int layoutResId, @Nullable List<FriendNotifyBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final FriendNotifyBean.ResultBean item) {
        //头像
        SimpleDraweeView icon = helper.getView(R.id.friend_notify_headImg);
        icon.setImageURI(item.getFromHeadPic());
        //名字
        helper.setText(R.id.friend_notify_nickName,item.getFromNickName());
        //时间
        String time = TimeUtil.stampToDate(item.getNoticeTime());
        helper.setText(R.id.friend_notify_time,time);
        //备注
        TextView beizhu = helper.getView(R.id.friend_notify_beizhu);
        //同意 拒绝
        LinearLayout linear = helper.getView(R.id.friend_notify_bottomLinear);
        TextView argee = helper.getView(R.id.friend_notify_agreeBtn);
        TextView Noargee = helper.getView(R.id.friend_notify_NoAgreeBtn);
        //已同意
        TextView alreadyArgee = helper.getView(R.id.friend_notify_alreadyAgree);
        //已拒绝
        TextView NoalreadyArgee = helper.getView(R.id.friend_notify_alreadyNoAgree);
        //状态判断
        switch (item.getStatus()){
            case 1:
                //待处理
                beizhu.setVisibility(View.VISIBLE);
                linear.setVisibility(View.VISIBLE);
                alreadyArgee.setVisibility(View.GONE);
                NoalreadyArgee.setVisibility(View.GONE);
                break;
            case 2:
                //已同意
                beizhu.setVisibility(View.GONE);
                alreadyArgee.setVisibility(View.VISIBLE);
                linear.setVisibility(View.GONE);
                NoalreadyArgee.setVisibility(View.GONE);
                break;
            case 3:
                //拒绝
                beizhu.setVisibility(View.GONE);
                alreadyArgee.setVisibility(View.GONE);
                linear.setVisibility(View.GONE);
                NoalreadyArgee.setVisibility(View.VISIBLE);
                break;
        }
        //点击同意
        argee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onArgeeClickListener.onCallBack(1,item.getNoticeId());
            }
        });
        //点击拒绝
        Noargee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onArgeeClickListener.onCallBack(2,item.getNoticeId());
            }
        });
    }

    GroupNotifyAdapter.OnArgeeClickListener onArgeeClickListener;

    public interface OnArgeeClickListener{
        void onCallBack(int i,int notiftyId);
    }

    public void setOnArgeeClickListener(GroupNotifyAdapter.OnArgeeClickListener onArgeeClickListener){
        this.onArgeeClickListener = onArgeeClickListener;
    }
}
