package com.wd.tech.di.contract;

import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.FriendGroupBean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/15 20:31
 * @fileName:FriendGroupContract
 * @packageName:com.wd.tech.di.contract
 */
public interface FriendGroupContract {
    public interface FriendGroupView{
        public void showData(FriendGroupBean friendGroupBean);

        public void showLaHeiFriendData(AddIngFriendBean addIngFriendBean);
    }

    public interface FriendGroupPresenter<FriendGroupView>{
        public void attahView(FriendGroupContract.FriendGroupView friendGroupView);

        public void deachView(FriendGroupContract.FriendGroupView friendGroupView);

        public void requestData(int userId, String sessionId);
        public void requestLaHeiFriendData(int userId, String sessionId,int newGroupId,int friendUid);
    }

    public interface FriendGroupModel{
        public void containData(int userId, String sessionId, CallBack callBack);
        public void containLaHeiFriendData(int userId, String sessionId,int newGroupId,int friendUid, CallBack callBack);
        public interface CallBack{
            public void onCallBack(FriendGroupBean friendGroupBean);
            public void onLaHeiFriendCallBack(AddIngFriendBean addIngFriendBean);
        }
    }
}
