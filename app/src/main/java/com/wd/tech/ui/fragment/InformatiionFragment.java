package com.wd.tech.ui.fragment;

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
import com.wd.tech.data.bean.BannerBean;
import com.wd.tech.data.bean.ShowBean;
import com.wd.tech.di.contract.BannerContract;
import com.wd.tech.di.contract.ShowContract;
import com.wd.tech.di.presenter.BannerPresenter;
import com.wd.tech.di.presenter.ShowPresenter;

import java.util.HashMap;

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
    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.frag_informatiion_layout;
    }

    @Override
    protected void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);



        //https://www.jianshu.com/p/05ce4ab4b948----刷新的简书
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();
            }
        });

        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadMore(true);

//banner的p层
        bannerPresenter = new BannerPresenter();
        bannerPresenter.attrView(this);
        bannerPresenter.requestData();

        showPresenter = new ShowPresenter();
        showPresenter.attrView(this);
        String sessionId="1557738582381468";
        HashMap<String,String> params = new HashMap<>();
        params.put("plated","1");
        params.put("page","1");
        params.put("count","10");
        showPresenter.requestData(params,468+"",sessionId+"");

        return rootView;
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
                break;
            case R.id.home_search:
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

    }
}
