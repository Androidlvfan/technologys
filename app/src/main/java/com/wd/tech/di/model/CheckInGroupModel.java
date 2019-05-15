package com.wd.tech.di.model;

import android.util.Log;

import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.CheckMyFriendBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.CheckInGroupContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/14 20:49
 * @fileName:CheckInGroupModel
 * @packageName:com.wd.tech.di.model
 */
public class CheckInGroupModel implements CheckInGroupContract.CheckInGroupModel {
    @Override
    public void containData(int userId, String sessionId, int groupId, final CallBack callBack) {
        Observable<CheckMyFriendBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getWhetherInGroupData(userId, sessionId, groupId);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CheckMyFriendBean>() {
                    @Override
                    public void accept(CheckMyFriendBean checkMyFriendBean) throws Exception {
                        callBack.onCallBack(checkMyFriendBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("CheckInGroupModel", throwable.getMessage());
                    }
                });
    }

    @Override
    public void containAddIngGroupData(int userId, String sessionId, int groupId, String remark, final CallBack callBack) {
        Observable<AddIngFriendBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getAddIngGroupData(userId, sessionId, groupId,remark);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddIngFriendBean>() {
                    @Override
                    public void accept(AddIngFriendBean addIngFriendBean) throws Exception {
                        callBack.onAddIngGroupCallBack(addIngFriendBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("CheckInGroupModel", throwable.getMessage());
                    }
                });
    }
}
