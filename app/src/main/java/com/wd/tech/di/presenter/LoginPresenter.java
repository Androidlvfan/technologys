package com.wd.tech.di.presenter;

import android.util.Log;

import com.wd.tech.data.bean.LoginBean;
import com.wd.tech.di.contant.LoginContracClass;
import com.wd.tech.di.model.LoginModel;

import java.lang.ref.SoftReference;

public class LoginPresenter implements LoginContracClass.Loginresenter {

    private LoginContracClass.LoginView loginView;
    private SoftReference<LoginContracClass.LoginView> reference;
    private LoginModel model;

    @Override
    public void attahView(LoginContracClass.LoginView loginView) {
        this.loginView = loginView;
        reference = new SoftReference<>(loginView);
        model = new LoginModel();
    }

    @Override
    public void deachView(LoginContracClass.LoginView loginView) {
        reference.clear();
    }

    @Override
    public void requestData(String phone,String pwd) {
        model.containData(phone,pwd,new LoginContracClass.LoginModel.CallBack() {
            @Override
            public void onCallBack(LoginBean loginBean) {
                loginView.showData(loginBean);
                Log.e("tag",""+loginBean.getMessage());
            }
        });
    }
}


