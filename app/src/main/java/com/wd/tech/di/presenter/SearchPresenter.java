package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.SerachByTitleBean;
import com.wd.tech.data.bean.ShowBean;
import com.wd.tech.di.contract.SearchContract;
import com.wd.tech.di.contract.ShowContract;
import com.wd.tech.di.model.SearchModel;
import com.wd.tech.di.model.ShowModel;

import java.lang.ref.SoftReference;
import java.util.HashMap;

public class SearchPresenter implements SearchContract.SearchPresenter {

    private SearchContract.SearchView searchView;
    private SearchModel searchModel;
    private SoftReference<SearchContract.SearchView> reference;


    @Override
    public void attrView(SearchContract.SearchView searchView) {
        this.searchView = searchView;
        reference = new SoftReference<>(searchView);
        searchModel = new SearchModel();
    }

    @Override
    public void deachView(SearchContract.SearchView searchView) {
        reference.clear();
    }

    @Override
    public void requestData(String title, HashMap params) {
        searchModel.cotainData(title, params, new SearchContract.SearchModel.CallBack() {
            @Override
            public void onCallBack(SerachByTitleBean serachByTitleBean) {
                searchView.SearchData(serachByTitleBean);
            }
        });
    }
}
