package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.CheckMyFriendBean;
import com.wd.tech.di.contract.CheckInGroupContract;
import com.wd.tech.di.model.CheckInGroupModel;

import java.lang.ref.SoftReference;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/14 20:51
 * @fileName:CheckInGroupPresenter
 * @packageName:com.wd.tech.di.presenter
 */
public class CheckInGroupPresenter implements CheckInGroupContract.CheckInGroupPresenter {

    CheckInGroupContract.CheckInGroupView checkInGroupView;
    private SoftReference<CheckInGroupContract.CheckInGroupView> check;
    private CheckInGroupModel checkInGroupModel;

    @Override
    public void attahView(CheckInGroupContract.CheckInGroupView checkInGroupView) {
        this.checkInGroupView = checkInGroupView;

        check = new SoftReference<>(checkInGroupView);

        checkInGroupModel = new CheckInGroupModel();

    }



    @Override
    public void deachView(CheckInGroupContract.CheckInGroupView checkInGroupView) {
        check.clear();
    }

    @Override
    public void requestData(int userId, String sessionId, int groupId) {
        checkInGroupModel.containData(userId, sessionId, groupId, new CheckInGroupContract.CheckInGroupModel.CallBack() {
            @Override
            public void onCallBack(CheckMyFriendBean checkMyFriendBean) {
                checkInGroupView.showCheckInGroupData(checkMyFriendBean);
            }

            @Override
            public void onAddIngGroupCallBack(AddIngFriendBean addIngFriendBean) {

            }
        });
    }

    @Override
    public void requestAddIngGroupData(int userId, String sessionId, int groupId, String remark) {
        checkInGroupModel.containAddIngGroupData(userId, sessionId, groupId, remark, new CheckInGroupContract.CheckInGroupModel.CallBack() {
            @Override
            public void onCallBack(CheckMyFriendBean checkMyFriendBean) {

            }

            @Override
            public void onAddIngGroupCallBack(AddIngFriendBean addIngFriendBean) {
                checkInGroupView.showAddIngGroupData(addIngFriendBean);
            }
        });
    }
}
