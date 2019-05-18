package com.wd.tech.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wd.tech.R;
import com.wd.tech.data.adapter.CheckGroupAdapter;
import com.wd.tech.data.app.App;
import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.FriendGroupBean;
import com.wd.tech.data.bean.GreenBean;
import com.wd.tech.di.contract.FriendGroupContract;
import com.wd.tech.di.presenter.FriendGroupPresenter;
import com.wd.tech.gen.DaoSession;
import com.wd.tech.gen.GreenBeanDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckGroupActivity extends BaseActivity implements FriendGroupContract.FriendGroupView {


    @BindView(R.id.checkgroup_back)
    ImageView checkgroupBack;
    @BindView(R.id.newGroup)
    RelativeLayout newGroup;
    @BindView(R.id.checkGroupActivity_recycler)
    RecyclerView checkGroupActivityRecycler;

    private int userId;
    private String sessionId;
    private FriendGroupPresenter presenter;
    private int friendId;

    @Override
    protected int initLayout() {
        return R.layout.activity_check_group;
    }

    @Override
    protected void initData() {
        //绑定布局
        ButterKnife.bind(this);
        //取出userId和sessionId
        DaoSession daoSession = App.getDaoSession();
        GreenBeanDao greenBeanDao = daoSession.getGreenBeanDao();
        List<GreenBean> greenBeans = greenBeanDao.loadAll();
        userId = greenBeans.get(0).getUserId();
        sessionId = greenBeans.get(0).getSessionId();
        //实例化p层
        presenter = new FriendGroupPresenter();
        presenter.attahView(this);
        presenter.requestData(userId,sessionId);
        //接收值
        Intent intent = getIntent();
        friendId = intent.getIntExtra("friendId", 0);
    }

    @Override
    protected void initListener() {
        //点击返回
        checkgroupBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //分组展示的回调
    @Override
    public void showData(final FriendGroupBean friendGroupBean) {
        List<FriendGroupBean.ResultBean> result = friendGroupBean.getResult();
        CheckGroupAdapter checkGroupAdapter = new CheckGroupAdapter(R.layout.checkgroup_item_layout,result);
        checkGroupActivityRecycler.setAdapter(checkGroupAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        checkGroupActivityRecycler.setLayoutManager(linearLayoutManager);

        //点击条目
        checkGroupAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                presenter.requestLaHeiFriendData(userId,sessionId,friendGroupBean.getResult().get(position).getGroupId(),friendId);
            }
        });
    }

    //移动好友的成功回调
    @Override
    public void showLaHeiFriendData(AddIngFriendBean addIngFriendBean) {
        if (addIngFriendBean.getStatus().equals("0000")){
            Toast.makeText(this, addIngFriendBean.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deachView(this);
    }
}
