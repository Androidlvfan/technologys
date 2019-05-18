package com.wd.tech.di.contract;

import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.GroupChatRecordBean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/17 19:31
 * @fileName:GroupChatContract
 * @packageName:com.wd.tech.di.contract
 */
public interface GroupChatContract {
    public interface GroupChatView{
        public void sendGroupMsg(AddIngFriendBean addIngFriendBean);
        public void groupChatData(GroupChatRecordBean groupChatRecordBean);
    }

    public interface GroupChatPresenter<GroupChatView>{
        public void attahView(GroupChatContract.GroupChatView groupChatView);

        public void deachView(GroupChatContract.GroupChatView groupChatView);

        public void requestSendGroupMsg(int userId, String sessionId, int groupId,String content);
        public void requestGroupChatData(int userId, String sessionId, int groupId,int page,int count);
    }

    public interface GroupChatModel{
        public void containSendGroupMsg(int userId, String sessionId, int groupId,String content ,CallBack callBack);
        public void containGroupChatData(int userId, String sessionId, int groupId,int page,int count, CallBack callBack);
        public interface CallBack{
            public void onSendGroupMsgCallBack(AddIngFriendBean addIngFriendBean);
            public void onGroupChatCallBack(GroupChatRecordBean groupChatRecordBean);
        }
    }
}
