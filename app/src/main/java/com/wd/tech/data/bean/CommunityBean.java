package com.wd.tech.data.bean;

import java.util.List;

public class CommunityBean {

    /**
     * result : [{"comment":3,"communityCommentVoList":[{"content":"傻逼儿子，看你那揍性","nickName":"qq","userId":398},{"content":"我是你爹啊，傻逼儿子","nickName":"朱砂","userId":337},{"content":"对对对","nickName":"qq","userId":398}],"content":"1609B朱砂就是个傻逼，如果认为是的点个赞","file":"","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":785,"nickName":"qq","praise":13,"publishTime":1557749466000,"userId":398,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":1,"communityCommentVoList":[{"content":"哈哈","nickName":"云闲","userId":451}],"content":"","file":"http://mobile.bwstudent.com/images/tech/community_pic/2019-05-13/6923420190513194927.png","headPic":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIfmzKObE2HZ9XLszk3jtYw0ucIX5u7gBW7NeeXURJfhBlIxZ1C6ABNFGNO8TB6urCMPt7RWbvfFg/132","id":784,"nickName":"等风来_GZY","praise":9,"publishTime":1557748167000,"userId":507,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":1,"communityCommentVoList":[{"content":"1","nickName":"云闲","userId":451}],"content":"哈哈","file":"","headPic":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIfmzKObE2HZ9XLszk3jtYw0ucIX5u7gBW7NeeXURJfhBlIxZ1C6ABNFGNO8TB6urCMPt7RWbvfFg/132","id":783,"nickName":"等风来_GZY","praise":2,"publishTime":1557748097000,"userId":507,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":1,"communityCommentVoList":[{"content":"哈哈","nickName":"云闲","userId":451}],"content":"Fghjkk","file":"","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":782,"nickName":"闫向","praise":8,"publishTime":1557746973000,"userId":425,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":0,"communityCommentVoList":[],"content":"","file":"http://mobile.bwstudent.com/images/tech/community_pic/2019-05-13/6145320190513192541.jpg,http://mobile.bwstudent.com/images/tech/community_pic/2019-05-13/9014220190513192541.jpg","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2019-05-09/20190509091210.jpg","id":781,"nickName":"gyy","praise":4,"publishTime":1557746741000,"userId":357,"whetherFollow":2,"whetherGreat":2,"whetherVip":2}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

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
         * comment : 3
         * communityCommentVoList : [{"content":"傻逼儿子，看你那揍性","nickName":"qq","userId":398},{"content":"我是你爹啊，傻逼儿子","nickName":"朱砂","userId":337},{"content":"对对对","nickName":"qq","userId":398}]
         * content : 1609B朱砂就是个傻逼，如果认为是的点个赞
         * file :
         * headPic : http://mobile.bwstudent.com/images/tech/default/tech.jpg
         * id : 785
         * nickName : qq
         * praise : 13
         * publishTime : 1557749466000
         * userId : 398
         * whetherFollow : 2
         * whetherGreat : 2
         * whetherVip : 2
         */

        private int comment;
        private String content;
        private String file;
        private String headPic;
        private int id;
        private String nickName;
        private int praise;
        private long publishTime;
        private int userId;
        private int whetherFollow;
        private int whetherGreat;
        private int whetherVip;
        private String signature;
        private List<CommunityCommentVoListBean> communityCommentVoList;

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
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

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getPraise() {
            return praise;
        }

        public void setPraise(int praise) {
            this.praise = praise;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherFollow() {
            return whetherFollow;
        }

        public void setWhetherFollow(int whetherFollow) {
            this.whetherFollow = whetherFollow;
        }

        public int getWhetherGreat() {
            return whetherGreat;
        }

        public void setWhetherGreat(int whetherGreat) {
            this.whetherGreat = whetherGreat;
        }

        public int getWhetherVip() {
            return whetherVip;
        }

        public void setWhetherVip(int whetherVip) {
            this.whetherVip = whetherVip;
        }

        public List<CommunityCommentVoListBean> getCommunityCommentVoList() {
            return communityCommentVoList;
        }

        public void setCommunityCommentVoList(List<CommunityCommentVoListBean> communityCommentVoList) {
            this.communityCommentVoList = communityCommentVoList;
        }

        public static class CommunityCommentVoListBean {
            /**
             * content : 傻逼儿子，看你那揍性
             * nickName : qq
             * userId : 398
             */

            private String content;
            private String nickName;
            private int userId;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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
}
