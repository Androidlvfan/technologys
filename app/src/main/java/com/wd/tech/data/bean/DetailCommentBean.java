package com.wd.tech.data.bean;

import java.util.List;

public class DetailCommentBean {

    /**
     * result : [{"commentTime":1556071715000,"content":"啦啦啦啦","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2019-04-28/20190428111659.jpg","id":181,"infoId":1,"nickName":"焦老仙君","userId":34},{"commentTime":1555753676000,"content":"行吧","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2019-04-23/20190423111759.jpg","id":126,"infoId":1,"nickName":"破碎","userId":10},{"commentTime":1555591207000,"content":";-)","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":11,"infoId":1,"nickName":"扭曲","userId":57},{"commentTime":1555591129000,"content":";-)","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2019-04-23/20190423111759.jpg","id":10,"infoId":1,"nickName":"破碎","userId":10},{"commentTime":1555591097000,"content":";-)","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2019-04-23/20190423111759.jpg","id":9,"infoId":1,"nickName":"破碎","userId":10}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commentTime : 1556071715000
         * content : 啦啦啦啦
         * headPic : http://mobile.bwstudent.com/images/tech/head_pic/2019-04-28/20190428111659.jpg
         * id : 181
         * infoId : 1
         * nickName : 焦老仙君
         * userId : 34
         */

        public long commentTime;
        public String content;
        public String headPic;
        public int id;
        public int infoId;
        public String nickName;
        public int userId;

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getInfoId() {
            return infoId;
        }

        public void setInfoId(int infoId) {
            this.infoId = infoId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
