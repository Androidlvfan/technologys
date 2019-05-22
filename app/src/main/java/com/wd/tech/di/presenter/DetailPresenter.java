package com.wd.tech.di.presenter;

import android.util.Log;

import com.wd.tech.data.bean.DetailOkBean;
import com.wd.tech.di.contract.DetailContract;
import com.wd.tech.di.model.DetailModel;
import java.lang.ref.SoftReference;

public class DetailPresenter implements DetailContract.DetailPresenter {

    private DetailContract.DetailView detailView;
    private DetailModel detailModel;
    private SoftReference<DetailContract.DetailView> reference;


    @Override
    public void attrView(DetailContract.DetailView detailView) {
        this.detailView = detailView;
        reference = new SoftReference<>(detailView);
        detailModel = new DetailModel();
    }

    @Override
    public void deachView(DetailContract.DetailView detailView) {
        reference.clear();
    }

    @Override
    public void requestData(int userId, String sessionId, int id) {
        detailModel.cotainData(userId, sessionId, id, new DetailContract.DetailModel.Callback() {
            @Override
            public void Callback(DetailOkBean detailOkBean) {
                detailView.ShowData(detailOkBean);
                Log.e("tag","11p"+detailOkBean.getMessage());
            }
        });
    }
}
