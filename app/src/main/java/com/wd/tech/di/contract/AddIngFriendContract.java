package com.wd.tech.di.contract;

import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.CheckMyFriendBean;
import com.wd.tech.data.bean.QueryFriendMessageBean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/13 19:29
 * @fileName:FriendMessageContract
 * @packageName:com.wd.tech.di.contract
 */
public interface AddIngFriendContract {

    public interface AddIngFriendView{
        public void showData(AddIngFriendBean addIngFriendBean);

    }

    public interface AddIngFriendPresenter<AddIngFriendView>{
        public void attahView(AddIngFriendContract.AddIngFriendView addIngFriendView);

        public void deachView(AddIngFriendContract.AddIngFriendView addIngFriendView);

        public void requestData(int userId, String sessionId, int friendUid,String remark);

    }

    public interface AddIngFriendModel{
        public void containData(int userId, String sessionId, int friendUid,String remark, CallBack callBack);

        public interface CallBack{
            public void onCallBack(AddIngFriendBean addIngFriendBean);

        }
    }
}
