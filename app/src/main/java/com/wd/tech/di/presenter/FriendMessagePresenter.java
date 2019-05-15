package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.CheckMyFriendBean;
import com.wd.tech.data.bean.QueryFriendMessageBean;
import com.wd.tech.di.contract.FriendMessageContract;
import com.wd.tech.di.model.FriendMessageModel;

import java.lang.ref.SoftReference;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/13 19:33
 * @fileName:FriendMessagePresenter
 * @packageName:com.wd.tech.di.presenter
 */
public class FriendMessagePresenter implements FriendMessageContract.FriendMessagePresenter {

    FriendMessageContract.FriendMessageView friendMessageView;
    private SoftReference<FriendMessageContract.FriendMessageView> reference;
    private FriendMessageModel model;

    @Override
    public void attahView(FriendMessageContract.FriendMessageView friendMessageView) {
        this.friendMessageView = friendMessageView;

        reference = new SoftReference<>(friendMessageView);

        model = new FriendMessageModel();
    }

    @Override
    public void deachView(FriendMessageContract.FriendMessageView friendMessageView) {
        reference.clear();
    }

    @Override
    public void requestData(int userId, String sessionId, int friend) {
        model.containData(userId, sessionId, friend, new FriendMessageContract.FriendMessageModel.CallBack() {
            @Override
            public void onCallBack(QueryFriendMessageBean queryFriendMessageBean) {
                friendMessageView.showData(queryFriendMessageBean);
            }

            @Override
            public void onCheckFriendCallBack(CheckMyFriendBean checkMyFriendBean) {

            }
        });
    }

    @Override
    public void requestCheckFriendData(int userId, String sessionId, int friendUid) {
        model.containCheckFriendData(userId, sessionId, friendUid, new FriendMessageContract.FriendMessageModel.CallBack() {
            @Override
            public void onCallBack(QueryFriendMessageBean queryFriendMessageBean) {

            }

            @Override
            public void onCheckFriendCallBack(CheckMyFriendBean checkMyFriendBean) {
                friendMessageView.showCheckFriendData(checkMyFriendBean);
            }
        });
    }
}
