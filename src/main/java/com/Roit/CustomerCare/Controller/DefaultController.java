/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Roit.CustomerCare.Controller;

import com.Roit.CustomerCare.DAO.ComplainDAO;
import com.Roit.CustomerCare.DAO.ComplainStatusDAO;
import com.Roit.CustomerCare.DAO.Constant.SystemConstant;
import com.Roit.CustomerCare.DAO.CustomerDAO;
import com.Roit.CustomerCare.DAO.DepartmentDAO;
import com.Roit.CustomerCare.DAO.impl.ComplainDAOimpl;
import com.Roit.CustomerCare.DAO.impl.ComplainStatusDAOimpl;
import com.Roit.CustomerCare.DAO.impl.CustomerDAOimpl;
import com.Roit.CustomerCare.DAO.impl.DepartmentDAOimpl;
import com.Roit.CustomerCare.Entity.Complain;
import com.Roit.CustomerCare.Entity.ComplainStatus;
import com.Roit.CustomerCare.Entity.Customer;
import com.Roit.CustomerCare.Entity.Department;
import com.Roit.CustomerCare.System.Controller;
import com.Roit.CustomerCare.System.Mailer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "default", urlPatterns = {"/"})
public class DefaultController extends Controller {

    private ComplainDAO complainDAO = new ComplainDAOimpl();
    private CustomerDAO customerDAO = new CustomerDAOimpl();
    private DepartmentDAO deptDAO = new DepartmentDAOimpl();
    private ComplainStatusDAO csDAO = new ComplainStatusDAOimpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            request.setAttribute("customers", customerDAO.getAll());
            request.setAttribute("departments", deptDAO.getAll());
            request.setAttribute("statuses", csDAO.getAll());
            request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException se) {
            System.out.println(se.getMessage());

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Complain complain = new Complain();
            Department department=deptDAO.getById(Integer.parseInt(request.getParameter("department_id")));
            
            complain.setSubject(request.getParameter("subject"));
            complain.setDescription(request.getParameter("description"));
            complain.setCustomer(new Customer(Integer.parseInt(request.getParameter("customer_id"))));
            complain.setDepartment(department);
            complain.setStatus(new ComplainStatus(1));
            complainDAO.insert(complain);
            
            Mailer mail=new Mailer();
            mail.setTo(department.getEmail());
            mail.setFrom(SystemConstant.MAIL_FROM);
            mail.setSubject("problem: "+complain.getSubject());
            mail.setBody(complain.getDescription());
            mail.send();
           
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
