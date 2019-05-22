
package com.wd.tech.di.contract;

import com.wd.tech.data.bean.NoLikeBean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 20:25
 * @fileName:RegisterContract
 * @packageName:com.wd.tech.di.contract
 */
public interface NoLikeContract {

    public interface NoLikeView{
        public void showData(NoLikeBean noLikeBean);
    }

    public abstract interface NoLikePresenter<NoLikeView>{

        public  void attahView(NoLikeContract.NoLikeView noLikeBean);

        public  void deachView(NoLikeContract.NoLikeView noLikeBean);

        public  void requestData(int userId, String sessionId, int communityId);
    }

    public interface NoLikeModel{
        public void containData(int userId, String sessionId, int communityId, CallBack callBack);

        public interface CallBack{
            public void onCallBack(NoLikeBean noLikeBean);
        }
    }
}