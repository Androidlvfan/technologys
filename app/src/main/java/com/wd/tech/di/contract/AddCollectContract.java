package com.wd.tech.di.contract;
import com.wd.tech.data.bean.AddCollectBean;

public interface AddCollectContract {

    public interface AddCollectView{
        public void ShowData(AddCollectBean addCollectBean);
    }

    public interface AddCollectPresenter<AddCollectView>{

        public void attrView(AddCollectContract.AddCollectView addCollectView);

        public void deachView(AddCollectContract.AddCollectView addCollectView);

        public void requestData(String infoId,String userId,String sessionId);
    }

    public interface AddCollectModel{

        public void cotainData(CallBack callBack);
        public interface CallBack{
            public void onCallBack(AddCollectBean addCollectView);
        }
    }

}
