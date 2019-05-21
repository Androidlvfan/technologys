package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.di.contract.AddIngFriendContract;
import com.wd.tech.di.model.AddIngFriendModel;

import java.lang.ref.SoftReference;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/13 21:06
 * @fileName:AddIngFriendPresenter
 * @packageName:com.wd.tech.di.presenter
 */
public class AddIngFriendPresenter implements AddIngFriendContract.AddIngFriendPresenter {

    AddIngFriendContract.AddIngFriendView addIngFriendView;
    private SoftReference<AddIngFriendContract.AddIngFriendView> reference;
    private AddIngFriendModel model;

    @Override
    public void attahView(AddIngFriendContract.AddIngFriendView addIngFriendView) {
        this.addIngFriendView = addIngFriendView;

        reference = new SoftReference<>(addIngFriendView);

        model = new AddIngFriendModel();

    }

    @Override
    public void deachView(AddIngFriendContract.AddIngFriendView addIngFriendView) {
        reference.clear();
    }

    @Override
    public void requestData(int userId, String sessionId, int friendUid, String remark) {
        model.containData(userId, sessionId, friendUid, remark, new AddIngFriendContract.AddIngFriendModel.CallBack() {
            @Override
            public void onCallBack(AddIngFriendBean addIngFriendBean) {
                addIngFriendView.showData(addIngFriendBean);
            }
        });
    }
}
