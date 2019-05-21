package com.wd.tech.di.model;

import android.util.Log;

import com.wd.tech.data.bean.CommunityBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.CommunityContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CommunityModel implements CommunityContract.CommunityModel{

    @Override
    public void containData(int page, int count, final CallBack callBack) {
        Observable<CommunityBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getCommunity(page,count);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CommunityBean>() {
                    @Override
                    public void accept(CommunityBean communityBean) throws Exception {
                        callBack.onCallBack(communityBean);
                        Log.e("tag",""+communityBean.getMessage());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

}
