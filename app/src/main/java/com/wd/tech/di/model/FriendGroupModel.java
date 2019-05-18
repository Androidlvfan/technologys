package com.wd.tech.di.model;

import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.FriendGroupBean;
import com.wd.tech.data.bean.GroupBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.FriendGroupContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/15 20:33
 * @fileName:FriendGroupModel
 * @packageName:com.wd.tech.di.model
 */
public class FriendGroupModel implements FriendGroupContract.FriendGroupModel {
    @Override
    public void containData(int userId, String sessionId, final CallBack callBack) {
        Observable<FriendGroupBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getFriendGroupData(userId, sessionId);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FriendGroupBean>() {
                    @Override
                    public void accept(FriendGroupBean friendGroupBean) throws Exception {
                        callBack.onCallBack(friendGroupBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void containLaHeiFriendData(int userId, String sessionId, int newGroupId, int friendUid, final CallBack callBack) {
        Observable<AddIngFriendBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getLaHeiFriendData(userId, sessionId, newGroupId, friendUid);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddIngFriendBean>() {
                    @Override
                    public void accept(AddIngFriendBean addIngFriendBean) throws Exception {
                        callBack.onLaHeiFriendCallBack(addIngFriendBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });

    }
}
