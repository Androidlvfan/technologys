package com.wd.tech.di.model;

import android.util.Log;

import com.wd.tech.data.bean.AddFriendBean;
import com.wd.tech.data.bean.AddGroupBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.AddFriendContract;

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
public class AddFriendModel implements AddFriendContract.AddFriendModel {


    @Override
    public void containData(int userId, String sessionId, String phone, final CallBack callBack) {
        Observable<AddFriendBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getAddFriendData(userId, sessionId, phone);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddFriendBean>() {
                    @Override
                    public void accept(AddFriendBean addFriendBean) throws Exception {
                        callBack.onCallBack(addFriendBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("AddFriendModel", throwable.getMessage());
                    }
                });
    }

    @Override
    public void containAddGroupData(int userId, String sessionId, int groupId, final CallBack callBack) {
        Observable<AddGroupBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getGroupIdData(userId, sessionId, groupId);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddGroupBean>() {
                    @Override
                    public void accept(AddGroupBean addGroupBean) throws Exception {
                        callBack.onAddGroupCallBack(addGroupBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("AddFriendModel", throwable.getMessage());
                    }
                });
    }
}
