package com.TBT.controller;

import com.TBT.dao.ShopCarDao;
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

/**
 * @author xjy
 * @create 2023-05-05 20:29
 */
@WebServlet("/submitOne")
public class SubmitOne extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String goodNo = req.getParameter("goodNo");
        String shopNo = req.getParameter("shopNo");
        String token = req.getHeader("token");
        String str = Jwtutils.getTokenName(token);
        String[] strs = str.split("-");

        ShopCarService shopCarService = new ShopCarService();
        boolean result = shopCarService.submitOne(strs[1], goodNo, shopNo);
        Gson gson = new Gson();
        PrintWriter writer = resp.getWriter();
        if(result){
            writer.print(gson.toJson(1));
        }else {
            writer.print(gson.toJson(0));
        }

    }
}
