package com.wd.tech.data.bean;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/13 19:49
 * @fileName:CheckMyFriendBean
 * @packageName:com.wd.tech.data.bean
 */
public class CheckMyFriendBean {


    /**
     * flag : 2
     * message : 对方不是你的好友
     * status : 0000
     */

    private int flag;
    private String message;
    private String status;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
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
}
