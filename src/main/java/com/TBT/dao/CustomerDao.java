package com.TBT.dao;

import com.TBT.pojo.Customer;
import com.TBT.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author xjy
 * @create 2023-05-05 14:38
 */
public class CustomerDao {

    public Customer getCusByName(String name,String password){

        String sql = "select * from customer where name = ? and password = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        Customer customer = null;
        ResultSet rs = null;

        try {

            conn = JDBCUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,password);

            rs = ps.executeQuery();
            while (rs.next()){
                customer = new Customer();
                customer.setCustomerNo(rs.getString("customer_no"));
                customer.setName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setMobile(rs.getString("mobile"));
                customer.setPassword(rs.getString("password"));
            }
            return customer;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,rs);
        }
        return customer;
    }

}
