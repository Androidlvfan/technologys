package com.wd.tech.data.bean;

import java.util.List;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/16 22:20
 * @fileName:ChatRecordBean
 * @packageName:com.wd.tech.data.bean
 */
public class ChatRecordBean {


    /**
     * result : [{"chatTime":1538965387000,"content":"KUxGMCS2n4wioVMAW77A0eW3Fv7MB5l7qBXZBhcPLFVKo/QzvpKB5IV4LRdcQdboRBe7mUNo7u7+huESR9Zfpu6Ox7+rULcvGFkC1FQmGJXrlRqo7QggDwzTKCwbF4NpSq3Kxa+TyO34k7l4FQswQqHlRrtAvkLJixWcPVxkM8U=","nickName":"小白","picUrl":"http://172.17.8.100/images/tech/head_pic/2018-09-26/20180926155256.jpg"}]
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
         * chatTime : 1538965387000
         * content : KUxGMCS2n4wioVMAW77A0eW3Fv7MB5l7qBXZBhcPLFVKo/QzvpKB5IV4LRdcQdboRBe7mUNo7u7+huESR9Zfpu6Ox7+rULcvGFkC1FQmGJXrlRqo7QggDwzTKCwbF4NpSq3Kxa+TyO34k7l4FQswQqHlRrtAvkLJixWcPVxkM8U=
         * nickName : 小白
         * picUrl : http://172.17.8.100/images/tech/head_pic/2018-09-26/20180926155256.jpg
         */

        private long chatTime;
        private String content;
        private String nickName;
        private String picUrl;

        public long getChatTime() {
            return chatTime;
        }

        public void setChatTime(long chatTime) {
            this.chatTime = chatTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }
    }
}
