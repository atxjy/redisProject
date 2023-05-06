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
import java.util.ArrayList;
import java.util.List;

/**
 * @author xjy
 * @create 2023-05-05 21:27
 */
@WebServlet("/submitChoose")
public class SubmitChoose extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String token = req.getHeader("token");
        String strCus = Jwtutils.getTokenName(token);
        String[] strs = strCus.split("-");
        String para = req.getParameter("para");
//        System.out.println(para);
        String[] str = para.split("#");
        List<String> list = new ArrayList<>();
        for(int i = 0;i < str.length;i++){
            list.add(str[i]);
        }
        ShopCarService shopCarService = new ShopCarService();
        int result = shopCarService.submitChoose(strs[1], list);
        Gson gson = new Gson();
        PrintWriter writer = resp.getWriter();
        writer.print(gson.toJson(result));
//        System.out.println(list);
    }
}
