package com.wd.tech.data.bean;

public class Detail_FailBean {

    /**
     * result : {"comment":9,"id":1,"integralCost":0,"praise":5,"readPower":2,"releaseTime":1535448349000,"share":6,"source":"本文转自微信公众号\u201c黄有璨\u201d","summary":"过去几天，滴滴顺风车事件喧嚣甚上，纵观整个事件前后发生的种种，颇有些思考和感慨。","thumbnail":"https://img.huxiucdn.com/article/cover/201808/28/103850448205.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg","title":"关于滴滴顺风车事件的几点思考","whetherCollection":2,"whetherGreat":2,"yuanCost":1}
     * message : 查询成功
     * status : 0000
     */

    public ResultBean result;
    public String message;
    public String status;

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
         * comment : 9
         * id : 1
         * integralCost : 0
         * praise : 5
         * readPower : 2
         * releaseTime : 1535448349000
         * share : 6
         * source : 本文转自微信公众号“黄有璨”
         * summary : 过去几天，滴滴顺风车事件喧嚣甚上，纵观整个事件前后发生的种种，颇有些思考和感慨。
         * thumbnail : https://img.huxiucdn.com/article/cover/201808/28/103850448205.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg
         * title : 关于滴滴顺风车事件的几点思考
         * whetherCollection : 2
         * whetherGreat : 2
         * yuanCost : 1
         */

        public int comment;
        public int id;
        public int integralCost;
        public int praise;
        public int readPower;
        public long releaseTime;
        public int share;
        public String source;
        public String summary;
        public String thumbnail;
        public String title;
        public int whetherCollection;
        public int whetherGreat;
        public int yuanCost;

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIntegralCost() {
            return integralCost;
        }

        public void setIntegralCost(int integralCost) {
            this.integralCost = integralCost;
        }

        public int getPraise() {
            return praise;
        }

        public void setPraise(int praise) {
            this.praise = praise;
        }

        public int getReadPower() {
            return readPower;
        }

        public void setReadPower(int readPower) {
            this.readPower = readPower;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getWhetherCollection() {
            return whetherCollection;
        }

        public void setWhetherCollection(int whetherCollection) {
            this.whetherCollection = whetherCollection;
        }

        public int getWhetherGreat() {
            return whetherGreat;
        }

        public void setWhetherGreat(int whetherGreat) {
            this.whetherGreat = whetherGreat;
        }

        public int getYuanCost() {
            return yuanCost;
        }

        public void setYuanCost(int yuanCost) {
            this.yuanCost = yuanCost;
        }
    }
}
