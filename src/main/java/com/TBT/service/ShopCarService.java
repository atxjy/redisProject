package com.TBT.service;

import com.TBT.dao.GoodDao;
import com.TBT.dao.OrderDao;
import com.TBT.dao.ShopCarDao;
import com.TBT.pojo.Order;
import com.TBT.pojo.ShopCar;
import com.TBT.utils.JDBCUtil;
import com.TBT.utils.JedisUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xjy
 * @create 2023-05-05 16:49
 */
public class ShopCarService {

    private ShopCarDao shopCarDao = new ShopCarDao();

    public void addGood(String customerNo,String goodNo,String shopNo){

        shopCarDao.addGood(customerNo,goodNo,shopNo);

    }

    public List<ShopCar> getShopCar(String customerNo){

        List<ShopCar> list = shopCarDao.getShopCar(customerNo);
        return list;

    }

    public void updateNum(String customerNo,String goodNo,String shopNo,String flag){
        shopCarDao.updateNum(customerNo,goodNo,shopNo,flag);
    }

    public void del(String customerNo,String goodNo,String shopNo){
        shopCarDao.del(customerNo,goodNo,shopNo);
    }

    public boolean submitOne(String customerNo,String goodNo,String shopNo) {

        GoodDao goodDao = new GoodDao();
        OrderDao orderDao = new OrderDao();
        Connection conn = null;
        boolean flag = true;

        int stock = goodDao.getStockofGood(goodNo, shopNo);
        int num = shopCarDao.getNum(customerNo, goodNo, shopNo);

        String orderNo = System.currentTimeMillis() + "";
        Integer price = goodDao.getPrice(goodNo) * num;
        java.util.Date d = new java.util.Date();
        Date date = new Date(d.getTime());
        Order order = new Order(orderNo,shopNo,customerNo,date,1,price);
        if(stock >= num){
            try {

                conn = JDBCUtil.getConn();
                conn.setAutoCommit(false);
                orderDao.insertOrder(order,conn);
                orderDao.insetOrderDetail(orderNo,goodNo,num,price,conn);
                goodDao.updateGoodStock(goodNo,shopNo,num,conn);
                conn.commit();
                shopCarDao.del(customerNo, goodNo, shopNo);
                return flag;
            } catch (SQLException e) {
                try {
                    flag = false;
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
            } finally {
                JDBCUtil.close(conn,null,null);
            }
        }else {
            return false;
        }
        return flag;
    }

    public int submitChoose(String customerNo,List<String> list) {

        GoodDao goodDao = new GoodDao();
        JedisUtils jedis = new JedisUtils();
        OrderDao orderDao = new OrderDao();
        Connection conn = null;

        List<ShopCar> shopCarList = new ArrayList<>();
//        Set<String> shopNoList = new HashSet<>();

        for(int i = 0;i < list.size();i++){
            String[] str = list.get(i).split("-");
            String goodNo = str[0];
            String shopNo = str[1];

            Integer num = Integer.valueOf(jedis.hget(customerNo,goodNo + "-" + shopNo));
            int stock = goodDao.getStockofGood(goodNo, shopNo);
            if(stock >= num){
                Integer price = goodDao.getPrice(goodNo);
                ShopCar shopCar = new ShopCar(customerNo,goodNo,num,price,shopNo);
                shopCarList.add(shopCar);
            }
        }

        try {
            conn = JDBCUtil.getConn();
            conn.setAutoCommit(false);

            Map<String, List<ShopCar>> shopCarMap = shopCarList.stream().filter(shopCar -> shopCar.getNum() > 0)
             .collect(Collectors.groupingBy(ShopCar::getShopNo));
//        System.out.println(shopNoMap);
            Set<Map.Entry<String, List<ShopCar>>> entries = shopCarMap.entrySet();
            Iterator<Map.Entry<String, List<ShopCar>>> iterator = entries.iterator();
            while (iterator.hasNext()){
                Map.Entry<String, List<ShopCar>> shopCarEntry = iterator.next();
                String shopNo = shopCarEntry.getKey();
                List<ShopCar> shopCarList2 = shopCarEntry.getValue();

                String orderNo = System.currentTimeMillis() + "";
                java.util.Date d = new java.util.Date();
                Date date = new Date(d.getTime());
                //获得订单总价
                int totalPrice = 0;
                for(ShopCar s : shopCarList2){
                    Integer price = goodDao.getPrice(s.getGoodNo());
                    Integer num = Integer.valueOf(jedis.hget(customerNo,s.getGoodNo() + "-" + s.getShopNo()));
                    totalPrice+=(price * num);
                }
                Order order = new Order(orderNo,shopNo,customerNo,date,1,totalPrice);
                orderDao.insertOrder(order,conn);

                for(ShopCar s : shopCarList2){
                    //查询大订单中某个商品的总价
                    Integer num = Integer.valueOf(jedis.hget(customerNo, s.getGoodNo() + "-" + s.getShopNo()));
                    Integer priceOne = goodDao.getPrice(s.getGoodNo());
                    orderDao.insetOrderDetail(orderNo,s.getGoodNo(),num,num * priceOne,conn);
                    goodDao.updateGoodStock(s.getGoodNo(),s.getShopNo(),num,conn);
                    jedis.hdel(customerNo,s.getGoodNo() + "-" + s.getShopNo());
                }
            }
            conn.commit();
            return 1;
        } catch (Exception e) {
            try {
                conn.rollback();
                return 0;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,null,null);
        }
        // 获取一个商店对应多个商品的总金额
//         int totalPrice = shopCarList.stream().mapToInt(shopCar -> shopCar.getNum() * shopCar.getPrice()).sum();
        return 1;
    }

}
