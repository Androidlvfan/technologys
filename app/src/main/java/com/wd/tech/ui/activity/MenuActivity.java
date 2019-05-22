package com.wd.tech.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.data.adapter.MenuAdapter;
import com.wd.tech.data.bean.MenuBean;
import com.wd.tech.di.contract.MenuContract;
import com.wd.tech.di.presenter.MenuPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends BaseActivity implements MenuContract.MenuView {

    @BindView(R.id.menu_rcv)
    RecyclerView menuRcv;
    private MenuAdapter menuAdapter;
    private MenuPresenter menuPresenter;

    @Override
    protected int initLayout() {
        return R.layout.activity_menu;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        menuPresenter = new MenuPresenter();
        menuPresenter.attrView(this);
        menuPresenter.requestData();
    }

    @Override
    protected void initListener() {

    }

    /**
     * 菜单展示成功
     * @param menuBean
     */
    @Override
    public void ShowData(MenuBean menuBean) {
        menuAdapter = new MenuAdapter(this,menuBean.getResult());
        menuRcv.setAdapter(menuAdapter);
        menuRcv.setLayoutManager(new GridLayoutManager(this,2));
    }
}
