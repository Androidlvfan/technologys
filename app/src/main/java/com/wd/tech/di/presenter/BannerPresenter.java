package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.BannerBean;
import com.wd.tech.di.contract.BannerContract;
import com.wd.tech.di.model.BannerModel;

import java.lang.ref.SoftReference;

public class BannerPresenter implements BannerContract.BannerPresenter {

    private BannerContract.BannerView bannerView;
    private BannerModel bannerModel;
    private SoftReference<BannerContract.BannerView> reference;

    @Override
    public void attrView(BannerContract.BannerView bannerView) {
        this.bannerView = bannerView;
        reference = new SoftReference<>(bannerView);
        bannerModel = new BannerModel();
    }

    @Override
    public void deachView(BannerContract.BannerView bannerView) {
        reference.clear();
    }

    @Override
    public void requestData() {
        bannerModel.cotainData(new BannerContract.BannerModel.CallBack() {
            @Override
            public void onCallBack(BannerBean bannerBean) {
                bannerView.ShowData(bannerBean);
            }
        });
    }
}
