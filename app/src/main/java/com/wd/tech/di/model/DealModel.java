package com.wd.tech.di.model;

import com.wd.tech.data.bean.DealBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.DealContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

public class DealModel implements DealContract.DealModel{

    @Override
    public void containData(int userId, String sessionId, String content, MultipartBody multipartBody, final CallBack callBack) {
        Observable<DealBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getReleasePost(userId,sessionId,content,multipartBody);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DealBean>() {
                    @Override
                    public void accept(DealBean dealBean) throws Exception {
                        callBack.onCallBack(dealBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

}
