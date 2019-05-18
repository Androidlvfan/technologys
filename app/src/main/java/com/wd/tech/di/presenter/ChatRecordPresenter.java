package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.ChatRecordBean;
import com.wd.tech.di.contract.ChatRecordContract;
import com.wd.tech.di.model.ChatRecordModel;

import java.lang.ref.SoftReference;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/17 16:02
 * @fileName:ChatRecordPresenter
 * @packageName:com.wd.tech.di.presenter
 */
public class ChatRecordPresenter implements ChatRecordContract.ChatRecordPresenter {

    ChatRecordContract.ChatRecordView chatRecordView;
    private SoftReference<ChatRecordContract.ChatRecordView> reference;
    private ChatRecordModel model;


    @Override
    public void attahView(ChatRecordContract.ChatRecordView chatRecordView) {
        this.chatRecordView = chatRecordView;

        reference = new SoftReference<>(chatRecordView);

        model = new ChatRecordModel();

    }

    @Override
    public void deachView(ChatRecordContract.ChatRecordView chatRecordView) {
        reference.clear();
    }

    @Override
    public void requestData(int userId, String sessionId, int friendUid, int page, int count) {
        model.containData(userId, sessionId, friendUid, page, count, new ChatRecordContract.ChatRecordModel.CallBack() {
            @Override
            public void onCallBack(ChatRecordBean chatRecordBean) {
                chatRecordView.showData(chatRecordBean);
            }
        });
    }
}
