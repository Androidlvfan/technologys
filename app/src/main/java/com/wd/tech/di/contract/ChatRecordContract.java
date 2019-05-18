package com.wd.tech.di.contract;

import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.ChatRecordBean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/13 19:29
 * @fileName:FriendMessageContract
 * @packageName:com.wd.tech.di.contract
 */
public interface ChatRecordContract {

    public interface ChatRecordView{
        public void showData(ChatRecordBean chatRecordBean);

    }

    public interface ChatRecordPresenter<ChatRecordView>{
        public void attahView(ChatRecordContract.ChatRecordView chatRecordView);

        public void deachView(ChatRecordContract.ChatRecordView chatRecordView);

        public void requestData(int userId, String sessionId, int friendUid, int page,int count);

    }

    public interface ChatRecordModel{
        public void containData(int userId, String sessionId, int friendUid, int page,int count, CallBack callBack);

        public interface CallBack{
            public void onCallBack(ChatRecordBean chatRecordBean);

        }
    }
}
