package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.LikeBean;
import com.wd.tech.di.contract.LikeContract;
import com.wd.tech.di.model.LikeModel;

import java.lang.ref.SoftReference;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 20:29
 * @fileName:RegisterPresenter
 * @packageName:com.wd.tech.di.presenter
 */
public class LikePresenter implements LikeContract.LikePresenter {

    private LikeContract.LikeView likeView;
    private SoftReference<LikeContract.LikeView> reference;
    private LikeModel model;


    @Override
    public void attahView(LikeContract.LikeView likeView) {
        this.likeView = likeView;
        reference = new SoftReference<>(likeView);
        model = new LikeModel();
    }

    @Override
    public void deachView(LikeContract.LikeView likeView) {
        reference.clear();
    }

    @Override
    public void requestData(int userId, String sessionId, int communityId) {
        model.containData(userId, sessionId, communityId, new LikeContract.LikeModel.CallBack() {
            @Override
            public void onCallBack(LikeBean likeBean) {
                likeView.showData(likeBean);
            }
        });
    }

}