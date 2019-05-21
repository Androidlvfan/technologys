package com.wd.tech.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wd.tech.R;
import com.wd.tech.data.adapter.ShowAdapter;
import com.wd.tech.data.app.App;
import com.wd.tech.data.bean.GreenBean;
import com.wd.tech.data.bean.ShowBean;
import com.wd.tech.di.contract.ShowContract;
import com.wd.tech.di.presenter.ShowPresenter;
import com.wd.tech.gen.DaoSession;
import com.wd.tech.gen.GreenBeanDao;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Menu_menuActivity extends BaseActivity implements ShowContract.ShowView {

    @BindView(R.id.return_page)
    ImageView returnPage;
    @BindView(R.id.menu_menu_search)
    ImageView menuMenuSearch;
    @BindView(R.id.menu_menu_rcv)
    RecyclerView menuMenuRcv;
    @BindView(R.id.menu_menu_swipefresh)
    SmartRefreshLayout menuMenuSwipefresh;


    private ShowPresenter showPresenter;
    private ShowAdapter showAdapter;
    private String sessionId;
    private int userId;
    private int page = 1;

    @Override
    protected int initLayout() {
        return R.layout.activity_menu_menu;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);

        menuMenuSwipefresh.setEnableRefresh(true);
        menuMenuSwipefresh.setEnableLoadMore(true);

        //取出userId和sessionId
        DaoSession daoSession = App.getDaoSession();
        GreenBeanDao greenBeanDao = daoSession.getGreenBeanDao();
        List<GreenBean> greenBeans = greenBeanDao.loadAll();
        userId = greenBeans.get(0).getUserId();
        sessionId = greenBeans.get(0).getSessionId();

        showPresenter = new ShowPresenter();
        showPresenter.attrView(this);

        Intent intent = getIntent();
        int id = intent.getExtras().getInt("id");
        Log.i("Id", "" + id);
        HashMap<String, String> params = new HashMap<>();
        params.put("plated", id + "");
        params.put("page", page + "");
        params.put("count", "10");
        showPresenter.requestData(params, userId + "", sessionId + "");


        //刷新
        menuMenuSwipefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
                showAdapter.notifyDataSetChanged();
                Log.i("TAST","refresh");
            }
        });
        //加载更多
        menuMenuSwipefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Log.i("TAST","load");
                page++;
                refreshLayout.finishLoadMore();
                showAdapter.notifyDataSetChanged();//刷新适配器
            }
        });

        menuMenuSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Menu_menuActivity.this,SearchActivity.class);
                startActivity(intent1);//跳转到搜索的页面
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.return_page, R.id.menu_menu_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.return_page:
                finish();
                break;
            case R.id.menu_menu_search:
                break;
        }
    }

    /**
     * 展示成功
     *
     * @param showBean
     */
    @Override
    public void ShowData(ShowBean showBean) {
        showAdapter = new ShowAdapter(this, showBean.getResult());
        menuMenuRcv.setAdapter(showAdapter);
        menuMenuRcv.setLayoutManager(new LinearLayoutManager(this));
    }

}
