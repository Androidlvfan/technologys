package com.wd.tech.di.contract;

import com.wd.tech.data.bean.DetailOkBean;

public interface DetailContract {

    public interface DetailView{
        public void ShowData(DetailOkBean detailOkBean);
    }

    public interface DetailPresenter<DetailView>{
        public void attrView(DetailContract.DetailView detailView);
        public void deachView(DetailContract.DetailView detailView);

        public void requestData(int userId,String sessionId,int id);
    }

    public interface DetailModel{
        public void cotainData(int userId,String sessionId,int id,Callback callBack);
        public interface Callback{
            public void Callback(DetailOkBean detailOkBean);
        }
    }
}
