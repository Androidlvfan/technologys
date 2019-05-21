package com.wd.tech.di.model;

import com.wd.tech.data.bean.SerachByTitleBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.SearchContract;
import com.wd.tech.di.contract.ShowContract;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SearchModel implements SearchContract.SearchModel {

    @Override
    public void cotainData(String title, HashMap<String, String> params, final CallBack callBack) {
        Observable<SerachByTitleBean> searchByTitle = RetrofitUtils.getInstance().create(ApiService.class).getSearchByTitle(title, params);
        searchByTitle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SerachByTitleBean>() {
                    @Override
                    public void accept(SerachByTitleBean serachByTitleBean) throws Exception {
                        callBack.onCallBack(serachByTitleBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
