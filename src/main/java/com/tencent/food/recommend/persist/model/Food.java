package com.tencent.food.recommend.persist.model;

public class Food {
    private Integer id;

    private String foodName;

    private String foodId;

    private Integer quantity;

    private Integer weight;

    private Long createDate;

    private Long remindDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName == null ? null : foodName.trim();
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getRemindDate() {
        return remindDate;
    }

    public void setRemindDate(Long remindDate) {
        this.remindDate = remindDate;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", foodName='" + foodName + '\'' +
                ", foodId=" + foodId +
                ", quantity=" + quantity +
                ", weight=" + weight +
                ", createDate=" + createDate +
                ", remindDate=" + remindDate +
                '}';
    }
}