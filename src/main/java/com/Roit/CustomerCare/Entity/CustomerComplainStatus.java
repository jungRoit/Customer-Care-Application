/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Roit.CustomerCare.Entity;

import java.util.Date;

/**
 *
 * @author User
 */
public class CustomerComplainStatus {
    private int id;
    private Complain complain;
    private ComplainStatus status;
    private Date statusUpdateDate;
    private String remarks;

    public CustomerComplainStatus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Complain getComplain() {
        return complain;
    }

    public void setComplain(Complain complain) {
        this.complain = complain;
    }

    public ComplainStatus getStatus() {
        return status;
    }

    public void setStatus(ComplainStatus status) {
        this.status = status;
    }

    public Date getStatusUpdateDate() {
        return statusUpdateDate;
    }

    public void setStatusUpdateDate(Date statusUpdateDate) {
        this.statusUpdateDate = statusUpdateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
     
            
                    
                    
    
    
}
