package com.wd.tech.di.contract;

import com.wd.tech.data.bean.ShowBean;

import java.util.HashMap;

public interface ShowContract {
    public interface ShowView{
        public void ShowData(ShowBean showBean);
    }

    public interface ShowPresenter<ShowView>{

        public void attrView(ShowContract.ShowView showView);

        public void deachView(ShowContract.ShowView showView);

        public void requestData(HashMap<String,String> params,String userId,String sessionId);
    }

    public interface ShowModel{

        public void cotainData(HashMap<String,String> params,String userId,String sessionId,CallBack callBack);
        public interface CallBack{
            public void onCallBack(ShowBean showBean);
        }
    }
}
