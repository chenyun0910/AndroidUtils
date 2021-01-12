package com.yun.library;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 * 数字处理工具
 *
 * @author yun
 * @date 2017-8-30 10:30
 * @Description:
 */
public class NumberUtil {

    /**
     * 转成保留两位小数点
     *
     * @param num
     * @return
     */
    public static String getDouble2DecimalFormat(Double num) {
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        return df.format(num);
    }

    /**
     * 判断字符串是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断字符串是否是整数
     */
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是整数
     */
    public static String getNumberToD(String str) {
        String info = "一";
        if ("1".equals(str)) {
            info = "一";
        } else if ("2".equals(str)) {
            info = "二";
        } else if ("3".equals(str)) {
            info = "三";
        } else if ("4".equals(str)) {
            info = "四";
        } else if ("5".equals(str)) {
            info = "五";
        } else if ("6".equals(str)) {
            info = "六";
        } else if ("7".equals(str)) {
            info = "七";
        } else if ("8".equals(str)) {
            info = "八";
        } else if ("9".equals(str)) {
            info = "九";
        } else if ("10".equals(str)) {
            info = "十";
        } else if ("11".equals(str)) {
            info = "十一";
        } else if ("12".equals(str)) {
            info = "十二";
        }
        return info;
    }
}
