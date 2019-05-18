package com.wd.tech.di.model;

import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.GroupChatRecordBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.GroupChatContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/17 19:37
 * @fileName:GroupChatModel
 * @packageName:com.wd.tech.di.model
 */
public class GroupChatModel implements GroupChatContract.GroupChatModel {
    @Override
    public void containSendGroupMsg(int userId, String sessionId, int groupId, String content, final CallBack callBack) {
        Observable<AddIngFriendBean> observable = RetrofitUtils.getInstance().create(ApiService.class).SendGroupMessage(userId, sessionId, groupId, content);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddIngFriendBean>() {
                    @Override
                    public void accept(AddIngFriendBean addIngFriendBean) throws Exception {
                        callBack.onSendGroupMsgCallBack(addIngFriendBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void containGroupChatData(int userId, String sessionId, int groupId, int page, int count, final CallBack callBack) {
        Observable<GroupChatRecordBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getGroupChatRecordData(userId, sessionId, groupId, page, count);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GroupChatRecordBean>() {
                    @Override
                    public void accept(GroupChatRecordBean groupChatRecordBean) throws Exception {
                        callBack.onGroupChatCallBack(groupChatRecordBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
