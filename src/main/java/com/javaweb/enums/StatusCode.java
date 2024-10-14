package com.javaweb.enums;
import java.util.*;

public enum StatusCode {
    CHUA_XU_LY ("Chưa xử lý"),
    DANG_XU_LY ("Đang xử lý"),
    DA_XU_LY ("Đã xử lý");

    private final String name;
    private StatusCode(String name){this.name = name;}
    public static Map<String,String> statusCode(){
        Map<String,String> listType = new LinkedHashMap<>();
        for(StatusCode item : StatusCode.values()) {
            listType.put(item.toString() , item.name);
        }
        return listType;
    }
}
