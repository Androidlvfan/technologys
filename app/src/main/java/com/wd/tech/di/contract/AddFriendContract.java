package com.wd.tech.di.contract;

import com.wd.tech.data.bean.AddFriendBean;
import com.wd.tech.data.bean.AddGroupBean;
import com.wd.tech.data.bean.RegisterBean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 20:25
 * @fileName:RegisterContract
 * @packageName:com.wd.tech.di.contract
 */
public interface AddFriendContract {

    public interface AddFriendView{
        public void showAddFriendData(AddFriendBean addFriendBean);

        public void showAddGroupData(AddGroupBean addGroupBean);
    }

    public interface AddFriendPresenter<AddFriendView>{
        public void attahView(AddFriendContract.AddFriendView addFriendView);

        public void deachView(AddFriendContract.AddFriendView addFriendView);

        public void requestData(int userId,String sessionId,String phone);
        public void requestAddGroupData(int userId,String sessionId,int groupId);
    }

    public interface AddFriendModel{
        public void containData(int userId,String sessionId,String phone,CallBack callBack);
        public void containAddGroupData(int userId,String sessionId,int groupId,CallBack callBack);
        public interface CallBack{
            public void onCallBack(AddFriendBean addFriendBean);
            public void onAddGroupCallBack(AddGroupBean addGroupBean);
        }
    }
}
