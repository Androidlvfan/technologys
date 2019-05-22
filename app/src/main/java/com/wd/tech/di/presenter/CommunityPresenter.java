package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.CommunityBean;
import com.wd.tech.di.contract.CommunityContract;
import com.wd.tech.di.model.CommunityModel;

import java.lang.ref.SoftReference;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 20:29
 * @fileName:RegisterPresenter
 * @packageName:com.wd.tech.di.presenter
 */
public class CommunityPresenter implements CommunityContract.CommunityPresenter {

    private CommunityContract.CommunityView communityView;
    private SoftReference<CommunityContract.CommunityView> reference;
    private CommunityModel model;

    @Override
    public void attahView(CommunityContract.CommunityView communityView) {
        this.communityView = communityView;
        reference = new SoftReference<>(communityView);
        model = new CommunityModel();
    }

    @Override
    public void deachView(CommunityContract.CommunityView communityView) {
        reference.clear();
    }

    @Override
    public void requestData(int page,int count) {
        model.containData(page, count, new CommunityContract. CommunityModel.CallBack() {
            @Override
            public void onCallBack(CommunityBean communityBean) {
                communityView.showData(communityBean);
            }
        });






};

}