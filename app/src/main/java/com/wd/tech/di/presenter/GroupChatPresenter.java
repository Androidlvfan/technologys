package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.GroupChatRecordBean;
import com.wd.tech.di.contract.GroupChatContract;
import com.wd.tech.di.model.GroupChatModel;

import java.lang.ref.SoftReference;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/17 19:40
 * @fileName:GroupChatPresenter
 * @packageName:com.wd.tech.di.presenter
 */
public class GroupChatPresenter implements GroupChatContract.GroupChatPresenter {

    GroupChatContract.GroupChatView groupChatView;
    private SoftReference<GroupChatContract.GroupChatView> reference;
    private GroupChatModel model;

    @Override
    public void attahView(GroupChatContract.GroupChatView groupChatView) {
        this.groupChatView = groupChatView;

        reference = new SoftReference<>(groupChatView);

        model = new GroupChatModel();

    }

    @Override
    public void deachView(GroupChatContract.GroupChatView groupChatView) {
        reference.clear();
    }

    @Override
    public void requestSendGroupMsg(int userId, String sessionId, int groupId, String content) {
        model.containSendGroupMsg(userId, sessionId, groupId, content, new GroupChatContract.GroupChatModel.CallBack() {
            @Override
            public void onSendGroupMsgCallBack(AddIngFriendBean addIngFriendBean) {
                groupChatView.sendGroupMsg(addIngFriendBean);
            }

            @Override
            public void onGroupChatCallBack(GroupChatRecordBean groupChatRecordBean) {

            }
        });
    }

    @Override
    public void requestGroupChatData(int userId, String sessionId, int groupId, int page, int count) {
        model.containGroupChatData(userId, sessionId, groupId, page, count, new GroupChatContract.GroupChatModel.CallBack() {
            @Override
            public void onSendGroupMsgCallBack(AddIngFriendBean addIngFriendBean) {

            }

            @Override
            public void onGroupChatCallBack(GroupChatRecordBean groupChatRecordBean) {
                groupChatView.groupChatData(groupChatRecordBean);
            }
        });
    }
}
