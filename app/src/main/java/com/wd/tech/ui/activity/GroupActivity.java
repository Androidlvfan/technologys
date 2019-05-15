package com.wd.tech.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wd.tech.R;
import com.wd.tech.data.adapter.GroupsByUserAdapter;
import com.wd.tech.data.app.App;
import com.wd.tech.data.bean.GreenBean;
import com.wd.tech.data.bean.GroupBean;
import com.wd.tech.di.contract.GroupContract;
import com.wd.tech.di.presenter.GroupPresenter;
import com.wd.tech.gen.DaoSession;
import com.wd.tech.gen.GreenBeanDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupActivity extends BaseActivity implements GroupContract.GroupView {


    @BindView(R.id.group_back)
    ImageView groupBack;
    @BindView(R.id.group_by_search)
    EditText groupBySearch;
    @BindView(R.id.find_groups_recycle)
    RecyclerView findGroupsRecycle;
    @BindView(R.id.groups_joined_recy)
    RecyclerView groupsJoinedRecy;
    @BindView(R.id.group_smartrefresh)
    SmartRefreshLayout groupSmartrefresh;
    private int userId;
    private String sessionId;
    private int page = 1;
    private GroupPresenter groupPresenter;

    @Override
    protected int initLayout() {
        return R.layout.activity_group;
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
        groupPresenter = new GroupPresenter();
        groupPresenter.attahView(this);
        //创建的群聊的请求
        groupPresenter.requestGroupsByUserData(userId,sessionId);
        //加入的群聊的请求
        groupPresenter.requestJoinedGroupData(userId,sessionId);
        //上拉下拉
        smartrefresh();
    }

    private void smartrefresh() {
        //刷新
        groupSmartrefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                groupPresenter.requestGroupsByUserData(userId,sessionId);
                groupPresenter.requestJoinedGroupData(userId,sessionId);
                refreshLayout.finishRefresh(1000);
            }
        });
        //加载
        groupSmartrefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                groupPresenter.requestGroupsByUserData(userId,sessionId);
                groupPresenter.requestJoinedGroupData(userId,sessionId);
                refreshLayout.finishLoadMore(1000);
            }
        });
        // 开始下拉
        groupSmartrefresh.setEnableRefresh(true);//启用刷新
        groupSmartrefresh.setEnableLoadMore(true);//启用加载
        // 关闭下拉
        groupSmartrefresh.finishRefresh();
        groupSmartrefresh.finishLoadMore();
    }

    @Override
    protected void initListener() {
        //点击返回
        groupBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //创建的群聊的回调
    @Override
    public void showGroupsByUserData(GroupBean groupBean) {
        List<GroupBean.ResultBean> result = groupBean.getResult();
        GroupsByUserAdapter groupsByUserAdapter = new GroupsByUserAdapter(R.layout.group_item_layout,result);
        findGroupsRecycle.setAdapter(groupsByUserAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        findGroupsRecycle.setLayoutManager(linearLayoutManager);
    }
    //加入的群聊的回调
    @Override
    public void showJoinedGroupData(GroupBean groupBean) {
        Log.d("GroupActivity", groupBean.getMessage());
        List<GroupBean.ResultBean> result = groupBean.getResult();
        GroupsByUserAdapter groupsByUserAdapter = new GroupsByUserAdapter(R.layout.group_item_layout,result);
        groupsJoinedRecy.setAdapter(groupsByUserAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        groupsJoinedRecy.setLayoutManager(linearLayoutManager);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        groupPresenter.deachView(this);
    }
}
