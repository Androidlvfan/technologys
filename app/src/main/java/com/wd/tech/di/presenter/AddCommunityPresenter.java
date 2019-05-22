package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.AddCommunityBean;
import com.wd.tech.di.contract.AddCommunityContract;
import com.wd.tech.di.model.AddCommunityModel;

import java.lang.ref.SoftReference;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/13 14:59
 * @fileName:AddFriendPresenter
 * @packageName:com.wd.tech.di.presenter
 */
public class AddCommunityPresenter implements AddCommunityContract.AddCommunityPresenter {

    AddCommunityContract.AddCommunityView addCommunityView;
    private SoftReference<AddCommunityContract.AddCommunityView> reference;
    private AddCommunityModel model;

    @Override
    public void attahView(AddCommunityContract.AddCommunityView addCommunityView) {
        this.addCommunityView = addCommunityView;

        reference = new SoftReference<>(addCommunityView);

        model = new AddCommunityModel();
    }

    @Override
    public void deachView(AddCommunityContract.AddCommunityView addFriendView) {
        reference.clear();
    }

    @Override
    public void requestData(int userId, String sessionId, int communityId, String content) {
        model.containData(userId, sessionId, communityId, content, new AddCommunityContract.AddCommunityModel.CallBack() {
            @Override
            public void onCallBack(AddCommunityBean addCommunityBean) {
                addCommunityView.showAddCommunityData(addCommunityBean);
            }
        });
    }


}
