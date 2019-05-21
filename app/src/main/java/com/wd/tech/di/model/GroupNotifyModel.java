package com.wd.tech.di.model;

import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.GroupNotifyBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.GroupNotifyContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/15 15:37
 * @fileName:GroupNotifyModel
 * @packageName:com.wd.tech.di.model
 */
public class GroupNotifyModel implements GroupNotifyContract.GroupNotifyModel {
    @Override
    public void containData(int userId, String sessionId, int page, int count, final CallBack callBack) {
        Observable<GroupNotifyBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getGroupNotifyData(userId,sessionId,page, count);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GroupNotifyBean>() {
                    @Override
                    public void accept(GroupNotifyBean groupNotifyBean) throws Exception {
                        callBack.onCallBack(groupNotifyBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void containReviewGroupData(int userId, String sessionId, int noticeId, int flag, final CallBack callBack) {
        Observable<AddIngFriendBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getReviewGroupData(userId,sessionId,noticeId,flag);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddIngFriendBean>() {
                    @Override
                    public void accept(AddIngFriendBean addIngFriendBean) throws Exception {
                        callBack.onReviewGroupCallBack(addIngFriendBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
