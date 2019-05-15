package com.wd.tech.di.contract;

import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.CreateGroupBean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/14 21:55
 * @fileName:CreateGroupContract
 * @packageName:com.wd.tech.di.contract
 */
public interface CreateGroupContract  {
    public interface CreateGroupView{
        public void showData(CreateGroupBean createGroupBean);

    }

    public interface CreateGroupPresenter<CreateGroupView>{
        public void attahView(CreateGroupContract.CreateGroupView createGroupView);

        public void deachView(CreateGroupContract.CreateGroupView createGroupView);

        public void requestData(int userId, String sessionId, String name,String description);

    }

    public interface CreateGroupModel{
        public void containData(int userId, String sessionId, String name,String description,CallBack callBack);

        public interface CallBack{
            public void onCallBack(CreateGroupBean createGroupBean);

        }
    }

}
