package com.wd.tech.data.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.data.bean.ChatRecordBean;
import com.wd.tech.data.utils.RsaCoder;
import com.wd.tech.data.utils.TimeUtil;

import java.util.List;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/17 16:14
 * @fileName:ChatRecordsAdapter
 * @packageName:com.wd.tech.data.adapter
 */
public class ChatRecordsAdapter extends BaseQuickAdapter<ChatRecordBean.ResultBean,BaseViewHolder> {


    public ChatRecordsAdapter(int layoutResId, @Nullable List<ChatRecordBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChatRecordBean.ResultBean item) {
        //姓名
        helper.setText(R.id.chat_name,item.getNickName());
        //图片
        ImageView icon = helper.getView(R.id.chat_img);
        Glide.with(mContext).load(item.getPicUrl()).into(icon);
        //时间
        helper.setText(R.id.chat_tiem,TimeUtil.stampToTime(item.getChatTime()));
        //内容
        try {
            helper.setText(R.id.chat_content,RsaCoder.decryptByPublicKey(item.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
