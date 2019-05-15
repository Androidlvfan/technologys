package com.wd.tech.data.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.data.bean.GroupBean;

import java.util.List;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/15 10:32
 * @fileName:GroupsByUserAdapter
 * @packageName:com.wd.tech.data.adapter
 */
public class GroupsByUserAdapter extends BaseQuickAdapter<GroupBean.ResultBean,BaseViewHolder> {


    public GroupsByUserAdapter(int layoutResId, @Nullable List<GroupBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GroupBean.ResultBean item) {
        helper.setText(R.id.find_groups_adapter_name,item.getGroupName());
        SimpleDraweeView icon = helper.getView(R.id.find_groups_adapter_icon);
        icon.setImageURI(item.getGroupImage());
    }

}
