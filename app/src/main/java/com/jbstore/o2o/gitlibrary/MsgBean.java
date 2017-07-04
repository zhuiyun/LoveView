package com.jbstore.o2o.gitlibrary;

import com.jbstore.o2o.mylibrary.SelectString;

import java.util.List;

/**
 * Created by gwy on 2017/6/9.
 */

public class MsgBean {

    /**
     * result : 0
     * message : Success
     * data : [{"Id":3,"Name":"餐饮美食"},{"Id":16,"Name":"食品百货"},{"Id":5,"Name":"服饰百货"},{"Id":14,"Name":"日用百货"},{"Id":11,"Name":"母婴用品"},{"Id":8,"Name":"酒店宾馆"},{"Id":9,"Name":"旅行票务"},{"Id":20,"Name":"休闲娱乐"},{"Id":10,"Name":"美容护理"},{"Id":15,"Name":"摄影婚庆"},{"Id":19,"Name":"鲜花礼品"},{"Id":17,"Name":"数码家电"},{"Id":13,"Name":"汽车行业"},{"Id":7,"Name":"家居建材"},{"Id":4,"Name":"房地产业"},{"Id":21,"Name":"医疗器械"},{"Id":18,"Name":"文体办公"},{"Id":6,"Name":"广告印刷"},{"Id":12,"Name":"其它行业"}]
     * command : Categories
     * action : GetCategories
     */

    private int result;
    private String message;
    private String command;
    private String action;
    private List<DataBean> data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends SelectString {
        /**
         * Id : 3
         * Name : 餐饮美食
         */

        private int Id;
        private String Name;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        @Override
        public String getString() {
            return getName();
        }

    }
}
