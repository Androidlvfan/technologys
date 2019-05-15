package com.wd.tech.data.bean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/14 21:56
 * @fileName:CreateGroupBean
 * @packageName:com.wd.tech.data.bean
 */
public class CreateGroupBean {


    /**
     * message : 创建成功
     * status : 0000
     * groupId : 12
     */

    private String message;
    private String status;
    private int groupId;

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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}

