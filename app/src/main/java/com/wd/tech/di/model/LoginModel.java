package com.wd.tech.di.model;

import android.util.Log;

import com.wd.tech.data.bean.LoginBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.LoginContractClass;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginModel implements LoginContractClass.LoginModel{

    @Override
    public void containData(String phone, String pwd, final CallBack callBack) {
        Observable<LoginBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getLogin(phone,pwd);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean loginBean) throws Exception {
                        callBack.onCallBack(loginBean);
                        Log.e("tag",""+loginBean.getMessage());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

}
