package com.yun.library;

import org.json.JSONObject;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 字符串
 *
 * @author yun
 * @date 2015-9-18 18:04
 * @Description:
 */

public class StringUtils {

    public static boolean isNullOrEmpty(String string) {
        if (string == null || string.trim().length() == 0 || "null".equals(string.trim())) {
            return true;
        }
        return false;
    }


    /**
     * map 转String json
     *
     * @param map
     * @return
     */
    public static String mapToJsonStr(Map map) {
        JSONObject json = new JSONObject(map);
        return json.toString();
    }

    public static boolean isChinaPhoneLegal(String str)
            throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(166)|(17[0-8])|(18[0-9])|(19[8-9])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }


    public static String strFormatTojson(String str) {
        if (!StringUtils.isNullOrEmpty(str)) {
            int start = str.indexOf("{");
            int end = str.indexOf("}") + 1;
            return str.substring(start, end);
        } else {
            return "";
        }

    }

}
