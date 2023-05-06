package com.TBT.controller;

import com.TBT.pojo.Good;
import com.TBT.service.GoodService;
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
 * @create 2023-05-05 14:51
 */
@WebServlet("/goodList")
public class GoodsListServlet extends HttpServlet {

    private GoodService goodService = new GoodService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Good> goods = goodService.getAllGood();

        Gson gson = new Gson();
        PrintWriter writer = resp.getWriter();
        writer.print(gson.toJson(goods));

    }
}
