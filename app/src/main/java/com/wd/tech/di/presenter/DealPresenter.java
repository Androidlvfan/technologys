package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.DealBean;
import com.wd.tech.di.contract.DealContract;
import com.wd.tech.di.model.DealModel;

import java.lang.ref.SoftReference;

import okhttp3.MultipartBody;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 20:29
 * @fileName:RegisterPresenter
 * @packageName:com.wd.tech.di.presenter
 */
public class DealPresenter implements DealContract.DealPresenter {

    private DealContract.DealView dealView;
    private SoftReference<DealContract.DealView> reference;
    private DealModel model;


    @Override
    public void attahView(DealContract.DealView dealView) {
        this.dealView = dealView;
        reference = new SoftReference<>(dealView);
        model = new DealModel();
    }

    @Override
    public void deachView(DealContract.DealView dealView) {
        reference.clear();
    }

    @Override
    public void requestData(int userId, String sessionId, String content, MultipartBody multipartBody) {
        model.containData(userId,sessionId,content,multipartBody, new DealContract.DealModel.CallBack() {
            @Override
            public void onCallBack(DealBean dealBean) {
                dealView.showData(dealBean);
            }
        });
    }

}