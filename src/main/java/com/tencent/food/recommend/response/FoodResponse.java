package com.tencent.food.recommend.response;

import com.tencent.food.recommend.persist.model.Food;

import java.util.List;

/**
 * @Author: gyt
 * @Date: 2022/5/18 9:58
 * @Description:
 */
public class FoodResponse {
    private Integer page;
    private Integer pageSize;
    private Integer total;
    private Integer pages;
    private List<Food> list;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<Food> getList() {
        return list;
    }

    public void setList(List<Food> list) {
        this.list = list;
    }
}
