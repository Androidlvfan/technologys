package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.CreateGroupBean;
import com.wd.tech.di.contract.CreateGroupContract;
import com.wd.tech.di.model.CreateGroupModel;

import java.lang.ref.SoftReference;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/14 21:59
 * @fileName:CreateGroupPresenter
 * @packageName:com.wd.tech.di.presenter
 */
public class CreateGroupPresenter implements CreateGroupContract.CreateGroupPresenter {

    CreateGroupContract.CreateGroupView createGroupView;
    private SoftReference<CreateGroupContract.CreateGroupView> reference;
    private CreateGroupModel model;

    @Override
    public void attahView(CreateGroupContract.CreateGroupView createGroupView) {
        this.createGroupView = createGroupView;

        reference = new SoftReference<>(createGroupView);

        model = new CreateGroupModel();
    }

    @Override
    public void deachView(CreateGroupContract.CreateGroupView createGroupView) {
        reference.clear();
    }

    @Override
    public void requestData(int userId, String sessionId, String name, String description) {
        model.containData(userId, sessionId, name, description, new CreateGroupContract.CreateGroupModel.CallBack() {
            @Override
            public void onCallBack(CreateGroupBean createGroupBean) {
                createGroupView.showData(createGroupBean);
            }
        });
    }
}
