package com.TBT.service;

import com.TBT.dao.GoodDao;
import com.TBT.pojo.Good;

import java.util.List;

/**
 * @author xjy
 * @create 2023-05-05 14:55
 */
public class GoodService {

    private GoodDao goodDao = new GoodDao();

    public List<Good> getAllGood(){

        List<Good> goods = goodDao.getGoods();
        return goods;

    }

    public void addShopCar(String customerNo,String goodNo,String price){

        String shopNo = goodDao.getShopNo(goodNo);



    }

}
