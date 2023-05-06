package com.TBT.dao;

import com.TBT.pojo.Good;
import com.TBT.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xjy
 * @create 2023-05-05 14:53
 */
public class GoodDao {

    public Integer getPrice(String goodNo){

        String sql = "select price from good where good_no = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        Integer price = 0;
        ResultSet rs = null;

        try {

            conn = JDBCUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1,goodNo);
            rs = ps.executeQuery();

            while (rs.next()){
                price = rs.getInt("price");
            }
            return price;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,rs);
        }
        return price;
    }

    public String getShopNo(String goodNo){

        String sql = "select shop_no from stock where good_no = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        String shopNo = null;
        ResultSet rs = null;

        try {

            conn = JDBCUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1,goodNo);
            rs = ps.executeQuery();

            while (rs.next()){
                shopNo = rs.getString("shop_no");
            }
            return shopNo;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,rs);
        }
        return shopNo;
    }

    public List<Good> getGoods(){

        String sql = "select g.* from good g right join stock s on g.good_no = s.good_no where s.stock != 0";
        Connection conn = null;
        PreparedStatement ps = null;
        List<Good> list = new ArrayList<>();
        ResultSet rs = null;

        try {

            conn = JDBCUtil.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                Good good = new Good();
                String goodName = rs.getString("good_name");
                String goodNo = rs.getString("good_no");
                int price = rs.getInt("price");
                good.setGoodNo(goodNo);
                good.setGoodName(goodName);
                good.setPrice(price);
                list.add(good);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,rs);
        }
        return list;
    }

    public List<Good> getGoodListofShop(String shopNo, Integer pageNo){

        String sql = "SELECT s.shop_no,s.shop_name,g.good_no,g.good_name,g.price,st.stock " +
                "FROM stock st\n" +
                "JOIN good g\n" +
                "ON st.good_no = g.good_no\n" +
                "JOIN shop s\n" +
                "ON st.shop_no = s.shop_no\n" +
                "WHERE st.shop_no = ? AND stock != 0 AND is_online = 1 LIMIT ?,3";

        Connection conn = null;
        PreparedStatement ps = null;
        List<Good> list = new ArrayList<>();
        ResultSet rs = null;

        try {

            conn = JDBCUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1,shopNo);
            ps.setInt(2,pageNo);
            rs = ps.executeQuery();

            while (rs.next()){
                Good good = new Good();
                String goodName = rs.getString("good_name");
                String goodNo = rs.getString("good_no");
                int price = rs.getInt("price");
                good.setGoodNo(goodNo);
                good.setGoodName(goodName);
                good.setPrice(price);
                list.add(good);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,rs);
        }

        return list;
    }

    public Good getGoodById(String goodno){

        String sql = "SELECT s.shop_no,s.shop_name,g.good_no,g.good_name,g.price,st.stock\n" +
                "FROM stock st\n" +
                "JOIN good g\n" +
                "ON st.good_no = g.good_no\n" +
                "JOIN shop s\n" +
                "ON st.shop_no = s.shop_no\n" +
                "WHERE st.good_no = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        Good good = null;
        ResultSet rs = null;

        try {

            conn = JDBCUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1,goodno);
            rs = ps.executeQuery();
            while (rs.next()){
                good = new Good();
                String goodName = rs.getString("good_name");
                String goodNo = rs.getString("good_no");
                int price = rs.getInt("price");
                good.setGoodNo(goodNo);
                good.setGoodName(goodName);
                good.setPrice(price);
            }
            return good;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        return good;
    }

    public int getStockofGood(String goodNo,String shopNo){

        String sql = "SELECT stock\n" +
                "FROM stock s\n" +
                "JOIN good g\n" +
                "ON g.good_no = s.good_no\n" +
                "WHERE s.good_no = ? AND shop_no = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int stock = 0;

        try {

            conn = JDBCUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1,goodNo);
            ps.setString(2,shopNo);
            rs = ps.executeQuery();
            while (rs.next()){
                stock = rs.getInt("stock");
            }
            return stock;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,rs);
        }
        return stock;
    }

    public int getStockofGood(String goodNo){

        String sql = "SELECT stock\n" +
                "FROM stock \n" +
                "WHERE good_no = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int stock = 0;

        try {

            conn = JDBCUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1,goodNo);
            rs = ps.executeQuery();
            while (rs.next()){
                stock = rs.getInt("stock");
            }
            return stock;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,rs);
        }
        return stock;
    }

    public void updateGoodStock(String goodNo,String shopNO,int buyNum,Connection conn){

        String sql = "update stock set stock = stock - ? where good_no = ? and shop_no = ?";
        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement(sql);
            ps.setInt(1,buyNum);
            ps.setString(2,goodNo);
            ps.setString(3,shopNO);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null,ps,null);
        }

    }

    public void updateGoodStockOfManager(String goodNo,String shopNO,int stock){

        String sql = "update stock set stock = ? where good_no = ? and shop_no = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = JDBCUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,stock);
            ps.setString(2,goodNo);
            ps.setString(3,shopNO);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,null);
        }

    }

    public int getCount(String shopNO){

        String sql = "select * from stock where shop_no = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;

        try {

            conn = JDBCUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1,shopNO);
            rs = ps.executeQuery();
            while (rs.next()){
                count++;
            }
            return count;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,rs);
        }
        return count;
    }

    public List<Map<String,Object>> shopManagerQueryGoodsOnline(String shopNo, Integer startIndex){

        String sql = "SELECT s.good_no,g.good_name,g.price,s.is_online,s.stock\n" +
                "FROM stock s\n" +
                "JOIN good g\n" +
                "ON g.good_no = s.good_no\n" +
                "WHERE s.shop_no = ? and is_online = 1 LIMIT ?,3";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String,Object>> goodsList = new ArrayList<>();

        try {

            conn = JDBCUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1,shopNo);
            ps.setInt(2,startIndex);
            rs = ps.executeQuery();
            while (rs.next()){
                Map<String,Object> map = new HashMap<>();
                String goodNo = rs.getString("good_no");
                String goodName = rs.getString("good_name");
                int price = rs.getInt("price");
                int isOnline = rs.getInt("is_online");
                int stock = rs.getInt("stock");
                map.put("goodNo",goodNo);
                map.put("goodName",goodName);
                map.put("price",price);
                map.put("isOnline",isOnline);
                map.put("stock",stock);
                goodsList.add(map);
            }
            return goodsList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,rs);
        }

        return goodsList;
    }
    public List<Map<String,Object>> shopManagerQueryGoodsNoOnline(String shopNo,Integer startIndex){

        String sql = "SELECT s.good_no,g.good_name,g.price,s.is_online,s.stock\n" +
                "FROM stock s\n" +
                "JOIN good g\n" +
                "ON g.good_no = s.good_no\n" +
                "WHERE s.shop_no = ? and is_online = 0 LIMIT ?,3";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String,Object>> goodsList = new ArrayList<>();

        try {

            conn = JDBCUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1,shopNo);
            ps.setInt(2,startIndex);
            rs = ps.executeQuery();
            while (rs.next()){
                Map<String,Object> map = new HashMap<>();
                String goodNo = rs.getString("good_no");
                String goodName = rs.getString("good_name");
                int price = rs.getInt("price");
                int isOnline = rs.getInt("is_online");
                int stock = rs.getInt("stock");
                map.put("goodNo",goodNo);
                map.put("goodName",goodName);
                map.put("price",price);
                map.put("isOnline",isOnline);
                map.put("stock",stock);
                goodsList.add(map);
            }
            return goodsList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,rs);
        }

        return goodsList;
    }

    public int getAllGood(String shopNo){

        String sql = "SELECT *\n" +
                "FROM stock s\n" +
                "JOIN good g\n" +
                "ON g.good_no = s.good_no\n" +
                "WHERE s.shop_no = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;

        try {

            conn = JDBCUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1,shopNo);
            rs = ps.executeQuery();
            while (rs.next()){
                count++;
            }
            return count;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,rs);
        }
        return count;
    }

    public int getAllGoodOnline(String shopNo){

        String sql = "SELECT *\n" +
                "FROM stock s\n" +
                "JOIN good g\n" +
                "ON g.good_no = s.good_no\n" +
                "WHERE s.shop_no = ? and is_online = 1";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;

        try {

            conn = JDBCUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1,shopNo);
            rs = ps.executeQuery();
            while (rs.next()){
                count++;
            }
            return count;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,rs);
        }
        return count;
    }

    public int getAllGoodNoOnline(String shopNo){

        String sql = "SELECT *\n" +
                "FROM stock s\n" +
                "JOIN good g\n" +
                "ON g.good_no = s.good_no\n" +
                "WHERE s.shop_no = ? and is_online = 0";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;

        try {

            conn = JDBCUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1,shopNo);
            rs = ps.executeQuery();
            while (rs.next()){
                count++;
            }
            return count;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,rs);
        }
        return count;
    }

    public void updateOnline(Integer online,String goodNo){

        String sql = "update stock set is_online = ? where good_no = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = JDBCUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,online);
            ps.setString(2,goodNo);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,null);
        }

    }

    public List<Good> getGoodsOfGood(){

        String sql = "SELECT good_no,good_name,price\n" +
                "FROM good g\n" +
                "WHERE g.good_no NOT IN (\n" +
                "\t\t\tSELECT good_no\n" +
                "\t\t\tFROM stock \n" +
                "\t\t\t)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Good> goodList = new ArrayList<>();

        try {

            conn = JDBCUtil.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                String goodNo = rs.getString("good_no");
                String goodName = rs.getString("good_name");
                int price = rs.getInt("price");
                Good good = new Good();
                good.setGoodNo(goodNo);
                good.setGoodName(goodName);
                good.setPrice(price);
                goodList.add(good);
            }
            return goodList;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,rs);
        }
        return goodList;
    }

    public void addGoods(String goodNo,String shopNo,Integer isOnline,Integer stock){

        String sql = "insert into stock(good_no,shop_no,is_online,stock) values(?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = JDBCUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1,goodNo);
            ps.setString(2,shopNo);
            ps.setInt(3,isOnline);
            ps.setInt(4,stock);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,null);
        }

    }

    public void deleteGoodOfShop(String goodNo,String shopNo){

        String sql = "delete from stock where good_no = ? and shop_no = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = JDBCUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1,goodNo);
            ps.setString(2,shopNo);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,null);
        }
    }

    public List<Map<String,Object>> getGoodsDetails(String shopNo){

        String sql = "SELECT s.good_no,good_name,price,is_online,stock\n" +
                "FROM stock s\n" +
                "JOIN good d\n" +
                "ON s.good_no = d.good_no WHERE s.shop_no = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String,Object>> list = new ArrayList<>();

        try {

            conn = JDBCUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1,shopNo);
            rs = ps.executeQuery();
            while (rs.next()){
                String goodNo = rs.getString("good_no");
                String goodName = rs.getString("good_name");
                int price = rs.getInt("price");
                int isOnline = rs.getInt("is_online");
                int stock = rs.getInt("stock");
                Map<String,Object> map = new HashMap<>();
                map.put("goodNo",goodNo);
                map.put("goodName",goodName);
                map.put("price",price);
                map.put("isOnline",isOnline);
                map.put("stock",stock);
                list.add(map);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,rs);
        }
        return list;
    }

}
