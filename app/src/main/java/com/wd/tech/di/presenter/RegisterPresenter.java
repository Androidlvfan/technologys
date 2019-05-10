package com.wd.tech.di.presenter;

import com.wd.tech.di.contract.RegisterContract;
import com.wd.tech.di.model.RegisterModel;

import java.lang.ref.SoftReference;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 20:29
 * @fileName:RegisterPresenter
 * @packageName:com.wd.tech.di.presenter
 */
public class RegisterPresenter implements RegisterContract.RegisterPresenter {

    private RegisterContract.RegisterView registerView;
    private SoftReference<RegisterContract.RegisterView> reference;
    private RegisterModel model;

    @Override
    public void attahView(RegisterContract.RegisterView registerView) {
        this.registerView = registerView;

        reference = new SoftReference<>(registerView);

        model = new RegisterModel();
    }

    @Override
    public void deachView(RegisterContract.RegisterView registerView) {
        reference.clear();
    }

    @Override
    public void requestData() {
        model.containData(new RegisterContract.RegisterModel.CallBack() {
            @Override
            public void onCallBack() {
                registerView.showData();
            }
        });
    }
}
