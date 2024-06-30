package com.personal.InventionMarket.utility;

public class StringUtil {
    public static Boolean isNotEmpty(String str){
        if(str == null || str.isEmpty()) return false;
        return true;
    }

    public static Boolean isEmpty(String str){
        return !isNotEmpty(str);
    }
}
