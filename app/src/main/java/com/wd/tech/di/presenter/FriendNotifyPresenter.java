package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.FriendNotifyBean;
import com.wd.tech.di.contract.FriendNotifyContract;
import com.wd.tech.di.model.FriendNotifyModel;

import java.lang.ref.SoftReference;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/15 19:38
 * @fileName:FriendNotifyPresenter
 * @packageName:com.wd.tech.di.presenter
 */
public class FriendNotifyPresenter implements FriendNotifyContract.FriendNotifyPresenter {

    FriendNotifyContract.FriendNotifyView friendNotifyView;
    private SoftReference<FriendNotifyContract.FriendNotifyView> reference;
    private FriendNotifyModel model;

    @Override
    public void attahView(FriendNotifyContract.FriendNotifyView friendNotifyView) {
        this.friendNotifyView= friendNotifyView;

        reference = new SoftReference<>(friendNotifyView);

        model = new FriendNotifyModel();
    }

    @Override
    public void deachView(FriendNotifyContract.FriendNotifyView friendNotifyView) {
        reference.clear();
    }

    @Override
    public void requestFriendNotifyData(int userId, String sessionId, int page, int count) {
        model.containFriendNotifyData(userId, sessionId, page, count, new FriendNotifyContract.FriendNotifyModel.CallBack() {
            @Override
            public void onFriendNotifyCallBack(FriendNotifyBean friendNotifyBean) {
                friendNotifyView.showFriendNotifyData(friendNotifyBean);
            }

            @Override
            public void onReviewFriendCallBack(AddIngFriendBean addIngFriendBean) {

            }
        });
    }

    @Override
    public void requestReviewFriendData(int userId, String sessionId, int noticeId, final int flag) {
        model.containReviewFriendData(userId, sessionId, noticeId, flag, new FriendNotifyContract.FriendNotifyModel.CallBack() {
            @Override
            public void onFriendNotifyCallBack(FriendNotifyBean friendNotifyBean) {

            }

            @Override
            public void onReviewFriendCallBack(AddIngFriendBean addIngFriendBean) {
                friendNotifyView.showReviewFriendData(addIngFriendBean);
            }
        });
    }
}
