package com.yjh.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    //日期转字符串
    public static String dateToString(Date date, String patt){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(patt);
        String format = simpleDateFormat.format(date);
        return format;
    }

    //字符串转日期
    public static Date stringToDate(String str,String patt){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(patt);
        try {
            Date parse = simpleDateFormat.parse(str);
            return parse;
        } catch (ParseException e) {
            throw new RuntimeException("日期格式转换异常"+e);
        }
    }
}
