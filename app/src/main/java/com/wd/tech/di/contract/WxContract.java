
package com.wd.tech.di.contract;

import com.wd.tech.data.bean.WxBean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 20:25
 * @fileName:RegisterContract
 * @packageName:com.wd.tech.di.contract
 */
public interface WxContract {

    public interface WxView{
        public void showData(WxBean wxBean);
    }

    public abstract interface WxPresenter<WxView>{

        public  void attahView(WxContract.WxView wxView);

        public  void deachView(WxContract.WxView wxView);

        public  void requestData(String ak,String code);
    }

    public interface WxModel{
        public void containData(String ak,String code,CallBack callBack);

        public interface CallBack{
            public void onCallBack(WxBean wxBean);
        }
    }
}