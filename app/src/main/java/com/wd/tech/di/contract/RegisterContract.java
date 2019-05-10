package com.wd.tech.di.contract;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 20:25
 * @fileName:RegisterContract
 * @packageName:com.wd.tech.di.contract
 */
public interface RegisterContract {

    public interface RegisterView{
        public void showData();
    }

    public interface RegisterPresenter<RegisterView>{
        public void attahView(RegisterContract.RegisterView registerView);

        public void deachView(RegisterContract.RegisterView registerView);

        public void requestData();
    }

    public interface RegisterModel{
        public void containData(CallBack callBack);

        public interface CallBack{
            public void onCallBack();
        }
    }
}
