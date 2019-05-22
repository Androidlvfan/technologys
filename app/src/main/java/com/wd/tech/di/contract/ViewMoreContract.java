
package com.wd.tech.di.contract;

import com.wd.tech.data.bean.ViewMoreBean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 20:25
 * @fileName:RegisterContract
 * @packageName:com.wd.tech.di.contract
 */
public interface ViewMoreContract {

    public interface ViewMoreView{
        public void showData(ViewMoreBean viewMoreBean);
    }

    public abstract interface ViewMorePresenter<ViewMoreView>{

        public  void attahView(ViewMoreContract.ViewMoreView viewMoreView);

        public  void deachView(ViewMoreContract.ViewMoreView viewMoreView);

        public  void requestData(int communityId, int page, int count);

    }

    public interface ViewMoreModel{
        public void containData(int communityId, int page, int count, CallBack callBack);

        public interface CallBack{
            public void onCallBack(ViewMoreBean viewMoreBean);
        }
    }
}