/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Roit.CustomerCare.DAO.Constant;

/**
 *
 * @author User
 */
public class SQLConstant {
    public final static String DEPARTMENT_INSERT="INSERT INTO tbl_departments(department_name,email) Values(?,?)";
    public final static String DEPARTMENT_GETALL="SELECT * FROM tbl_departments";
    public final static String DEPARTMENT_GETBYID="SELECT * FROM tbl_departments WHERE department_id=?";
    
    
    public final static String COMPLAIN_STATUS_INSERT="INSERT INTO tbl_complain_status(status,color) Values(?,?)";
    public final static String COMPLAIN_STATUS_GETALL="SELECT * FROM tbl_complain_status";
    public final static String COMPLAIN_STATUS_GETBYID="SELECT * FROM tbl_complain_status WHERE status_id=?";
    
    
    public final static String CUSTOMER_INSERT="INSERT INTO tbl_customers(first_name,last_name,email,contact_no,status) Values(?,?,?,?,?)";
    public final static String CUSTOMER_GETALL="SELECT * FROM tbl_customers";
    public final static String CUSTOMER_GETBYID="SELECT * FROM tbl_customers WHERE customer_id=?";
    
    
    public final static String COMPLAIN_INSERT="INSERT INTO tbl_complains(customer_id,department_id,subject,description,status_id) Values(?,?,?,?,?)";
    public final static String COMPLAIN_GETALL="SELECT com.*,cust.*,department_id,department_name,dept.email as dept_email,st.status as com_status,st.color FROM tbl_complains com "
                                               +"joins tbl_customers cust on "
                                               +"com.customer_id=cust.customer_id "
                                               +"join tbl_departments dept on " 
                                               +"dept.department_id = com.department_id "
                                               +"join tbl_complain_status stat on " 
                                               +"stat.status_id=com.status_id ";
   
    
     public final static String COMPLAIN_GETBYID="SELECT * FROM tbl_complains com "
                                               +"joins tbl_customers cust on "
                                               +"com.customer_id=cust.customer_id "
                                               +"join tbl_departments dept on " 
                                               +"dept.department_id=com.department_id "
                                               +"join tbl_complain_status stat on " 
                                               +"stat.status_id=com.status_id where cust.customer_id=?";





}
