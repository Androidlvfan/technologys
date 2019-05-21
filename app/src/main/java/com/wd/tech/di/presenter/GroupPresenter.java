package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.GroupBean;
import com.wd.tech.di.contract.GroupContract;
import com.wd.tech.di.model.GroupModel;

import java.lang.ref.SoftReference;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/15 10:12
 * @fileName:GroupPresenter
 * @packageName:com.wd.tech.di.presenter
 */
public class GroupPresenter implements GroupContract.GroupPresenter {

    GroupContract.GroupView groupView;
    private SoftReference<GroupContract.GroupView> reference;
    private GroupModel model;

    @Override
    public void attahView(GroupContract.GroupView groupView) {
        this.groupView = groupView;

        reference = new SoftReference<>(groupView);

        model = new GroupModel();
    }

    @Override
    public void deachView(GroupContract.GroupView groupView) {
        reference.clear();
    }

    @Override
    public void requestGroupsByUserData(int userId, String sessionId) {
        model.containGroupsByUserData(userId, sessionId, new GroupContract.GroupModel.CallBack() {
            @Override
            public void onGroupsByUserCallBack(GroupBean groupBean) {
                groupView.showGroupsByUserData(groupBean);
            }

            @Override
            public void onJoinedGroupCallBack(GroupBean groupBean) {

            }
        });
    }

    @Override
    public void requestJoinedGroupData(int userId, String sessionId) {
        model.containJoinedGroupData(userId, sessionId, new GroupContract.GroupModel.CallBack() {
            @Override
            public void onGroupsByUserCallBack(GroupBean groupBean) {

            }

            @Override
            public void onJoinedGroupCallBack(GroupBean groupBean) {
                groupView.showJoinedGroupData(groupBean);
            }
        });
    }
}
