/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Roit.CustomerCare.DAO.impl;

import com.Roit.CustomerCare.DAO.Constant.SQLConstant;
import com.Roit.CustomerCare.DAO.DepartmentDAO;
import com.Roit.CustomerCare.DbUtil.DbConnection;
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
public class DepartmentDAOimpl implements DepartmentDAO{
    private DbConnection db = new DbConnection();

    @Override
    public int insert(Department dept) throws ClassNotFoundException, SQLException {
        db.connect();
        PreparedStatement stmt= db.initStatement(SQLConstant.DEPARTMENT_INSERT);
        stmt.setString(1, dept.getName());
        stmt.setString(2, dept.getEmail());
        int result=db.update();
        db.close();
        return result;
        
        }

    @Override
    public List<Department> getAll() throws ClassNotFoundException, SQLException {
        List<Department> deptList=new ArrayList<>();
        db.connect();
        db.initStatement(SQLConstant.DEPARTMENT_GETALL);
        ResultSet rs=db.query();
        while(rs.next()){
            Department dept=new Department();
            dept.setId(rs.getInt("department_id"));
            dept.setName(rs.getString("department_name"));
            dept.setEmail(rs.getString("email"));
            deptList.add(dept);
            
        }
        db.close();
        return deptList;
        
        
       }

    @Override
    public Department getById(int id) throws ClassNotFoundException, SQLException {
        Department dept=null;
        db.connect();
        PreparedStatement stmt= db.initStatement(SQLConstant.DEPARTMENT_GETBYID);
        stmt.setInt(1, id);
        ResultSet rs=db.query();
        if(rs.next()){
            dept=new Department();
            dept.setId(rs.getInt("department_id"));
            dept.setName(rs.getString("department_name"));
            dept.setEmail(rs.getString("email"));
 
        }
        db.close();
        return dept;
        
        }
    
    
}
