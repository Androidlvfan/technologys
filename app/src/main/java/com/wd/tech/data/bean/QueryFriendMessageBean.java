package com.wd.tech.data.bean;

import java.util.List;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/13 19:28
 * @fileName:QueryFriendMessageBean
 * @packageName:com.wd.tech.data.bean
 */
public class QueryFriendMessageBean {


    /**
     * result : {"birthday":913219200000,"email":"884923222@qq.com","headPic":"http://172.17.8.100/images/tech/head_pic/2018-10-16/20181016084640.jpg","integral":90,"myGroupList":[{"blackFlag":0,"groupId":1036,"groupImage":"http://172.17.8.100/images/tech/default/tech.jpg","groupName":"13","hxGroupId":"62678621028353","role":3}],"nickName":"嘉","phone":"17710137468","sex":1,"signature":"嘉的签名","userId":1010,"whetherVip":2}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {
        /**
         * birthday : 913219200000
         * email : 884923222@qq.com
         * headPic : http://172.17.8.100/images/tech/head_pic/2018-10-16/20181016084640.jpg
         * integral : 90
         * myGroupList : [{"blackFlag":0,"groupId":1036,"groupImage":"http://172.17.8.100/images/tech/default/tech.jpg","groupName":"13","hxGroupId":"62678621028353","role":3}]
         * nickName : 嘉
         * phone : 17710137468
         * sex : 1
         * signature : 嘉的签名
         * userId : 1010
         * whetherVip : 2
         */

        private long birthday;
        private String email;
        private String headPic;
        private int integral;
        private String nickName;
        private String phone;
        private int sex;
        private String signature;
        private int userId;
        private int whetherVip;
        private List<MyGroupListBean> myGroupList;

        public long getBirthday() {
            return birthday;
        }

        public void setBirthday(long birthday) {
            this.birthday = birthday;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherVip() {
            return whetherVip;
        }

        public void setWhetherVip(int whetherVip) {
            this.whetherVip = whetherVip;
        }

        public List<MyGroupListBean> getMyGroupList() {
            return myGroupList;
        }

        public void setMyGroupList(List<MyGroupListBean> myGroupList) {
            this.myGroupList = myGroupList;
        }

        public static class MyGroupListBean {
            /**
             * blackFlag : 0
             * groupId : 1036
             * groupImage : http://172.17.8.100/images/tech/default/tech.jpg
             * groupName : 13
             * hxGroupId : 62678621028353
             * role : 3
             */

            private int blackFlag;
            private int groupId;
            private String groupImage;
            private String groupName;
            private String hxGroupId;
            private int role;

            public int getBlackFlag() {
                return blackFlag;
            }

            public void setBlackFlag(int blackFlag) {
                this.blackFlag = blackFlag;
            }

            public int getGroupId() {
                return groupId;
            }

            public void setGroupId(int groupId) {
                this.groupId = groupId;
            }

            public String getGroupImage() {
                return groupImage;
            }

            public void setGroupImage(String groupImage) {
                this.groupImage = groupImage;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public String getHxGroupId() {
                return hxGroupId;
            }

            public void setHxGroupId(String hxGroupId) {
                this.hxGroupId = hxGroupId;
            }

            public int getRole() {
                return role;
            }

            public void setRole(int role) {
                this.role = role;
            }
        }
    }
}
