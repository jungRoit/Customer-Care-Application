/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Roit.CustomerCare.DAO.impl;

import com.Roit.CustomerCare.DAO.Constant.SQLConstant;
import com.Roit.CustomerCare.DAO.ComplainStatusDAO;
import com.Roit.CustomerCare.DbUtil.DbConnection;
import com.Roit.CustomerCare.Entity.ComplainStatus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class ComplainStatusDAOimpl implements ComplainStatusDAO{
    private DbConnection db = new DbConnection();

    @Override
    public int insert(ComplainStatus status) throws ClassNotFoundException, SQLException {
        db.connect();
        PreparedStatement stmt= db.initStatement(SQLConstant.COMPLAIN_STATUS_INSERT);
        stmt.setString(1, status.getStatus());
        stmt.setString(2, status.getColor());
        int result=db.update();
        db.close();
        return result;
        
        }

    @Override
    public List<ComplainStatus> getAll() throws ClassNotFoundException, SQLException {
        List<ComplainStatus> statusList=new ArrayList<>();
        db.connect();
        db.initStatement(SQLConstant.COMPLAIN_STATUS_GETALL);
        ResultSet rs=db.query();
        while(rs.next()){
            ComplainStatus status=new ComplainStatus();
            status.setId(rs.getInt("status_id"));
            status.setStatus(rs.getString("status"));
            status.setColor(rs.getString("color"));
            statusList.add(status);
            
        }
        db.close();
        return statusList;
        
        
       }

    @Override
    public ComplainStatus getById(int id) throws ClassNotFoundException, SQLException {
        ComplainStatus status=null;
        db.connect();
        PreparedStatement stmt= db.initStatement(SQLConstant.COMPLAIN_STATUS_GETBYID);
        stmt.setInt(1, id);
        ResultSet rs=db.query();
        if(rs.next()){
            status=new ComplainStatus();
            status.setId(rs.getInt("status_id"));
            status.setStatus(rs.getString("status"));
            status.setColor(rs.getString("color"));
 
        }
        db.close();
        return status;
        
        }
    
    
}
