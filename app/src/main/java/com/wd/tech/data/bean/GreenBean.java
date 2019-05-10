package com.wd.tech.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 16:38
 * @fileName:GreenBean
 * @packageName:com.wd.tech.dimensionalitytechnology.data.bean
 */
@Entity
public class GreenBean {

    @Id
    Long id;

    private int userId;
    private String sessionId;
    @Generated(hash = 616861712)
    public GreenBean(Long id, int userId, String sessionId) {
        this.id = id;
        this.userId = userId;
        this.sessionId = sessionId;
    }
    @Generated(hash = 1002137420)
    public GreenBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getUserId() {
        return this.userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getSessionId() {
        return this.sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }


}
