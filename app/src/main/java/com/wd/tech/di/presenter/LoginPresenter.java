package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.LoginBean;
import com.wd.tech.di.contract.LoginContract;
import com.wd.tech.di.model.LoginModel;

import java.lang.ref.SoftReference;
/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 20:29
 * @fileName:RegisterPresenter
 * @packageName:com.wd.tech.di.presenter
 */
public class LoginPresenter implements LoginContract.LoginPresenter {

    private LoginContract.LoginView loginView;
    private SoftReference<LoginContract.LoginView> reference;
    private LoginModel model;

    @Override
    public void attahView(LoginContract.LoginView loginView) {
        this.loginView = loginView;
        reference = new SoftReference<>(loginView);
        model = new LoginModel();
    }

    @Override
    public void deachView(LoginContract.LoginView loginView) {
        reference.clear();
    }

    @Override
    public void requestData(String phone,String pwd) {
        model.containData(phone, pwd, new LoginContract.LoginModel.CallBack() {
            @Override
            public void onCallBack(LoginBean loginBean) {
                loginView.showData(loginBean);
            }
        });






};

}