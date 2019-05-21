package com.wd.tech.di.model;

import com.wd.tech.data.bean.AddCommunityBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.AddCommunityContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/13 14:54
 * @fileName:AddFriendModel
 * @packageName:com.wd.tech.di.model
 */
public class AddCommunityModel implements AddCommunityContract.AddCommunityModel {


    @Override
    public void containData(int userId, String sessionId, int communityId, String content, final CallBack callBack) {
        Observable<AddCommunityBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getAddCommunity(userId, sessionId, communityId,content);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddCommunityBean>() {
                    @Override
                    public void accept(AddCommunityBean addCommunityBean) throws Exception {
                        callBack.onCallBack(addCommunityBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
