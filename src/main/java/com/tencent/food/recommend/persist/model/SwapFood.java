package com.tencent.food.recommend.persist.model;

public class SwapFood {
    private Integer id;

    private String swapId;

    private String fromId;

    private String to;

    private Integer toId;

    private Integer quantityFrom;

    private Integer weightFrom;

    private Integer quantityTo;

    private Integer weightTo;

    private Long postTime;

    private String extInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSwapId() {
        return swapId;
    }

    public void setSwapId(String swapId) {
        this.swapId = swapId == null ? null : swapId.trim();
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId == null ? null : fromId.trim();
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to == null ? null : to.trim();
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public Integer getQuantityFrom() {
        return quantityFrom;
    }

    public void setQuantityFrom(Integer quantityFrom) {
        this.quantityFrom = quantityFrom;
    }

    public Integer getWeightFrom() {
        return weightFrom;
    }

    public void setWeightFrom(Integer weightFrom) {
        this.weightFrom = weightFrom;
    }

    public Integer getQuantityTo() {
        return quantityTo;
    }

    public void setQuantityTo(Integer quantityTo) {
        this.quantityTo = quantityTo;
    }

    public Integer getWeightTo() {
        return weightTo;
    }

    public void setWeightTo(Integer weightTo) {
        this.weightTo = weightTo;
    }

    public Long getPostTime() {
        return postTime;
    }

    public void setPostTime(Long postTime) {
        this.postTime = postTime;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo == null ? null : extInfo.trim();
    }
}