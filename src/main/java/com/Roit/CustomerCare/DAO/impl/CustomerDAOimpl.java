/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Roit.CustomerCare.DAO.impl;


import com.Roit.CustomerCare.DAO.Constant.SQLConstant;
import com.Roit.CustomerCare.DAO.CustomerDAO;
import com.Roit.CustomerCare.DbUtil.DbConnection;
import com.Roit.CustomerCare.Entity.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class CustomerDAOimpl implements CustomerDAO{
    private DbConnection db = new DbConnection();

    @Override
    public int insert(Customer customer) throws ClassNotFoundException, SQLException {
        db.connect();
        PreparedStatement stmt= db.initStatement(SQLConstant.CUSTOMER_INSERT);
        stmt.setString(1, customer.getFirstName());
        stmt.setString(2, customer.getLastName());
        stmt.setString(3, customer.getEmail());
        stmt.setString(4, customer.getContactNo());
        stmt.setBoolean(5, customer.isStatus());
        int result=db.update();
        db.close();
        return result;
        
        }

    @Override
    public List<Customer> getAll() throws ClassNotFoundException, SQLException {
        List<Customer> customerList=new ArrayList<>();
        db.connect();
        db.initStatement(SQLConstant.CUSTOMER_GETALL);
        ResultSet rs=db.query();
        while(rs.next()){
            Customer customer=new Customer();
            customer.setId(rs.getInt("customer_id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setEmail(rs.getString("email"));
            customer.setContactNo("contact_no");
            customer.setStatus(rs.getBoolean("status"));
            customerList.add(customer);
            
        }
        db.close();
        return customerList;
        
        
       }

    @Override
    public Customer getById(int id) throws ClassNotFoundException, SQLException {
        Customer customer=null;
        db.connect();
        PreparedStatement stmt= db.initStatement(SQLConstant.CUSTOMER_GETBYID);
        stmt.setInt(1, id);
        ResultSet rs=db.query();
        if(rs.next()){
            customer=new Customer();
            customer.setId(rs.getInt("customer_id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setEmail(rs.getString("email"));
            customer.setContactNo("contact_no");
            customer.setStatus(rs.getBoolean("status"));
        }
        db.close();
        return customer;
        
        }
    
    
}
