package com.wd.tech.ui.activity;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wd.tech.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllCommentActivity extends BaseActivity {

    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.image_headpic)
    SimpleDraweeView imageHeadpic;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_numcomment)
    TextView txtNumcomment;
    @BindView(R.id.recy_comment)
    RecyclerView recyComment;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;

    @Override
    protected int initLayout() {
        return R.layout.activity_all_comment;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);



    }

    @Override
    protected void initListener() {

    }




}
