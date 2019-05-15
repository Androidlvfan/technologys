package com.wd.tech.di.model;

import com.wd.tech.data.bean.CreateGroupBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.CreateGroupContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/14 21:58
 * @fileName:CreateGroupModel
 * @packageName:com.wd.tech.di.model
 */
public class CreateGroupModel implements CreateGroupContract.CreateGroupModel {
    @Override
    public void containData(int userId, String sessionId, String name, String description, final CallBack callBack) {
        Observable<CreateGroupBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getCreateGroupData(userId, sessionId, name, description);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CreateGroupBean>() {
                    @Override
                    public void accept(CreateGroupBean createGroupBean) throws Exception {
                        callBack.onCallBack(createGroupBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
