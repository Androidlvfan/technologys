package com.wd.tech.di.model;

import com.wd.tech.data.bean.ViewMoreBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.ViewMoreContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ViewMoreModel implements ViewMoreContract.ViewMoreModel{

    @Override
    public void containData(int communityId,int page, int count, final CallBack callBack) {
        Observable<ViewMoreBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getViewMore(communityId,page,count);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ViewMoreBean>() {
                    @Override
                    public void accept(ViewMoreBean viewMoreBean) throws Exception {
                        callBack.onCallBack(viewMoreBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

}
