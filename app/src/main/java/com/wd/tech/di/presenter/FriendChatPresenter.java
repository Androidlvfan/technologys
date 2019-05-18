package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.FriendChatRecordBean;
import com.wd.tech.di.contract.FriendChatContract;
import com.wd.tech.di.model.FriendChatModel;

import java.lang.ref.SoftReference;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/17 21:24
 * @fileName:FriendChatPresenter
 * @packageName:com.wd.tech.di.presenter
 */
public class FriendChatPresenter implements FriendChatContract.FriendChatPresenter {


    FriendChatContract.FriendChatView friendChatView;
    private SoftReference<FriendChatContract.FriendChatView> reference;
    private FriendChatModel model;

    @Override
    public void attahView(FriendChatContract.FriendChatView friendChatView) {
        this.friendChatView = friendChatView;

        reference = new SoftReference<>(friendChatView);

        model = new FriendChatModel();
    }


    @Override
    public void deachView(FriendChatContract.FriendChatView friendChatView) {
        reference.clear();
    }

    @Override
    public void requestSendFriendMsg(int userId, String sessionId, int receiveUid, String content) {
        model.containSendFriendMsg(userId, sessionId, receiveUid, content, new FriendChatContract.FriendChatModel.CallBack() {
            @Override
            public void onSendFriendMsgCallBack(AddIngFriendBean addIngFriendBean) {
                friendChatView.sendFriendMsg(addIngFriendBean);
            }

            @Override
            public void onFriendChatCallBack(FriendChatRecordBean friendChatRecordBean) {

            }
        });
    }

    @Override
    public void requestFriendChatData(int userId, String sessionId, int friendUid, int page, int count) {
        model.containFriendChatData(userId, sessionId, friendUid, page, count, new FriendChatContract.FriendChatModel.CallBack() {
            @Override
            public void onSendFriendMsgCallBack(AddIngFriendBean addIngFriendBean) {

            }

            @Override
            public void onFriendChatCallBack(FriendChatRecordBean friendChatRecordBean) {
                friendChatView.FriendChatData(friendChatRecordBean);
            }
        });
    }
}
