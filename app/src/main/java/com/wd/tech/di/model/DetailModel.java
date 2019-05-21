package com.wd.tech.di.model;

import android.util.Log;

import com.wd.tech.data.bean.DetailOkBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.DetailContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DetailModel implements DetailContract.DetailModel {

    @Override
    public void cotainData(int userId, String sessionId,int id, final Callback callBack) {
        Observable<DetailOkBean> detailOk = RetrofitUtils.getInstance().create(ApiService.class).getDetailOk(userId, sessionId, id);
        detailOk.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DetailOkBean>() {
                    @Override
                    public void accept(DetailOkBean detailOkBean) throws Exception {
                        callBack.Callback(detailOkBean);
                        Log.e("tag1","11"+detailOkBean.getMessage());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("wwwwwww",throwable.getMessage());
                    }
                });
    }
}
