package com.wd.tech.di.model;

import com.wd.tech.data.bean.CheckMyFriendBean;
import com.wd.tech.data.bean.QueryFriendMessageBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.FriendMessageContract;
import com.wd.tech.di.contract.RegisterContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/13 19:31
 * @fileName:FriendMessageModel
 * @packageName:com.wd.tech.di.model
 */
public class FriendMessageModel implements FriendMessageContract.FriendMessageModel {


    @Override
    public void containData(int userId, String sessionId, int friend, final CallBack callBack) {
        Observable<QueryFriendMessageBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getFriendMessageData(userId, sessionId, friend);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<QueryFriendMessageBean>() {
                    @Override
                    public void accept(QueryFriendMessageBean queryFriendMessageBean) throws Exception {
                        callBack.onCallBack(queryFriendMessageBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void containCheckFriendData(int userId, String sessionId, int friendUid, final CallBack callBack) {
        Observable<CheckMyFriendBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getCheckFriendData(userId, sessionId, friendUid);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CheckMyFriendBean>() {
                    @Override
                    public void accept(CheckMyFriendBean checkMyFriendBean) throws Exception {
                        callBack.onCheckFriendCallBack(checkMyFriendBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
