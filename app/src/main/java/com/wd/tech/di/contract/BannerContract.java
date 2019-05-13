package com.wd.tech.di.contract;

import com.wd.tech.data.bean.BannerBean;

public interface BannerContract {

    public interface BannerView{
        public void ShowData(BannerBean bannerBean);
    }

    public interface BannerPresenter<BannerView>{

        public void attrView(BannerContract.BannerView bannerView);

        public void deachView(BannerContract.BannerView bannerView);

        public void requestData();
    }

    public interface BannerModel{

        public void cotainData(CallBack callBack);
            public interface CallBack{
                public void onCallBack(BannerBean bannerBean);
            }
    }
}
