package com.wd.tech.di.model;

import com.wd.tech.data.bean.LikeBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.LikeContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LikeModel implements LikeContract.LikeModel{


    @Override
    public void containData(int userId, String sessionId, int communityId, final CallBack callBack) {
        Observable<LikeBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getLike(userId,sessionId,communityId);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LikeBean>() {
                    @Override
                    public void accept(LikeBean likeBean) throws Exception {
                        callBack.onCallBack(likeBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

}

