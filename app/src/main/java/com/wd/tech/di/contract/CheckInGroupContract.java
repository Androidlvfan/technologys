package com.wd.tech.di.contract;

import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.CheckMyFriendBean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/14 20:47
 * @fileName:CheckInGroupContract
 * @packageName:com.wd.tech.di.contract
 */
public interface CheckInGroupContract {
    public interface CheckInGroupView{
        public void showCheckInGroupData(CheckMyFriendBean checkMyFriendBean);

        public void showAddIngGroupData(AddIngFriendBean addIngFriendBean);
    }

    public interface CheckInGroupPresenter<CheckInGroupView>{
        public void attahView(CheckInGroupContract.CheckInGroupView checkInGroupView);

        public void deachView(CheckInGroupContract.CheckInGroupView checkInGroupView);

        public void requestData(int userId, String sessionId, int groupId);
        public void requestAddIngGroupData(int userId, String sessionId, int groupId, String remark);
    }

    public interface CheckInGroupModel{
        public void containData(int userId, String sessionId, int groupId,CallBack callBack);
        public void containAddIngGroupData(int userId, String sessionId, int groupId,String remark,CallBack callBack);
        public interface CallBack{
            public void onCallBack(CheckMyFriendBean checkMyFriendBean);
            public void onAddIngGroupCallBack(AddIngFriendBean addIngFriendBean);
        }
    }
}
