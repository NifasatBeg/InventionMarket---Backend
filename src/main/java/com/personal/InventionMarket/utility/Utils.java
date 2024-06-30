package com.personal.InventionMarket.utility;

import com.personal.InventionMarket.model.enums.InventionCategoryEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
