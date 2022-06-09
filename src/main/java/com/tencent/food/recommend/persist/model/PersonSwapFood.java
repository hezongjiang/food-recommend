package com.tencent.food.recommend.persist.model;

public class PersonSwapFood {
    private Integer id;

    private String swapId;

    private String fromOpenid;

    private String toOpenid;

    private Byte state;

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

    public String getFromOpenid() {
        return fromOpenid;
    }

    public void setFromOpenid(String fromOpenid) {
        this.fromOpenid = fromOpenid == null ? null : fromOpenid.trim();
    }

    public String getToOpenid() {
        return toOpenid;
    }

    public void setToOpenid(String toOpenid) {
        this.toOpenid = toOpenid == null ? null : toOpenid.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}