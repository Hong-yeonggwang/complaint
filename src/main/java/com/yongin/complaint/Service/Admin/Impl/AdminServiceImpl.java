package com.yongin.complaint.Service.Admin.Impl;

import com.yongin.complaint.DAO.AdminDAO;
import com.yongin.complaint.Service.Admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminDAO adminDAO;

    @Autowired
    public AdminServiceImpl (AdminDAO adminDAO){
        this.adminDAO = adminDAO;
    }
}
