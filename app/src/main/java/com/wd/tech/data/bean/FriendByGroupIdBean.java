package com.wd.tech.data.bean;

import java.util.List;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/16 14:46
 * @fileName:Friend
 * @packageName:com.wd.tech.data.bean
 */
public class FriendByGroupIdBean{


    /**
     * result : [{"friendUid":95,"headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2019-04-24/20190424161957.jpg","nickName":"Aimee~","remarkName":"Aimee~","signature":"正经人都单身","userName":"1fYXtj13581733690","vipFlag":2},{"friendUid":82,"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","nickName":"lamm","remarkName":"lamm","signature":"111","userName":"rAqbDA18888888888","vipFlag":2},{"friendUid":299,"headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2019-04-28/20190428145734.jpg","nickName":"来点风","remarkName":"来点风","signature":"调结构此次几次","userName":"o1XTnA15222222222","vipFlag":2},{"friendUid":18,"headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2019-04-30/20190430140907.jpg","nickName":"嘟嘟","remarkName":"嘟嘟","signature":"123456","userName":"E8YrrR18813175607","vipFlag":2},{"friendUid":98,"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","nickName":"hannah","remarkName":"hannah","signature":"擦擦擦","userName":"upxRux15001010539","vipFlag":2},{"friendUid":89,"headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2019-04-26/20190426081314.jpg","nickName":"好人","remarkName":"好人","signature":"姐弟妹","userName":"gXTtTZ15518044365","vipFlag":2}]
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
         * friendUid : 95
         * headPic : http://mobile.bwstudent.com/images/tech/head_pic/2019-04-24/20190424161957.jpg
         * nickName : Aimee~
         * remarkName : Aimee~
         * signature : 正经人都单身
         * userName : 1fYXtj13581733690
         * vipFlag : 2
         */

        private int friendUid;
        private String headPic;
        private String nickName;
        private String remarkName;
        private String signature;
        private String userName;
        private int vipFlag;

        public int getFriendUid() {
            return friendUid;
        }

        public void setFriendUid(int friendUid) {
            this.friendUid = friendUid;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getRemarkName() {
            return remarkName;
        }

        public void setRemarkName(String remarkName) {
            this.remarkName = remarkName;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getVipFlag() {
            return vipFlag;
        }

        public void setVipFlag(int vipFlag) {
            this.vipFlag = vipFlag;
        }
    }
}
