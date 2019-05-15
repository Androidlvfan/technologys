package com.wd.tech.di.model;

import com.wd.tech.data.bean.GroupBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.GroupContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/15 10:12
 * @fileName:GroupModel
 * @packageName:com.wd.tech.di.model
 */
public class GroupModel implements GroupContract.GroupModel {
    @Override
    public void containGroupsByUserData(int userId, String sessionId, final CallBack callBack) {
        Observable<GroupBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getGroupsByUserData(userId, sessionId);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GroupBean>() {
                    @Override
                    public void accept(GroupBean groupBean) throws Exception {
                        callBack.onGroupsByUserCallBack(groupBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void containJoinedGroupData(int userId, String sessionId, final CallBack callBack) {
        Observable<GroupBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getJoinedGroupData(userId, sessionId);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GroupBean>() {
                    @Override
                    public void accept(GroupBean groupBean) throws Exception {
                        callBack.onJoinedGroupCallBack(groupBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
