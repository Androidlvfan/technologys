package com.wd.tech.di.contract;

import com.wd.tech.data.bean.SerachByTitleBean;

import java.util.HashMap;

public interface SearchContract {
    public interface SearchView{
        public void SearchData(SerachByTitleBean serachByTitleBean);
    }

    public interface SearchPresenter<SearchView>{

        public void attrView(SearchContract.SearchView searchView);

        public void deachView(SearchContract.SearchView searchView);

        public void requestData(String title,HashMap<String,String> params);
    }

    public interface SearchModel{

        public void cotainData(String title, HashMap<String,String> params,CallBack callBack);
        public interface CallBack{
            public void onCallBack(SerachByTitleBean serachByTitleBean);
        }
    }
}
