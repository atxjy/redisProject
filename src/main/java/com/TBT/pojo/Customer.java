package com.TBT.pojo;

/**
 * @author xjy
 * @create 2023-05-05 14:37
 */
public class Customer {

    private String customerNo;
    private String name;
    private String address;
    private String mobile;
    private String password;

    public Customer() {
    }

    public Customer(String customerNo, String name, String address, String mobile, String password) {
        this.customerNo = customerNo;
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.password = password;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
