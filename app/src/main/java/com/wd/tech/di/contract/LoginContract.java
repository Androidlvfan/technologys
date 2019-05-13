
package com.wd.tech.di.contract;

import com.wd.tech.data.bean.LoginBean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 20:25
 * @fileName:RegisterContract
 * @packageName:com.wd.tech.di.contract
 */
public interface LoginContract {

    public interface LoginView{
        public void showData(LoginBean loginBean);
    }

    public interface LoginPresenter<LoginView>{
        public void attahView(LoginContract.LoginView loginView);

        public void deachView(LoginContract.LoginView loginView);

        public void requestData(String phone, String pwd);
    }

    public interface LoginModel{
        public void containData(String phone,String pwd, CallBack callBack);

        public interface CallBack{
            public void onCallBack(LoginBean loginBean);
        }
    }
}