package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.FriendByGroupIdBean;
import com.wd.tech.di.contract.FriendByGroupIdContract;
import com.wd.tech.di.model.FriendByGroupIdModel;

import java.lang.ref.SoftReference;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/16 15:03
 * @fileName:FriendByGroupIdPresenter
 * @packageName:com.wd.tech.di.presenter
 */
public class FriendByGroupIdPresenter implements FriendByGroupIdContract.FriendByGroupIdPresenter {

    FriendByGroupIdContract.FriendByGroupIdView friendByGroupIdView;
    private SoftReference<FriendByGroupIdContract.FriendByGroupIdView> friend;
    private FriendByGroupIdModel model;

    @Override
    public void attahView(FriendByGroupIdContract.FriendByGroupIdView friendByGroupIdView) {
        this.friendByGroupIdView = friendByGroupIdView;

        friend = new SoftReference<>(friendByGroupIdView);

        model = new FriendByGroupIdModel();
    }

    @Override
    public void deachView(FriendByGroupIdContract.FriendByGroupIdView friendByGroupIdView) {
        friend.clear();
    }

    @Override
    public void requestData(int userId, String sessionId, int groupId) {
        model.containData(userId, sessionId, groupId, new FriendByGroupIdContract.FriendByGroupIdModel.CallBack() {
            @Override
            public void onCallBack(FriendByGroupIdBean friendByGroupIdBean) {
                friendByGroupIdView.showData(friendByGroupIdBean);
            }
        });
    }
}
