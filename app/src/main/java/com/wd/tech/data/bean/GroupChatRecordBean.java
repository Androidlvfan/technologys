package com.wd.tech.data.bean;

import java.util.List;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/17 19:29
 * @fileName:GroupChatRecordBean
 * @packageName:com.wd.tech.data.bean
 */
public class GroupChatRecordBean {


    /**
     * result : [{"chatImage":"","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","nickName":"计算机","userId":437}]
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
         * chatImage :
         * headPic : http://mobile.bwstudent.com/images/tech/default/tech.jpg
         * nickName : 计算机
         * userId : 437
         */

        private String chatImage;
        private String headPic;
        private String nickName;
        private int userId;
        private String chatContent;
        private Long chatTime;

        public ResultBean(String chatContent, Long chatTime) {
            this.chatContent = chatContent;
            this.chatTime = chatTime;
        }

        public String getChatContent() {
            return chatContent;
        }

        public void setChatContent(String chatContent) {
            this.chatContent = chatContent;
        }

        public Long getChatTime() {
            return chatTime;
        }

        public void setChatTime(Long chatTime) {
            this.chatTime = chatTime;
        }

        public String getChatImage() {
            return chatImage;
        }

        public void setChatImage(String chatImage) {
            this.chatImage = chatImage;
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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
