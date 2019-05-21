package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.AddFriendBean;
import com.wd.tech.data.bean.AddGroupBean;
import com.wd.tech.di.contract.AddFriendContract;
import com.wd.tech.di.model.AddFriendModel;

import java.lang.ref.SoftReference;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/13 14:59
 * @fileName:AddFriendPresenter
 * @packageName:com.wd.tech.di.presenter
 */
public class AddFriendPresenter implements AddFriendContract.AddFriendPresenter {

    AddFriendContract.AddFriendView addFriendView;
    private SoftReference<AddFriendContract.AddFriendView> reference;
    private AddFriendModel model;

    @Override
    public void attahView(AddFriendContract.AddFriendView addFriendView) {
        this.addFriendView = addFriendView;

        reference = new SoftReference<>(addFriendView);

        model = new AddFriendModel();
    }

    @Override
    public void deachView(AddFriendContract.AddFriendView addFriendView) {
        reference.clear();
    }

    @Override
    public void requestData(int userId, String sessionId, String phone) {
        model.containData(userId, sessionId, phone, new AddFriendContract.AddFriendModel.CallBack() {
            @Override
            public void onCallBack(AddFriendBean addFriendBean) {
                addFriendView.showAddFriendData(addFriendBean);
            }

            @Override
            public void onAddGroupCallBack(AddGroupBean addGroupBean) {

            }
        });
    }

    @Override
    public void requestAddGroupData(int userId, String sessionId, int groupId) {
        model.containAddGroupData(userId, sessionId, groupId, new AddFriendContract.AddFriendModel.CallBack() {
            @Override
            public void onCallBack(AddFriendBean addFriendBean) {

            }

            @Override
            public void onAddGroupCallBack(AddGroupBean addGroupBean) {
                addFriendView.showAddGroupData(addGroupBean);
            }
        });
    }


}
