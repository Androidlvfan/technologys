package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.MenuBean;
import com.wd.tech.di.contract.MenuContract;
import com.wd.tech.di.model.MenuModel;

import java.lang.ref.SoftReference;

public class MenuPresenter implements MenuContract.MenuPresenter {

    private MenuContract.MenuView menuView;
    private MenuModel menuModel;
    private SoftReference<MenuContract.MenuView> reference;
    @Override
    public void attrView(MenuContract.MenuView menuView) {
        this.menuView = menuView;
        reference = new SoftReference<>(menuView);
        menuModel = new MenuModel();
    }

    @Override
    public void deachView(MenuContract.MenuView menuView) {
        reference.clear();
    }

    @Override
    public void requestData() {
        menuModel.cotainData(new MenuContract.MenuModel.CallBack() {
            @Override
            public void onCallBack(MenuBean menuBean) {
                menuView.ShowData(menuBean);
            }
        });
    }
}
