package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.WxBean;
import com.wd.tech.di.contract.WxContract;
import com.wd.tech.di.model.WxModel;

import java.lang.ref.SoftReference;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 20:29
 * @fileName:RegisterPresenter
 * @packageName:com.wd.tech.di.presenter
 */
public class WxPresenter implements WxContract.WxPresenter {

    private WxContract.WxView wxView;
    private SoftReference<WxContract.WxView> reference;
    private WxModel model;


    @Override
    public void attahView(WxContract.WxView wxView) {
        this.wxView = wxView;
        reference = new SoftReference<>(wxView);
        model = new WxModel();
    }

    @Override
    public void deachView(WxContract.WxView wxView) {
        reference.clear();
    }

    @Override
    public void requestData(String ak,String code) {
        model.containData(ak,code, new WxContract.WxModel.CallBack() {
            @Override
            public void onCallBack(WxBean wxBean) {
                wxView.showData(wxBean);
            }
        });
    }

}