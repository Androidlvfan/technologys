package com.wd.tech.data.bean;

import java.util.List;

public class CommunityBean {


    /**
     * result : [{"comment":0,"communityCommentVoList":[],"content":"我不知道","file":"","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":779,"nickName":"闫向向","praise":1,"publishTime":1557746125000,"userId":339,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":0,"communityCommentVoList":[],"content":"123","file":"","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":778,"nickName":"闫向","praise":5,"publishTime":1557745798000,"userId":425,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":0,"communityCommentVoList":[],"content":"123","file":"","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":777,"nickName":"闫向","praise":2,"publishTime":1557745726000,"userId":425,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":1,"communityCommentVoList":[{"content":"快","nickName":"块签","userId":370}],"content":"121212","file":"","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":776,"nickName":"楚云飞","praise":8,"publishTime":1557738475000,"userId":389,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":2,"communityCommentVoList":[{"content":"徐红糖","nickName":"Bg","userId":477},{"content":"asd","nickName":"haoran123","userId":502}],"content":"我是谢大炮。。。。。。。","file":"http://mobile.bwstudent.com/images/tech/community_pic/2019-05-13/2318420190513163225.png,http://mobile.bwstudent.com/images/tech/community_pic/2019-05-13/4786020190513163225.png,http://mobile.bwstudent.com/images/tech/community_pic/2019-05-13/3689420190513163225.png","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":775,"nickName":"你","praise":0,"publishTime":1557736345000,"signature":"去去去","userId":479,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":2,"communityCommentVoList":[{"content":"？？","nickName":"gyy","userId":357},{"content":"123","nickName":"你给我冷静一点","userId":369}],"content":"123","file":"","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":774,"nickName":"闫向向","praise":1,"publishTime":1557735475000,"userId":339,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":1,"communityCommentVoList":[{"content":"1111","nickName":"你给我冷静一点","userId":369}],"content":"123","file":"","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":772,"nickName":"闫向向","praise":3,"publishTime":1557735291000,"userId":339,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":0,"communityCommentVoList":[],"content":"123","file":"","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":773,"nickName":"闫向向","praise":1,"publishTime":1557735291000,"userId":339,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":0,"communityCommentVoList":[],"content":"123","file":"","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":768,"nickName":"闫向向","praise":0,"publishTime":1557735290000,"userId":339,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":0,"communityCommentVoList":[],"content":"123","file":"","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":769,"nickName":"闫向向","praise":0,"publishTime":1557735290000,"userId":339,"whetherFollow":2,"whetherGreat":2,"whetherVip":2}]
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
         * comment : 0
         * communityCommentVoList : []
         * content : 我不知道
         * file :
         * headPic : http://mobile.bwstudent.com/images/tech/default/tech.jpg
         * id : 779
         * nickName : 闫向向
         * praise : 1
         * publishTime : 1557746125000
         * userId : 339
         * whetherFollow : 2
         * whetherGreat : 2
         * whetherVip : 2
         * signature : 去去去
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
        private List<?> communityCommentVoList;

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

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public List<?> getCommunityCommentVoList() {
            return communityCommentVoList;
        }

        public void setCommunityCommentVoList(List<?> communityCommentVoList) {
            this.communityCommentVoList = communityCommentVoList;
        }
    }
}
