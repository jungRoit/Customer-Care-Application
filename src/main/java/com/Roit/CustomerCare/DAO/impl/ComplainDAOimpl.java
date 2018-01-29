/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Roit.CustomerCare.DAO.impl;


import com.Roit.CustomerCare.DAO.Constant.SQLConstant;
import com.Roit.CustomerCare.DAO.ComplainDAO;
import com.Roit.CustomerCare.DbUtil.DbConnection;
import com.Roit.CustomerCare.Entity.Complain;
import com.Roit.CustomerCare.Entity.ComplainStatus;
import com.Roit.CustomerCare.Entity.Customer;
import com.Roit.CustomerCare.Entity.Department;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class ComplainDAOimpl implements ComplainDAO{
    private DbConnection db = new DbConnection();

    @Override
    public int insert(Complain complain) throws ClassNotFoundException, SQLException {
        db.connect();
        PreparedStatement stmt= db.initStatement(SQLConstant.COMPLAIN_INSERT);
        stmt.setInt(1, complain.getCustomer().getId());
        stmt.setInt(2, complain.getDepartment().getId());
        stmt.setString(3, complain.getSubject());
        stmt.setString(4, complain.getDescription());
        stmt.setInt(5, complain.getStatus().getId());
        int result=db.update();
        db.close();
        return result;
        
        }

    @Override
    public List<Complain> getAll() throws ClassNotFoundException, SQLException {
        List<Complain> complainList=new ArrayList<>();
        db.connect();
        db.initStatement(SQLConstant.COMPLAIN_GETALL);
        ResultSet rs=db.query();
        while(rs.next()){
            Complain complain=new Complain();
            complain.setId(rs.getInt("complain_id"));
            complain.setSubject(rs.getString("subject"));
            complain.setDescription(rs.getString("description"));
            Customer customer=new Customer(rs.getInt("customer_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("contact_no"), true);
            complain.setCustomer(customer);
            Department dept=new Department(rs.getInt("department_id"), rs.getString("department_name"), rs.getString("dept_email"));
            complain.setDepartment(dept);
            ComplainStatus status=new ComplainStatus(rs.getInt("status_id"), rs.getString("com_status"), rs.getString("color"));
            complain.setStatus(status);
            complainList.add(complain);
            
        }
        db.close();
        return complainList;
        
        
       }

    @Override
    public Complain getById(int id) throws ClassNotFoundException, SQLException {
        Complain complain=null;
        db.connect();
        PreparedStatement stmt= db.initStatement(SQLConstant.COMPLAIN_GETBYID);
        stmt.setInt(1, id);
        ResultSet rs=db.query();
        if(rs.next()){
            complain=new Complain();
            complain.setId(rs.getInt("complain_id"));
            complain.setSubject(rs.getString("subject"));
            complain.setDescription(rs.getString("description"));
            Customer customer=new Customer(rs.getInt("customer_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("contact_no"), true);
            complain.setCustomer(customer);
            Department dept=new Department(rs.getInt("department_id"), rs.getString("department_name"), rs.getString("dept_email"));
            complain.setDepartment(dept);
            ComplainStatus status=new ComplainStatus(rs.getInt("status_id"), rs.getString("com_status"), rs.getString("color"));
            complain.setStatus(status);
            
        }
        db.close();
        return complain;
        
        }
    
    
}
