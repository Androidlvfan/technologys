package com.wd.tech.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wd.tech.R;
import com.wd.tech.data.adapter.ViewMoreAdapter;
import com.wd.tech.data.bean.ViewMoreBean;
import com.wd.tech.di.contract.ViewMoreContract;
import com.wd.tech.di.presenter.ViewMorePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllCommentActivity extends BaseActivity implements ViewMoreContract.ViewMoreView {

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
    private ViewMorePresenter viewMorePresenter;
    private int communityId1;
    private int comment1;
    private String headPic1;
    private String nickName1;

    @Override
    protected int initLayout() {
        return R.layout.activity_all_comment;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);

        Intent intent = getIntent();
        communityId1 = intent.getExtras().getInt("communityId");
        comment1 = intent.getExtras().getInt("comment");
        headPic1 = intent.getStringExtra("headPic");
        nickName1 = intent.getStringExtra("nickName");

        viewMorePresenter = new ViewMorePresenter();
        viewMorePresenter.attahView(this);
        viewMorePresenter.requestData(communityId1,1,10);

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    @Override
    protected void initListener() {
        //txtNumcomment.setText(comment1);
        imageHeadpic.setImageURI(headPic1);
        txtName.setText(nickName1);
    }


    @Override
    public void showData(ViewMoreBean viewMoreBean) {

        List<ViewMoreBean.ResultBean> result = viewMoreBean.getResult();
        ViewMoreAdapter viewMoreAdapter = new ViewMoreAdapter(AllCommentActivity.this);
        viewMoreAdapter.setAll(result);
        recyComment.setLayoutManager(new LinearLayoutManager(AllCommentActivity.this));
        recyComment.setAdapter(viewMoreAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewMorePresenter.deachView(this);
    }
}
