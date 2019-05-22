package com.wd.tech.di.contract;

import com.wd.tech.data.bean.MenuBean;

public interface MenuContract {

    public interface MenuView{
        public void ShowData(MenuBean menuBean);
    }

    public interface MenuPresenter<Menuview>{
        public void attrView(MenuContract.MenuView menuView);
        public void deachView(MenuContract.MenuView menuView);
        public void requestData();
    }

    public interface MenuModel{
        public void cotainData(CallBack callBack);
        public interface CallBack{
            public void onCallBack(MenuBean menuBean);
        }
    }
}
