package com.wd.tech.di.contract;

import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.FriendChatRecordBean;
import com.wd.tech.data.bean.GroupChatRecordBean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/17 21:17
 * @fileName:FriendChatContract
 * @packageName:com.wd.tech.di.contract
 */
public interface FriendChatContract {
    public interface FriendChatView{
        public void sendFriendMsg(AddIngFriendBean addIngFriendBean);
        public void FriendChatData(FriendChatRecordBean friendChatRecordBean);
    }

    public interface FriendChatPresenter<FriendChatView>{
        public void attahView(FriendChatContract.FriendChatView friendChatView);

        public void deachView(FriendChatContract.FriendChatView friendChatView);

        public void requestSendFriendMsg(int userId, String sessionId, int receiveUid,String content);
        public void requestFriendChatData(int userId, String sessionId, int friendUid,int page,int count);
    }

    public interface FriendChatModel{
        public void containSendFriendMsg(int userId, String sessionId, int receiveUid,String content ,CallBack callBack);
        public void containFriendChatData(int userId, String sessionId, int friendUid,int page,int count, CallBack callBack);
        public interface CallBack{
            public void onSendFriendMsgCallBack(AddIngFriendBean addIngFriendBean);
            public void onFriendChatCallBack(FriendChatRecordBean friendChatRecordBean);
        }
    }
}
