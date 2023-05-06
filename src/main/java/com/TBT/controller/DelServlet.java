package com.TBT.controller;

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
 * @create 2023-05-05 19:58
 */
@WebServlet("/del")
public class DelServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String goodNo = req.getParameter("goodNo");
        String shopNo = req.getParameter("shopNo");
        String flag = req.getParameter("flag");
        String token = req.getHeader("token");
        String str = Jwtutils.getTokenName(token);
        String[] strs = str.split("-");

        ShopCarService shopCarService = new ShopCarService();
        shopCarService.del(strs[1],goodNo,shopNo);

        Gson gson = new Gson();
        PrintWriter writer = resp.getWriter();
        writer.print(gson.toJson(1));
    }
}
