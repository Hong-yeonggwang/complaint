package com.yongin.complaint.DAO;

import com.yongin.complaint.JPA.Entity.QRcode;

public interface QRcodeDAO {
    QRcode existQRcode(String serial);
}
