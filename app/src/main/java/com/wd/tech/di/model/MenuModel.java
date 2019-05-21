package com.wd.tech.di.model;

import com.wd.tech.data.bean.MenuBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.MenuContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MenuModel implements MenuContract.MenuModel {
    @Override
    public void cotainData(final CallBack callBack) {
        Observable<MenuBean> menu = RetrofitUtils.getInstance().create(ApiService.class).getMenu();
        menu.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MenuBean>() {
                    @Override
                    public void accept(MenuBean menuBean) throws Exception {
                        callBack.onCallBack(menuBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
