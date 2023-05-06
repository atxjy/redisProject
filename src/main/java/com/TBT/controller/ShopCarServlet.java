package com.TBT.controller;

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
import java.io.PrintWriter;
import java.util.List;

/**
 * @author xjy
 * @create 2023-05-05 17:31
 */
@WebServlet("/shopCar")
public class ShopCarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String token = req.getHeader("token");
        String str = Jwtutils.getTokenName(token);
        String[] strs = str.split("-");

        ShopCarService shopCarService = new ShopCarService();
        List<ShopCar> list = shopCarService.getShopCar(strs[1]);

        Gson gson = new Gson();
        PrintWriter writer = resp.getWriter();
        writer.print(gson.toJson(list));

    }
}
