package com.wd.tech.di.contract;

import com.wd.tech.data.bean.AddCommunityBean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 20:25
 * @fileName:RegisterContract
 * @packageName:com.wd.tech.di.contract
 */
public interface AddCommunityContract {

    public interface AddCommunityView{
        public void showAddCommunityData(AddCommunityBean communityBean);
    }

    public interface AddCommunityPresenter<AddCommunityView>{
        public void attahView(AddCommunityContract.AddCommunityView addCommunityView);

        public void deachView(AddCommunityContract.AddCommunityView addCommunityView);

        public void requestData(int userId, String sessionId, int communityId,String content);

    }

    public interface  AddCommunityModel{

        public void containData(int userId, String sessionId, int communityId,String content, CallBack callBack);

        public interface CallBack{
            public void onCallBack(AddCommunityBean addCommunityBean);

        }
    }
}
