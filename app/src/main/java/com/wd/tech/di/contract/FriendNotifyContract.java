package com.wd.tech.di.contract;

import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.FriendNotifyBean;
import com.wd.tech.data.bean.GroupNotifyBean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/15 17:13
 * @fileName:FriendNotifyContract
 * @packageName:com.wd.tech.di.contract
 */
public interface FriendNotifyContract {


    public interface FriendNotifyView{
        public void showFriendNotifyData(FriendNotifyBean friendNotifyBean);

        public void showReviewFriendData(AddIngFriendBean addIngFriendBean);
    }

    public interface FriendNotifyPresenter<FriendNotifyView>{
        public void attahView(FriendNotifyContract.FriendNotifyView friendNotifyView);

        public void deachView(FriendNotifyContract.FriendNotifyView friendNotifyView);

        public void requestFriendNotifyData(int userId, String sessionId, int page, int count);
        public void requestReviewFriendData(int userId,String sessionId,int noticeId,int flag);
    }

    public interface FriendNotifyModel{
        public void containFriendNotifyData(int userId, String sessionId, int page, int count,CallBack callBack);
        public void containReviewFriendData(int userId,String sessionId,int noticeId,int flag,CallBack callBack);
        public interface CallBack{
            public void onFriendNotifyCallBack(FriendNotifyBean friendNotifyBean);
            public void onReviewFriendCallBack(AddIngFriendBean addIngFriendBean);
        }
    }
}
