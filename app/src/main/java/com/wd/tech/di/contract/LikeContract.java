
package com.wd.tech.di.contract;

import com.wd.tech.data.bean.LikeBean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 20:25
 * @fileName:RegisterContract
 * @packageName:com.wd.tech.di.contract
 */
public interface LikeContract {

    public interface LikeView{
        public void showData(LikeBean likeBean);
    }

    public abstract interface LikePresenter<LikeView>{

        public  void attahView(LikeContract.LikeView likeView);

        public  void deachView(LikeContract.LikeView likeView);

        public  void requestData(int userId, String sessionId, int communityId);
    }

    public interface LikeModel{
        public void containData(int userId, String sessionId, int communityId, CallBack callBack);

        public interface CallBack{
            public void onCallBack(LikeBean likeBean);
        }
    }
}