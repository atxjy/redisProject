package com.TBT.controller;

import com.TBT.service.CustomerService;
import com.TBT.pojo.Customer;
import com.TBT.res.LoginRes;
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
 * @create 2023-05-04 17:08
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String password = req.getParameter("password");

//        EmpDao empDao = new EmpDao();
//        Emp emp = empDao.getEmp(name, password);
        LoginRes res = new LoginRes();
//        if(emp != null){
//            String token = Jwtutils.jwtToken(name);
//            res.setCode(1);
//            res.setToken(token);
//        }else {
//            res.setCode(0);
//        }
//
//        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
////        resp.setContentType("application/json");
////        resp.setCharacterEncoding("utf-8");
//        PrintWriter writer = resp.getWriter();
//        writer.print(gson.toJson(res));

        CustomerService customerService = new CustomerService();
        Customer cus = customerService.getCusByName(name, password);

        if(cus != null){
            String token = Jwtutils.jwtToken(name,cus.getCustomerNo());
            res.setCode(1);
            res.setToken(token);
        }else {
            res.setCode(0);
        }
        Gson gson = new Gson();
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        writer.print(gson.toJson(res));
    }
}
