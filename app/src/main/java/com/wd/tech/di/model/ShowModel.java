package com.wd.tech.di.model;

import android.util.Log;

import com.wd.tech.data.bean.ShowBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.ShowContract;
import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ShowModel implements ShowContract.ShowModel {

    @Override
    public void cotainData(HashMap<String, String> params, String userId, String sessionId, final CallBack callBack) {
        Observable<ShowBean> show = RetrofitUtils.getInstance().create(ApiService.class).getShow(params, userId, sessionId);
        show.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShowBean>() {
                    @Override
                    public void accept(ShowBean showBean) throws Exception {
                        callBack.onCallBack(showBean);
                        Log.e("tag",""+showBean.getMessage());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
