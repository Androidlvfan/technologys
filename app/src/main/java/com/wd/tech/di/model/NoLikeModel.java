package com.wd.tech.di.model;

import com.wd.tech.data.bean.NoLikeBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.NoLikeContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NoLikeModel implements NoLikeContract.NoLikeModel{


    @Override
    public void containData(int userId, String sessionId, int communityId, final CallBack callBack) {
        Observable<NoLikeBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getNoLike(userId,sessionId,communityId);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NoLikeBean>() {
                    @Override
                    public void accept(NoLikeBean noLikeBean) throws Exception {
                        callBack.onCallBack(noLikeBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

}

