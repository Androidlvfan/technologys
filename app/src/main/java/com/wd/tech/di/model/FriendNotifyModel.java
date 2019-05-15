package com.wd.tech.di.model;

import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.FriendNotifyBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.FriendNotifyContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/15 19:36
 * @fileName:FriendNotifyModel
 * @packageName:com.wd.tech.di.model
 */
public class FriendNotifyModel implements FriendNotifyContract.FriendNotifyModel {
    @Override
    public void containFriendNotifyData(int userId, String sessionId, int page, int count, final CallBack callBack) {
        Observable<FriendNotifyBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getFriendNotifyData(userId, sessionId, page, count);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FriendNotifyBean>() {
                    @Override
                    public void accept(FriendNotifyBean friendNotifyBean) throws Exception {
                        callBack.onFriendNotifyCallBack(friendNotifyBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void containReviewFriendData(int userId, String sessionId, int noticeId, int flag, final CallBack callBack) {
        Observable<AddIngFriendBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getReviewFriendData(userId, sessionId, noticeId, flag);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddIngFriendBean>() {
                    @Override
                    public void accept(AddIngFriendBean addIngFriendBean) throws Exception {
                        callBack.onReviewFriendCallBack(addIngFriendBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
