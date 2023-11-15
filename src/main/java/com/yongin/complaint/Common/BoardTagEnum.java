package com.yongin.complaint.Common;

import java.util.ArrayList;
import java.util.List;

public enum BoardTagEnum {
    PURCHASE,SERVICE,ERROR;

    static public List<String> getBoardTag(){
        List<String> data = new ArrayList<String>();
        data.add(PURCHASE.toString());
        data.add(SERVICE.toString());
        data.add(ERROR.toString());
        return data;
    }
}
