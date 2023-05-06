package com.TBT.pojo;

/**
 * @author xjy
 * @create 2023-05-05 16:27
 */
public class ShopCar {

    private String customerNo;
    private String goodNo;
    private Integer num;
    private Integer price;
    private String shopNo;

    public ShopCar() {
    }

    public ShopCar(String customerNo, String goodNo, Integer num, Integer price, String shopNo) {
        this.customerNo = customerNo;
        this.goodNo = goodNo;
        this.num = num;
        this.price = price;
        this.shopNo = shopNo;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getGoodNo() {
        return goodNo;
    }

    public void setGoodNo(String goodNo) {
        this.goodNo = goodNo;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo;
    }

    @Override
    public String toString() {
        return "ShopCar{" +
                "customerNo='" + customerNo + '\'' +
                ", goodNo='" + goodNo + '\'' +
                ", num=" + num +
                ", price=" + price +
                ", shopNo='" + shopNo + '\'' +
                '}';
    }
}
