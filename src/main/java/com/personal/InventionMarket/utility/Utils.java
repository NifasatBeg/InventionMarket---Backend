package com.personal.InventionMarket.utility;

public class Utils {

    private static final String VALID_EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final String VALID_PHONE_REGEX = "^[789]\\d{9}$";

    public static Boolean validateEmail(String email){
        return email.toLowerCase().matches(VALID_EMAIL_REGEX);
    }

    public static Boolean validatePhoneNumber(String phoneNo){
        return phoneNo.matches(VALID_PHONE_REGEX);
    }
}
