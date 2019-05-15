package com.wd.tech.data.bean;

import java.util.List;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/15 15:28
 * @fileName:GroupNotifyBean
 * @packageName:com.wd.tech.data.bean
 */
public class GroupNotifyBean {

    /**
     * result : [{"groupName":"聚宝盆","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2019-05-10/20190510194446.jpg","nickName":"乐圣","noticeId":356,"noticeTime":1557901979000,"status":1,"type":1},{"groupName":"粗鲁聚集地","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","nickName":"刘李店路口","noticeId":354,"noticeTime":1557843214000,"status":1,"type":1}]
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
         * groupName : 聚宝盆
         * headPic : http://mobile.bwstudent.com/images/tech/head_pic/2019-05-10/20190510194446.jpg
         * nickName : 乐圣
         * noticeId : 356
         * noticeTime : 1557901979000
         * status : 1
         * type : 1
         */

        private String groupName;
        private String headPic;
        private String nickName;
        private String remark;
        private int noticeId;
        private long noticeTime;
        private int status;
        private int type;

        public ResultBean(String remark) {
            this.remark = remark;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
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

        public int getNoticeId() {
            return noticeId;
        }

        public void setNoticeId(int noticeId) {
            this.noticeId = noticeId;
        }

        public long getNoticeTime() {
            return noticeTime;
        }

        public void setNoticeTime(long noticeTime) {
            this.noticeTime = noticeTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
