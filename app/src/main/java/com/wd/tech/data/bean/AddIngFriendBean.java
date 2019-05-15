package com.wd.tech.data.bean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/13 20:59
 * @fileName:AddIngFriendBean
 * @packageName:com.wd.tech.data.bean
 */
public class AddIngFriendBean
{

    /**
     * message : 已发送好友请求
     * status : 0000
     */

    private String message;
    private String status;

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
}
