package com.yongin.complaint.DAO;

import com.yongin.complaint.DTO.Admin.CategoryUpdateDTO;
import com.yongin.complaint.JPA.Entity.QRcodeCategory;

import java.util.List;

public interface AdminDAO {
    List<QRcodeCategory> getAllCategory();

    void updateCategory(String placeName, String name, CategoryUpdateDTO updateInfo);

    void addCategory(CategoryUpdateDTO updateInfo);

    void deleteCategory(String name);
}
