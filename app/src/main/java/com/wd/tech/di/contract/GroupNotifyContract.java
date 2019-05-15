package com.wd.tech.di.contract;

import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.GroupNotifyBean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/15 15:35
 * @fileName:GroupNotifyContract
 * @packageName:com.wd.tech.di.contract
 */
public interface GroupNotifyContract {

    public interface GroupNotifyView{
        public void showData(GroupNotifyBean groupNotifyBean);

        public void showReviewGrouData(AddIngFriendBean addIngFriendBean);
    }

    public interface GroupNotifyPresenter<GroupNotifyView>{
        public void attahView(GroupNotifyContract.GroupNotifyView groupNotifyView);

        public void deachView(GroupNotifyContract.GroupNotifyView groupNotifyView);

        public void requestData(int userId, String sessionId, int page, int count);
        public void requestReviewGroupData(int userId,String sessionId,int noticeId,int flag);
    }

    public interface GroupNotifyModel{
        public void containData(int userId, String sessionId, int page, int count, CallBack callBack);
        public void containReviewGroupData(int userId,String sessionId,int noticeId,int flag,CallBack callBack);
        public interface CallBack{
            public void onCallBack(GroupNotifyBean groupNotifyBean);
            public void onReviewGroupCallBack(AddIngFriendBean addIngFriendBean);
        }
    }
}
