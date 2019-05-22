package com.wd.tech.di.presenter;

import com.wd.tech.data.bean.DetailCommentBean;
import com.wd.tech.di.contract.CommentContract;
import com.wd.tech.di.model.CommentModel;

import java.lang.ref.SoftReference;
import java.util.HashMap;

public class CommentPresenter implements CommentContract.CommentPresenter {

   private CommentContract.CommentView commentView;
   private CommentModel commentModel;
   private SoftReference<CommentContract.CommentView> reference;
    @Override
    public void attrView(CommentContract.CommentView commentView) {
        this.commentView = commentView;
        reference = new SoftReference<>(commentView);
        commentModel = new CommentModel();
    }

    @Override
    public void deachView(CommentContract.CommentView commentView) {
        reference.clear();
    }

    @Override
    public void requestData(int userId, String sessionId, int infoId, HashMap params) {
        commentModel.cotainData(userId, sessionId, infoId, params, new CommentContract.CommentModel.CallBack() {
            @Override
            public void onCallBack(DetailCommentBean detailCommentBean) {
                commentView.getData(detailCommentBean);
            }
        });
    }
}
