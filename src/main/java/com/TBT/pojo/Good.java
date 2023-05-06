package com.TBT.pojo;


/**
 * @author xjy
 * @create 2023-05-05 14:49
 */
public class Good {

    private String goodNo;
    private String goodName;
    private Integer price;

    public Good() {
    }

    public Good(String goodNo, String goodName, Integer price) {
        this.goodNo = goodNo;
        this.goodName = goodName;
        this.price = price;
    }

    public String getGoodNo() {
        return goodNo;
    }

    public void setGoodNo(String goodNo) {
        this.goodNo = goodNo;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
