package com.wd.tech.di.contant;

import com.wd.tech.data.bean.LoginBean;

public interface LoginContracClass {

    public interface LoginView{
        public void showData(LoginBean loginBean);
    }

    public interface Loginresenter<LoginView>{

        public void attahView(LoginContracClass.LoginView loginView);

        public void deachView(LoginContracClass.LoginView loginView);

        public void requestData(String phone,String pwd);
    }

    public interface LoginModel{
        public void containData(String phone,String pwd,CallBack callBack);

        public interface CallBack{
            public void onCallBack(LoginBean loginBean);
        }
    }




}
