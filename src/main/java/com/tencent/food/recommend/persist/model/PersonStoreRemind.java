package com.tencent.food.recommend.persist.model;

public class PersonStoreRemind {
    private Integer id;

    private String openId;

    private Integer storeRemindId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Integer getStoreRemindId() {
        return storeRemindId;
    }

    public void setStoreRemindId(Integer storeRemindId) {
        this.storeRemindId = storeRemindId;
    }
}