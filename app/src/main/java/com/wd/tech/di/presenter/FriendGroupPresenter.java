package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.FriendGroupBean;
import com.wd.tech.di.contract.FriendGroupContract;
import com.wd.tech.di.model.FriendGroupModel;

import java.lang.ref.SoftReference;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/15 20:35
 * @fileName:FriendGroupPresenter
 * @packageName:com.wd.tech.di.presenter
 */
public class FriendGroupPresenter implements FriendGroupContract.FriendGroupPresenter {

    FriendGroupContract.FriendGroupView friendGroupView;
    private SoftReference<FriendGroupContract.FriendGroupView> reference;
    private FriendGroupModel groupModel;

    @Override
    public void attahView(FriendGroupContract.FriendGroupView friendGroupView) {
        this.friendGroupView = friendGroupView;

        reference = new SoftReference<>(friendGroupView);


        groupModel = new FriendGroupModel();
    }

    @Override
    public void deachView(FriendGroupContract.FriendGroupView friendGroupView) {
        reference.clear();
    }

    @Override
    public void requestData(int userId, String sessionId) {
        groupModel.containData(userId, sessionId, new FriendGroupContract.FriendGroupModel.CallBack() {
            @Override
            public void onCallBack(FriendGroupBean friendGroupBean) {
                friendGroupView.showData(friendGroupBean);
            }

            @Override
            public void onLaHeiFriendCallBack(AddIngFriendBean addIngFriendBean) {

            }
        });
    }

    @Override
    public void requestLaHeiFriendData(int userId, String sessionId, int newGroupId, int friendUid) {
        groupModel.containLaHeiFriendData(userId, sessionId, newGroupId, friendUid, new FriendGroupContract.FriendGroupModel.CallBack() {
            @Override
            public void onCallBack(FriendGroupBean friendGroupBean) {

            }

            @Override
            public void onLaHeiFriendCallBack(AddIngFriendBean addIngFriendBean) {
                friendGroupView.showLaHeiFriendData(addIngFriendBean);
            }
        });
    }
}
