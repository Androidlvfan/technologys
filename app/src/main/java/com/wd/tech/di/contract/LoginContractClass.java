
package com.wd.tech.di.contract;

import com.wd.tech.data.bean.LoginBean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 20:25
 * @fileName:RegisterContract
 * @packageName:com.wd.tech.di.contract
 */
public interface LoginContractClass {

    public interface LoginView{
        public void showData(LoginBean loginBean);
    }

    public interface LoginPresenter<LoginView>{
        public void attahView(LoginContractClass.LoginView loginView);

        public void deachView(LoginContractClass.LoginView loginView);

        public void requestData(String phone, String pwd);
    }

    public interface LoginModel{
        public void containData(String phone,String pwd, CallBack callBack);

        public interface CallBack{
            public void onCallBack(LoginBean loginBean);
        }
    }
}