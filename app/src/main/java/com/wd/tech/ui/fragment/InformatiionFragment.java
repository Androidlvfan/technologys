package com.wd.tech.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wd.tech.R;
import com.wd.tech.data.adapter.RevAdapter;
import com.wd.tech.data.app.App;
import com.wd.tech.data.bean.BannerBean;
import com.wd.tech.data.bean.GreenBean;
import com.wd.tech.data.bean.ShowBean;
import com.wd.tech.di.contract.BannerContract;
import com.wd.tech.di.contract.ShowContract;
import com.wd.tech.di.presenter.BannerPresenter;
import com.wd.tech.di.presenter.ShowPresenter;
import com.wd.tech.gen.DaoSession;
import com.wd.tech.gen.GreenBeanDao;
import com.wd.tech.ui.activity.MenuActivity;
import com.wd.tech.ui.activity.SearchActivity;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 16:51
 * @fileName:InformatiionFragment
 * @packageName:com.wd.tech.dimensionalitytechnology.ui.fragment
 */
public class InformatiionFragment extends BaseFragment implements BannerContract.BannerView , ShowContract.ShowView {
    @BindView(R.id.home_menu)
    ImageView homeMenu;
    @BindView(R.id.home_search)
    ImageView homeSearch;
    @BindView(R.id.home_nav)
    RelativeLayout homeNav;
    @BindView(R.id.home_rcv)
    RecyclerView homeRcv;
    Unbinder unbinder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private BannerPresenter bannerPresenter;
    private RevAdapter revAdapter;
    private ShowPresenter showPresenter;
    private String sessionId;
    private int userId;
    private int plated = 1;
    private int page = 1;

    @Override
    protected void initData() {
        super.initData();
        //取出userId和sessionId
        DaoSession daoSession = App.getDaoSession();
        GreenBeanDao greenBeanDao = daoSession.getGreenBeanDao();
        List<GreenBean> greenBeans = greenBeanDao.loadAll();
        userId = greenBeans.get(0).getUserId();
        sessionId = greenBeans.get(0).getSessionId();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadMore(true);

//banner的p层
        bannerPresenter = new BannerPresenter();
        bannerPresenter.attrView(this);
        bannerPresenter.requestData();
//show的p层
        showPresenter = new ShowPresenter();
        showPresenter.attrView(this);
        HashMap<String,String> params = new HashMap<>();
        params.put("plated",plated+"");
        params.put("page",page+"");
        params.put("count","10");
        showPresenter.requestData(params,userId+"",sessionId+"");

        //https://www.jianshu.com/p/05ce4ab4b948----刷新的简书
        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
                revAdapter.notifyDataSetChanged();
                Log.i("TAST","refresh");
            }
        });
        //加载更多
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Log.i("TAST","load");
                plated++;
                page++;
                refreshLayout.finishLoadMore();
                revAdapter.notifyDataSetChanged();//刷新适配器
            }
        });

        return rootView;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.frag_informatiion_layout;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.home_menu, R.id.home_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_menu:
                Intent intent = new Intent(getActivity(), MenuActivity.class);
                startActivity(intent);
                break;
            case R.id.home_search:
                Intent intent1 = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent1);
                break;
        }
    }

    /**
     * banner请求成功
     * @param bannerBean
     */
    @Override
    public void ShowData(BannerBean bannerBean) {

        revAdapter = new RevAdapter(getActivity());//实例化adapter
        revAdapter.setBannerAdapter(bannerBean.getResult());//设置数据
        Log.i("Banner",bannerBean.getResult().get(0).getImageUrl());
        revAdapter.notifyDataSetChanged();//刷新适配器

        homeRcv.setAdapter(revAdapter);
        homeRcv.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    /**
     * 首页展示成功
     * @param showBean
     */
    @Override
    public void ShowData(ShowBean showBean) {
        revAdapter.setShowAdapter(showBean.getResult());
        revAdapter.notifyDataSetChanged();
        homeRcv.setAdapter(revAdapter);
        homeRcv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
