package com.wd.tech.di.model;

import com.wd.tech.data.bean.RegisterBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.RegisterContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 20:29
 * @fileName:RegisterModel
 * @packageName:com.wd.tech.di.model
 */
public class RegisterModel implements RegisterContract.RegisterModel {
    @Override
    public void containData(String phone, String nickName, String pwd, final CallBack callBack) {
        Observable<RegisterBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getRegisterData(phone, nickName, pwd);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        callBack.onCallBack(registerBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
