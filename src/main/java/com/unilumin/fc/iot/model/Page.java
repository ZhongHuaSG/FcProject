package com.unilumin.fc.iot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable {
    private Integer total;
    private List rows;
    private Integer page = 1;
    private Integer pageSize = 10;
    @JsonIgnore
    private String startDate;
    @JsonIgnore
    private String endDate;
    @JsonIgnore
    private String orderByString;

    public Page() {
    }

    public String getOrderByString() {
        return this.orderByString;
    }

    public void setOrderByString(String orderByString) {
        this.orderByString = orderByString;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List getRows() {
        return this.rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
