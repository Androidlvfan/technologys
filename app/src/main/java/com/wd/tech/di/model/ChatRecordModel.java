package com.wd.tech.di.model;

import com.wd.tech.data.bean.ChatRecordBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.ChatRecordContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/17 16:01
 * @fileName:ChatRecordModel
 * @packageName:com.wd.tech.di.model
 */
public class ChatRecordModel implements ChatRecordContract.ChatRecordModel {
    @Override
    public void containData(int userId, String sessionId, int friendUid, int page, int count, final CallBack callBack) {
        Observable<ChatRecordBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getChatRecordData(userId, sessionId, friendUid, page, count);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ChatRecordBean>() {
                    @Override
                    public void accept(ChatRecordBean chatRecordBean) throws Exception {
                        callBack.onCallBack(chatRecordBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
