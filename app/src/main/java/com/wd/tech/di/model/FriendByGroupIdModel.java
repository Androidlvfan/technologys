package com.wd.tech.di.model;

import com.wd.tech.data.bean.FriendByGroupIdBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.FriendByGroupIdContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/16 15:00
 * @fileName:FriendByGroupIdModel
 * @packageName:com.wd.tech.di.model
 */
public class FriendByGroupIdModel implements FriendByGroupIdContract.FriendByGroupIdModel
{
    @Override
    public void containData(int userId, String sessionId, int groupId, final CallBack callBack) {
        Observable<FriendByGroupIdBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getFriendByGroupIdData(userId, sessionId, groupId);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FriendByGroupIdBean>() {
                    @Override
                    public void accept(FriendByGroupIdBean friendByGroupIdBean) throws Exception {
                        callBack.onCallBack(friendByGroupIdBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });


    }
}
