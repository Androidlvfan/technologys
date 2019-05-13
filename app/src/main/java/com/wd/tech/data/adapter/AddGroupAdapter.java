package com.wd.tech.data.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.data.bean.AddGroupBean;

import java.util.List;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/13 16:39
 * @fileName:AddGroupAdapter
 * @packageName:com.wd.tech.data.adapter
 */
public class AddGroupAdapter extends BaseQuickAdapter<AddGroupBean,BaseViewHolder> {

    public AddGroupAdapter(int layoutResId, @Nullable List<AddGroupBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddGroupBean item) {

        Glide.with(mContext).load(item.getResult().getGroupImage()).into((ImageView) helper.getView(R.id.FindGroupRecycler_img));
        helper.setText(R.id.FindGroup_name,item.getResult().getGroupName());
    }
}
