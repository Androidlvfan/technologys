package com.wd.tech.di.contract;

import com.wd.tech.data.bean.RegisterBean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 20:25
 * @fileName:RegisterContract
 * @packageName:com.wd.tech.di.contract
 */
public interface RegisterContract {

    public interface RegisterView{
        public void showData(RegisterBean registerBean);
    }

    public interface RegisterPresenter<RegisterView>{
        public void attahView(RegisterContract.RegisterView registerView);

        public void deachView(RegisterContract.RegisterView registerView);

        public void requestData(String phone,String nickName,String pwd);
    }

    public interface RegisterModel{
        public void containData(String phone,String nickName,String pwd,CallBack callBack);

        public interface CallBack{
            public void onCallBack(RegisterBean registerBean);
        }
    }
}
