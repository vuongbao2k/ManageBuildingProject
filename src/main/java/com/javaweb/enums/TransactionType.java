package com.javaweb.enums;
import java.util.*;

public enum  TransactionType {
    CSKH ("Chăm sóc khách hàng"),
    DDX ("Dẫn đi xem");

    private final String name;

    TransactionType(String name) {
        this.name = name;
    }

    public static Map<String,String> transactionType(){
        Map<String,String> listType = new LinkedHashMap<>();
        for(TransactionType item : TransactionType.values()){
            listType.put(item.toString() , item.name);
        }
        return listType;
    }
}
