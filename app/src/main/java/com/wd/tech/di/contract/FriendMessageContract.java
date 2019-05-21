package com.wd.tech.di.contract;

import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.CheckMyFriendBean;
import com.wd.tech.data.bean.QueryFriendMessageBean;
import com.wd.tech.data.bean.RegisterBean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/13 19:29
 * @fileName:FriendMessageContract
 * @packageName:com.wd.tech.di.contract
 */
public interface FriendMessageContract {

    public interface FriendMessageView{
        public void showData(QueryFriendMessageBean queryFriendMessageBean);

        public void showCheckFriendData(CheckMyFriendBean checkMyFriendBean);

        public void showUpdBeiZhuData(AddIngFriendBean addIngFriendBean);
    }

    public interface FriendMessagePresenter<FriendMessageView>{
        public void attahView(FriendMessageContract.FriendMessageView friendMessageView);

        public void deachView(FriendMessageContract.FriendMessageView friendMessageView);

        public void requestData(int userId,String sessionId,int friend);
        public void requestCheckFriendData(int userId,String sessionId,int friendUid);
        public void requestUpdBeiZhuData(int userId,String sessionId,int friendUid,String remarkName);
    }

    public interface FriendMessageModel{
        public void containData(int userId,String sessionId,int friend,CallBack callBack);
        public void containCheckFriendData(int userId,String sessionId,int friendUid,CallBack callBack);
        public void containUpdBeiZhuData(int userId,String sessionId,int friendUid,String remarkName,CallBack callBack);
        public interface CallBack{
            public void onCallBack(QueryFriendMessageBean queryFriendMessageBean);
            public void onCheckFriendCallBack(CheckMyFriendBean checkMyFriendBean);
            public void onUpdBeiZhuCallBack(AddIngFriendBean addIngFriendBean);
        }
    }
}
