package com.wd.tech.di.model;

import com.wd.tech.data.bean.WxBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.WxContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class WxModel implements WxContract.WxModel{

    @Override
    public void containData(int userId,String sessionId, String ak, String code, final CallBack callBack) {
        Observable<WxBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getWx(userId,sessionId,ak,code);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WxBean>() {
                    @Override
                    public void accept(WxBean wxBean) throws Exception {
                        callBack.onCallBack(wxBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

}
