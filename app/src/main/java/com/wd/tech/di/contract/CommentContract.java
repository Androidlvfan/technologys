package com.wd.tech.di.contract;

import com.wd.tech.data.bean.DetailCommentBean;

import java.util.HashMap;

public class CommentContract {

    public interface CommentView{
        public void getData(DetailCommentBean detailCommentBean);
    }

    public interface CommentPresenter<CommentView>{
        public void attrView(CommentContract.CommentView commentView);
        public void deachView(CommentContract.CommentView commentView);

        public void requestData(int userId,String sessionId,int infoId,HashMap<String,String> params);
    }

    public interface CommentModel{
        public void cotainData(int userId, String sessionId,int infoId,HashMap<String,String> params ,CallBack callBack);
        public interface CallBack{
            public void onCallBack(DetailCommentBean detailCommentBean);
        }
    }
}
