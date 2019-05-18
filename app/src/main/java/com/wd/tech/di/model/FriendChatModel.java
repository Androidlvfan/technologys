package com.wd.tech.di.model;

import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.FriendChatRecordBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.FriendChatContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/17 21:21
 * @fileName:FriendChatModel
 * @packageName:com.wd.tech.di.model
 */
public class FriendChatModel implements FriendChatContract.FriendChatModel {
    @Override
    public void containSendFriendMsg(int userId, String sessionId, int receiveUid, String content, final CallBack callBack) {
        Observable<AddIngFriendBean> observable = RetrofitUtils.getInstance().create(ApiService.class).SendFriendMessage(userId, sessionId, receiveUid, content);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddIngFriendBean>() {
                    @Override
                    public void accept(AddIngFriendBean addIngFriendBean) throws Exception {
                        callBack.onSendFriendMsgCallBack(addIngFriendBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });

    }

    @Override
    public void containFriendChatData(int userId, String sessionId, int friendUid, int page, int count, final CallBack callBack) {
        Observable<FriendChatRecordBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getFriendChatRecordData(userId, sessionId, friendUid,page, count);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FriendChatRecordBean>() {
                    @Override
                    public void accept(FriendChatRecordBean friendChatRecordBean) throws Exception {
                        callBack.onFriendChatCallBack(friendChatRecordBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
