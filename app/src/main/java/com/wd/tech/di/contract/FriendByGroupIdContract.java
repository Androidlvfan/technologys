package com.wd.tech.di.contract;

import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.FriendByGroupIdBean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/13 19:29
 * @fileName:FriendMessageContract
 * @packageName:com.wd.tech.di.contract
 */
public interface FriendByGroupIdContract {

    public interface FriendByGroupIdView{
        public void showData(FriendByGroupIdBean friendByGroupIdBean);

    }

    public interface FriendByGroupIdPresenter<FriendByGroupIdView>{
        public void attahView(FriendByGroupIdContract.FriendByGroupIdView friendByGroupIdView);

        public void deachView(FriendByGroupIdContract.FriendByGroupIdView friendByGroupIdView);

        public void requestData(int userId, String sessionId, int groupId);

    }

    public interface FriendByGroupIdModel{
        public void containData(int userId, String sessionId, int groupId, CallBack callBack);

        public interface CallBack{
            public void onCallBack(FriendByGroupIdBean friendByGroupIdBean);

        }
    }
}
