package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.GroupNotifyBean;
import com.wd.tech.di.contract.GroupNotifyContract;
import com.wd.tech.di.model.GroupNotifyModel;

import java.lang.ref.SoftReference;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/15 15:38
 * @fileName:GroupNotifyPresenter
 * @packageName:com.wd.tech.di.presenter
 */
public class GroupNotifyPresenter implements GroupNotifyContract.GroupNotifyPresenter {

    GroupNotifyContract.GroupNotifyView groupNotifyView;
    private SoftReference<GroupNotifyContract.GroupNotifyView> reference;
    private GroupNotifyModel model;



    @Override
    public void attahView(GroupNotifyContract.GroupNotifyView groupNotifyView) {
        this.groupNotifyView = groupNotifyView;

        reference = new SoftReference<>(groupNotifyView);

        model = new GroupNotifyModel();
    }

    @Override
    public void deachView(GroupNotifyContract.GroupNotifyView groupNotifyView) {
        reference.clear();
    }

    @Override
    public void requestData(int userId, String sessionId, int page, int count) {
        model.containData(userId,sessionId,page, count, new GroupNotifyContract.GroupNotifyModel.CallBack() {
            @Override
            public void onCallBack(GroupNotifyBean groupNotifyBean) {
                groupNotifyView.showData(groupNotifyBean);
            }

            @Override
            public void onReviewGroupCallBack(AddIngFriendBean addIngFriendBean) {

            }
        });
    }

    @Override
    public void requestReviewGroupData(int userId, String sessionId, int noticeId, int flag) {
        model.containReviewGroupData(userId, sessionId, noticeId, flag, new GroupNotifyContract.GroupNotifyModel.CallBack() {
            @Override
            public void onCallBack(GroupNotifyBean groupNotifyBean) {

            }

            @Override
            public void onReviewGroupCallBack(AddIngFriendBean addIngFriendBean) {
                groupNotifyView.showReviewGrouData(addIngFriendBean);
            }
        });
    }


}
