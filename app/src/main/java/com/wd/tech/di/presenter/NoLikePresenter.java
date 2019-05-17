package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.NoLikeBean;
import com.wd.tech.di.contract.NoLikeContract;
import com.wd.tech.di.model.NoLikeModel;

import java.lang.ref.SoftReference;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 20:29
 * @fileName:RegisterPresenter
 * @packageName:com.wd.tech.di.presenter
 */
public class NoLikePresenter implements NoLikeContract.NoLikePresenter {

    private NoLikeContract.NoLikeView noLikeView;
    private SoftReference<NoLikeContract.NoLikeView> reference;
    private NoLikeModel model;


    @Override
    public void attahView(NoLikeContract.NoLikeView noLikeView) {
        this.noLikeView = noLikeView;
        reference = new SoftReference<>(noLikeView);
        model = new NoLikeModel();
    }

    @Override
    public void deachView(NoLikeContract.NoLikeView noLikeView) {
        reference.clear();
    }

    @Override
    public void requestData(int userId, String sessionId, int communityId) {
        model.containData(userId, sessionId, communityId, new NoLikeContract.NoLikeModel.CallBack() {
            @Override
            public void onCallBack(NoLikeBean noLikeBean) {
                noLikeView.showData(noLikeBean);
            }
        });
    }

}