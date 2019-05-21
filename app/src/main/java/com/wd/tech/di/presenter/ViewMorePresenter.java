package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.ViewMoreBean;
import com.wd.tech.di.contract.ViewMoreContract;
import com.wd.tech.di.model.ViewMoreModel;

import java.lang.ref.SoftReference;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 20:29
 * @fileName:RegisterPresenter
 * @packageName:com.wd.tech.di.presenter
 */
public class ViewMorePresenter implements ViewMoreContract.ViewMorePresenter {

    private ViewMoreContract.ViewMoreView viewMoreView;
    private SoftReference<ViewMoreContract.ViewMoreView> reference;
    private ViewMoreModel model;

    @Override
    public void attahView(ViewMoreContract.ViewMoreView viewMoreView) {
        this.viewMoreView = viewMoreView;
        reference = new SoftReference<>(viewMoreView);
        model = new ViewMoreModel();
    }

    @Override
    public void deachView(ViewMoreContract.ViewMoreView viewMoreView) {
        reference.clear();
    }

    @Override
    public void requestData(int communityId, int page, int count) {
        model.containData(communityId, page, count, new ViewMoreContract.ViewMoreModel.CallBack() {
            @Override
            public void onCallBack(ViewMoreBean viewMoreBean) {
                viewMoreView.showData(viewMoreBean);
            }
        });
    }



}