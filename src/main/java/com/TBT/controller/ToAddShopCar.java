package com.TBT.controller;

import com.TBT.dao.GoodDao;
import com.TBT.dao.ShopCarDao;
import com.TBT.pojo.ShopCar;
import com.TBT.service.ShopCarService;
import com.TBT.utils.Jwtutils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xjy
 * @create 2023-05-05 15:18
 */
@WebServlet("/toShopCar")
public class ToAddShopCar extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String goodNo = req.getParameter("goodNo");
        Integer price = Integer.valueOf(req.getParameter("price"));
        String token = req.getHeader("token");
        String str = Jwtutils.getTokenName(token);
        String[] strs = str.split("-");
        GoodDao goodDao = new GoodDao();
        String shopNo = goodDao.getShopNo(goodNo);
        ShopCarService shopCarService = new ShopCarService();
        shopCarService.addGood(strs[1],goodNo,shopNo);
        Gson gson = new Gson();
        gson.toJson(1);
    }
}
