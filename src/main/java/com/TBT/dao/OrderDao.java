package com.TBT.dao;

import com.TBT.pojo.Order;
import com.TBT.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author xjy
 * @create 2023-05-05 20:44
 */
public class OrderDao {

    public void insertOrder(Order order,Connection conn){

        String sql = "insert into order1(order_no,shop_no,customer_no,create_date,status,price) values(?,?,?,?,?,?)";
        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement(sql);
            ps.setString(1,order.getOrderNo());
            ps.setString(2,order.getShopNo());
            ps.setString(3,order.getCustomerNo());
            ps.setDate(4,order.getCreateDate());
            ps.setInt(5,order.getStatus());
            ps.setInt(6,order.getPrice());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null,ps,null);
        }

    }

    public void insetOrderDetail(String orderNo,String goodNo,int num,int price,Connection conn){

        String sql = "insert into order_detail(order_no,good_no,num,price) values(?,?,?,?)";

        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement(sql);
            ps.setString(1,orderNo);
            ps.setString(2,goodNo);
            ps.setInt(3,num);
            ps.setInt(4,price);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null,ps,null);
        }

    }

}
