package com.wd.tech.data.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.data.bean.FriendByGroupIdBean;

import java.util.List;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/16 15:17
 * @fileName:FriendByGroupIdAdapter
 * @packageName:com.wd.tech.data.adapter
 */
public class FriendByGroupIdAdapter extends BaseQuickAdapter<FriendByGroupIdBean.ResultBean,BaseViewHolder> {


    public FriendByGroupIdAdapter(int layoutResId, @Nullable List<FriendByGroupIdBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendByGroupIdBean.ResultBean item) {
        //头像
        SimpleDraweeView icon = helper.getView(R.id.twoListRecyclerItem_headImg);
        icon.setImageURI(item.getHeadPic());
        //名字
        if (item.getRemarkName().equals("")){
            helper.setText(R.id.twoListRecyclerItem_nickName,item.getNickName());
        }else{
            helper.setText(R.id.twoListRecyclerItem_nickName,item.getRemarkName());
        }
        //签名
        helper.setText(R.id.twoListRecyclerItem_qianMing,item.getSignature());

    }
}
