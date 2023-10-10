package com.yongin.complaint.Common;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.List;

public enum MemberRoleEnum {
    ROLE_ADMIN,ROLE_USER,ROLE_OPERATOR;

    static public List<String> getMemberRole(){
        List<String> data = new ArrayList<String>();
        data.add(ROLE_ADMIN.toString());
        data.add(ROLE_USER.toString());
        data.add(ROLE_OPERATOR.toString());
        return data;
    }
}
