package com.yongin.complaint.DAO;

import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.JPA.Entity.QRcode;

import java.util.List;

public interface QRcodeDAO {
    QRcode existQRcode(String serial);

    List<QRcode> getQRcodeList(String memberId);
}
