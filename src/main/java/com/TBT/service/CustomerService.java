package com.TBT.service;

import com.TBT.dao.CustomerDao;
import com.TBT.pojo.Customer;

/**
 * @author xjy
 * @create 2023-05-05 14:41
 */
public class CustomerService {

    private CustomerDao customerDao = new CustomerDao();

    public Customer getCusByName(String name,String password){

        Customer cus = customerDao.getCusByName(name, password);
        return cus;

    }

}
