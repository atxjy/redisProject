package com.TBT.pojo;

import java.sql.Date;

/**
 * @author xjy
 * @create 2023-05-05 20:43
 */
public class Order {

    private String orderNo;
    private String shopNo;
    private String customerNo;
    private Date createDate;
    private Integer status;
    private Integer price;

    public Order() {
    }

    public Order(String orderNo, String shopNo, String customerNo, Date createDate, Integer status, Integer price) {
        this.orderNo = orderNo;
        this.shopNo = shopNo;
        this.customerNo = customerNo;
        this.createDate = createDate;
        this.status = status;
        this.price = price;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
