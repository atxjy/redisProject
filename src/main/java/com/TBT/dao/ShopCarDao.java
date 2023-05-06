package com.TBT.dao;

import com.TBT.pojo.ShopCar;
import com.TBT.utils.JDBCUtil;
import com.TBT.utils.JedisUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author xjy
 * @create 2023-05-05 16:28
 */
public class ShopCarDao {

    public void addGood(String customerNo,String goodNo,String shopNo){

        JedisUtils jedis = new JedisUtils();
        if(jedis.exists(customerNo)){
            if(jedis.hexists(customerNo,goodNo + "-" + shopNo)){
                jedis.hincrBy(customerNo,goodNo + "-" + shopNo,1);
            }else {
                jedis.hset(customerNo, goodNo + "-" + shopNo,"1");
            }
        }else {
            jedis.hset(customerNo, goodNo + "-" + shopNo,"1");
        }

    }

    public List<ShopCar> getShopCar(String customerNo){

        List<ShopCar> list = new ArrayList<>();
        JedisUtils jedis = new JedisUtils();
        if(jedis.exists(customerNo)){
            Set<String> fields = jedis.hkeys(customerNo);
            Iterator<String> iterator = fields.iterator();
            while (iterator.hasNext()){
                String str = iterator.next();
                String[] strs = str.split("-");
                Integer num = Integer.valueOf(jedis.hget(customerNo,str));

                GoodDao goodDao = new GoodDao();
                Integer price = goodDao.getPrice(strs[0]);

                ShopCar shopCar = new ShopCar(customerNo,strs[0],num,price,strs[1]);
                list.add(shopCar);
            }
        }
        return list;

    }

    public void updateNum(String customerNo,String goodNo,String shopNo,String flag){

        JedisUtils jedis = new JedisUtils();
        if(jedis.exists(customerNo)){
            if(jedis.hexists(customerNo,goodNo + "-" + shopNo)){
                if("incr".equals(flag)){
                    long l = jedis.hincrBy(customerNo, goodNo + "-" + shopNo, 1);
                }else if("decr".equals(flag)) {
                    if(Integer.valueOf(jedis.hget(customerNo,goodNo + "-" + shopNo)) >= 2){
                        jedis.hincrBy(customerNo,goodNo + "-" + shopNo,-1);
                    }else {
                        del(customerNo,goodNo,shopNo);
                    }
                }
            }
        }

    }

    public void del(String customerNo,String goodNo,String shopNo){
        JedisUtils jedis = new JedisUtils();
        if(jedis.exists(customerNo)){
            if(jedis.hexists(customerNo,goodNo + "-" + shopNo)){
                jedis.hdel(customerNo,goodNo + "-" + shopNo);
            }
        }
    }

    public int getNum(String customerNo,String goodNo,String shopNo){
        int num = 0;
        JedisUtils jedis = new JedisUtils();
        if(jedis.exists(customerNo)){
            if(jedis.hexists(customerNo,goodNo + "-" + shopNo)){
                String str = jedis.hget(customerNo, goodNo + "-" + shopNo);
                return Integer.parseInt(str);
            }
        }
        return num;
    }

}
