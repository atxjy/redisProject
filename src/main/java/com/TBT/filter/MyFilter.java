package com.TBT.filter;

import com.TBT.utils.Jwtutils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xjy
 * @create 2023-05-04 17:33
 */
@WebFilter("/*")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();

        String uri = req.getRequestURI();
        if(uri.endsWith(".html") || "/myweb/login".equals(uri) || "/myweb/".equals(uri)){
            resp.setContentType("text/html;charset=utf-8");
            filterChain.doFilter(req,resp);
            return;
        }

        String token = req.getHeader("token");
        if (token == null){
            resp.setContentType("application/json;charset=utf-8");
            writer.write(0);
            writer.flush();
            writer.close();
            return;
        }

        if(Jwtutils.verifyToken(token)){
            resp.setContentType("application/json;charset=utf-8");
            filterChain.doFilter(req,resp);
            return;
        }else {
            resp.setContentType("application/json;charset=utf-8");
            writer.write(0);
            writer.flush();
            writer.close();
        }
    }

    @Override
    public void destroy() {

    }
}
