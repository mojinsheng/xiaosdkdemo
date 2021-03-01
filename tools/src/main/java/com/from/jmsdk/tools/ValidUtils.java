package com.from.jmsdk.tools;

import android.text.TextUtils;

import java.util.regex.Pattern;

public abstract class ValidUtils {

    public static boolean isPhone(String phone) {
        if (isBlank(phone)) return false;
        return phone.matches("(1[0-9][0-9]|15[0-9]|18[0-9])\\d{8}");
    }

    public static boolean isIDCard(String idcard) {
        if(idcard==null){
            return false;
        }
        int personalIdLength = idcard.length();
        if (((personalIdLength != 15) && (personalIdLength != 18))) {
            return false;
        }
        String regex = "[1-8]{1}[0-9]{" + (personalIdLength - 2) + "}[0-9X]";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(idcard).matches();
    }

    public static boolean isLetterDigit(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            }
            if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9]+$";
        boolean isRight = isDigit && isLetter && str.matches(regex);
        return isRight;

    }

    public static boolean isMatchLength(String s, Integer min, Integer max) {
        if (isBlank(s)) return false;
        if (min != null && s.length() < min) return false;
        if (max != null && s.length() > max) return false;
        return true;
    }


    public static boolean isBlank(String s) {
        return TextUtils.isEmpty(s.trim());
    }


    public static boolean isNumber(String s) {
        if (isBlank(s)) return false;
        try {
            double d = Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
