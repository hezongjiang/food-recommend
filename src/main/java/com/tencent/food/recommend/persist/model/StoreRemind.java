package com.tencent.food.recommend.persist.model;

public class StoreRemind {
    private Integer id;

    private Long createdDate;

    private Long remindDate;

    private String remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getRemindDate() {
        return remindDate;
    }

    public void setRemindDate(Long remindDate) {
        this.remindDate = remindDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}