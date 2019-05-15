package com.wd.tech.di.contract;

import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.GroupBean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/15 10:09
 * @fileName:GroupContract
 * @packageName:com.wd.tech.di.contract
 */
public interface GroupContract {
    public interface GroupView{
        public void showGroupsByUserData(GroupBean groupBean);
        public void showJoinedGroupData(GroupBean groupBean);
    }

    public interface GroupPresenter<GroupView>{
        public void attahView(GroupContract.GroupView groupView);

        public void deachView(GroupContract.GroupView groupView);

        public void requestGroupsByUserData(int userId, String sessionId);
        public void requestJoinedGroupData(int userId, String sessionId);
    }

    public interface GroupModel{
        public void containGroupsByUserData(int userId, String sessionId,CallBack callBack);
        public void containJoinedGroupData(int userId, String sessionId,CallBack callBack);
        public interface CallBack{
            public void onGroupsByUserCallBack(GroupBean groupBean);
            public void onJoinedGroupCallBack(GroupBean groupBean);
        }
    }
}
