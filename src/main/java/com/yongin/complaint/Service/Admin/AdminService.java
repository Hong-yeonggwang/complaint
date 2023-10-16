package com.yongin.complaint.Service.Admin;

import com.yongin.complaint.DTO.Admin.CategoryUpdateDTO;
import com.yongin.complaint.JPA.Entity.QRcodeCategory;

import java.util.List;

public interface AdminService {

    List<QRcodeCategory> getAllCategory();

    void updateCategory(String name, String placeName, CategoryUpdateDTO updateInfo);

    void addCategory(CategoryUpdateDTO updateInfo);

    void deleteCategory(String name);
}
