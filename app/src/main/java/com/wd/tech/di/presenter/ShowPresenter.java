package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.ShowBean;
import com.wd.tech.di.contract.ShowContract;
import com.wd.tech.di.model.ShowModel;
import java.lang.ref.SoftReference;
import java.util.HashMap;

public class ShowPresenter implements ShowContract.ShowPresenter {

    private ShowContract.ShowView showView;
    private ShowModel showModel;
    private SoftReference<ShowContract.ShowView> reference;


    @Override
    public void attrView(ShowContract.ShowView showView) {
        this.showView = showView;
        reference = new SoftReference<>(showView);
        showModel = new ShowModel();
    }

    @Override
    public void deachView(ShowContract.ShowView showView) {
        reference.clear();
    }

    @Override
    public void requestData(HashMap params, String userId, String sessionId) {
        showModel.cotainData(params, userId, sessionId, new ShowContract.ShowModel.CallBack() {
            @Override
            public void onCallBack(ShowBean showBean) {
                showView.ShowData(showBean);
            }
        });
    }
}
