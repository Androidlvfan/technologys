
package com.wd.tech.di.contract;

import com.wd.tech.data.bean.CommunityBean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 20:25
 * @fileName:RegisterContract
 * @packageName:com.wd.tech.di.contract
 */
public interface CommunityContract {

    public interface CommunityView{
        public void showData(CommunityBean communityBean);
    }

    public interface CommunityPresenter<CommunityView>{
        public void attahView(CommunityContract.CommunityView communityView);

        public void deachView(CommunityContract.CommunityView communityView);

        public void requestData(int page, int count);
    }

    public interface CommunityModel{
        public void containData(int page, int count, CallBack callBack);

        public interface CallBack{
            public void onCallBack(CommunityBean communityBean);
        }
    }
}