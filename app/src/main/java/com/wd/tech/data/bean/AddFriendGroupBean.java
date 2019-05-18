package com.wd.tech.data.bean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/16 16:24
 * @fileName:AddFriendGroupBean
 * @packageName:com.wd.tech.data.bean
 */
public class AddFriendGroupBean {

    /**
     * message : 创建分组成功
     * status : 0000
     * groupId : 1111
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
