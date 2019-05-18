package com.wd.tech.data.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wd.tech.R;
import com.wd.tech.data.bean.FriendGroupBean;

import java.util.List;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/16 21:38
 * @fileName:CheckGroupAdapter
 * @packageName:com.wd.tech.data.adapter
 */
public class CheckGroupAdapter extends BaseQuickAdapter<FriendGroupBean.ResultBean,BaseViewHolder> {

    public CheckGroupAdapter(int layoutResId, @Nullable List<FriendGroupBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendGroupBean.ResultBean item) {
        helper.setText(R.id.checkGroupRecyclerItem_groupName,item.getGroupName());
    }
}
