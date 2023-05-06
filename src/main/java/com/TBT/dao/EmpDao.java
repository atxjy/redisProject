package com.TBT.dao;

import com.TBT.pojo.Emp;
import com.TBT.utils.JDBCUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xjy
 * @create 2023-05-04 10:40
 */
public class EmpDao {

    public Emp getEmp(String name,Integer password){

        String sql = "select * from emp where ename = ? and empno = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Emp emp = null;

        try {

            conn = JDBCUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setInt(2,password);
            rs = ps.executeQuery();
            while (rs.next()){
                String job = rs.getString("job");
                int mgr = rs.getInt("mgr");
                Date hiredate = rs.getDate("hiredate");
                BigDecimal sal = rs.getBigDecimal("sal");
                BigDecimal comm = rs.getBigDecimal("COMM");
                int deptno = rs.getInt("deptno");
                emp = new Emp(password,name,job,mgr,hiredate,sal,comm,deptno);
            }
            return emp;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,rs);
        }
        return emp;
    }

    public List<Emp> getAllEmp(){

        String sql = "select * from emp";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Emp> list = new ArrayList<>();

        try {

            conn = JDBCUtil.getConn();
            ps= conn.prepareStatement(sql);


            rs = ps.executeQuery();

            while (rs.next()){

                int empno = rs.getInt("empno");
                String ename = rs.getString("ename");
                String job = rs.getString("job");
                Integer mgr = rs.getInt("mgr");
                Date hiredate = rs.getDate("hiredate");
                BigDecimal sal = rs.getBigDecimal("sal");
                BigDecimal comm = rs.getBigDecimal("COMM");
                int deptno = rs.getInt("deptno");
                Emp emp = new Emp(empno,ename,job,mgr,hiredate,sal,comm,deptno);
                list.add(emp);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,rs);
        }
        return list;

    }

}
