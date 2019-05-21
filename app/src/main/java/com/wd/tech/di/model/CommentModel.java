package com.wd.tech.di.model;

import android.util.Log;

import com.wd.tech.data.bean.DetailCommentBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.CommentContract;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CommentModel implements CommentContract.CommentModel {
    @Override
    public void cotainData(int userId, String sessionId, int infoId,HashMap<String,String> params, final CallBack callBack) {
        final Observable<DetailCommentBean> detailComent = RetrofitUtils.getInstance().create(ApiService.class).getDetailComent(userId, sessionId, infoId,params);
        detailComent.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DetailCommentBean>() {
                    @Override
                    public void accept(DetailCommentBean detailCommentBean) throws Exception {
                        callBack.onCallBack(detailCommentBean);
                        Log.i("teg",detailCommentBean.getMessage()+"");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
