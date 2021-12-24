package com.bw.module_mine.bean;

import java.util.List;

/**
 * ─────────────────────────────────────────────────────────────────────────
 * ─████████──████████─██████████████─██████──────────██████─██████████████─
 * ─██░░░░██──██░░░░██─██░░░░░░░░░░██─██░░██████████──██░░██─██░░░░░░░░░░██─
 * ─████░░██──██░░████─██░░██████░░██─██░░░░░░░░░░██──██░░██─██░░██████████─
 * ───██░░░░██░░░░██───██░░██──██░░██─██░░██████░░██──██░░██─██░░██─────────
 * ───████░░░░░░████───██░░██████░░██─██░░██──██░░██──██░░██─██░░██─────────
 * ─────████░░████─────██░░░░░░░░░░██─██░░██──██░░██──██░░██─██░░██──██████─
 * ───────██░░██───────██░░██████░░██─██░░██──██░░██──██░░██─██░░██──██░░██─
 * ───────██░░██───────██░░██──██░░██─██░░██──██░░██████░░██─██░░██──██░░██─
 * ───────██░░██───────██░░██──██░░██─██░░██──██░░░░░░░░░░██─██░░██████░░██─
 * ───────██░░██───────██░░██──██░░██─██░░██──██████████░░██─██░░░░░░░░░░██─
 * ───────██████───────██████──██████─██████──────────██████─██████████████─
 * ─────────────────────────────────────────────────────────────────────────
 **/
public class LogisticsBean {


    /**
     * code : 200
     * data : [{"id":1,"displaytime":"2019/6/1 17:56:24","description":"由仓库发出","goodscode":"1"},{"id":2,"displaytime":"2019/6/2 17:57:05","description":"到达宁波转仓","goodscode":"1"},{"id":3,"displaytime":"2019/6/4 17:57:35","description":"到达秦皇岛转运中心","goodscode":"1"},{"id":4,"displaytime":"2019/6/6 17:58:05","description":"到达北京仓库","goodscode":"1"},{"id":5,"displaytime":"2019/6/7 17:58:32","description":"到达西二旗","goodscode":"1"},{"id":6,"displaytime":"2019/6/8 17:58:54","description":"送达八维研修学院，买家已收货","goodscode":"1"}]
     * msg : 获取物流信息成功
     */

    private int code;
    private String msg;
    /**
     * id : 1
     * displaytime : 2019/6/1 17:56:24
     * description : 由仓库发出
     * goodscode : 1
     */

    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        private String displaytime;
        private String description;
        private String goodscode;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDisplaytime() {
            return displaytime;
        }

        public void setDisplaytime(String displaytime) {
            this.displaytime = displaytime;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getGoodscode() {
            return goodscode;
        }

        public void setGoodscode(String goodscode) {
            this.goodscode = goodscode;
        }
    }
}
