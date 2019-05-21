
package com.wd.tech.di.contract;

import com.wd.tech.data.bean.DealBean;

import okhttp3.MultipartBody;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 20:25
 * @fileName:RegisterContract
 * @packageName:com.wd.tech.di.contract
 */
public interface DealContract {

    public interface DealView{
        public void showData(DealBean dealBean);
    }

    public abstract interface DealPresenter<DealView>{

        public  void attahView(DealContract.DealView dealView);

        public  void deachView(DealContract.DealView dealView);

        public  void requestData(int userId, String sessionId, String content, MultipartBody multipartBody);
    }

    public interface DealModel{
        public void containData(int userId, String sessionId, String content, MultipartBody multipartBody, CallBack callBack);

        public interface CallBack{
            public void onCallBack(DealBean dealBean);
        }
    }
}