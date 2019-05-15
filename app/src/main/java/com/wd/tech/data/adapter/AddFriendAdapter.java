package com.wd.tech.data.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.data.bean.AddFriendBean;

import java.util.List;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/13 15:13
 * @fileName:AddFriendAdapter
 * @packageName:com.wd.tech.data.adapter
 */
public class AddFriendAdapter extends BaseQuickAdapter<AddFriendBean,BaseViewHolder> {


    public AddFriendAdapter(int layoutResId, @Nullable List<AddFriendBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, AddFriendBean item) {
        //头像
        SimpleDraweeView img = helper.getView(R.id.QueryFriendRecyclerViewItem_img);
//        Uri uri = Uri.parse(item.getResult().getHeadPic());
//        DraweeController controller = Fresco.newDraweeControllerBuilder().setUri(uri).build();
//        img.setController(controller);
        img.setImageURI(Uri.parse(item.getResult().getHeadPic()));
        Log.d(TAG, "aaaaa头像"+item.getResult().getHeadPic());
        //名字

        TextView textView  =helper.getView(R.id.QueryFriendRecyclerViewItem_name);
        textView.setText(item.getResult().getNickName());
    }
}
