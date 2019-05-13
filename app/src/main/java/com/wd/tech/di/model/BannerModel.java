package com.wd.tech.di.model;

import android.util.Log;

import com.wd.tech.data.bean.BannerBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.BannerContract;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BannerModel implements BannerContract.BannerModel {

    @Override
    public void cotainData(final CallBack callBack) {
        Observable<BannerBean> banner = RetrofitUtils.getInstance().create(ApiService.class).getBanner();
        banner.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BannerBean>() {
                    @Override
                    public void accept(BannerBean bannerBean) throws Exception {
                        callBack.onCallBack(bannerBean);
                        Log.e("tag",""+bannerBean.getMessage());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
