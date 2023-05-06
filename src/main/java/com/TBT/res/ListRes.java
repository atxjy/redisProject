package com.TBT.res;

import com.TBT.pojo.Emp;

import java.util.List;

/**
 * @author xjy
 * @create 2023-05-04 17:45
 */
public class ListRes {

    private Integer code;
    private List<Emp> list;

    public ListRes() {
    }

    public ListRes(Integer code, List<Emp> list) {
        this.code = code;
        this.list = list;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<Emp> getList() {
        return list;
    }

    public void setList(List<Emp> list) {
        this.list = list;
    }
}
