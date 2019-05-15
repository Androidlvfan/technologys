package com.wd.tech.data.bean;

import java.util.List;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/15 10:08
 * @fileName:GroupBean
 * @packageName:com.wd.tech.data.bean
 */
public class GroupBean {

    /**
     * result : [{"groupId":10000,"groupImage":"D:/image/2018-09-19/20180919083221.jpg","groupName":"天下第一","hxGroupId":"1"}]
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
         * groupId : 10000
         * groupImage : D:/image/2018-09-19/20180919083221.jpg
         * groupName : 天下第一
         * hxGroupId : 1
         */

        private int groupId;
        private String groupImage;
        private String groupName;
        private String hxGroupId;

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
    }
}
