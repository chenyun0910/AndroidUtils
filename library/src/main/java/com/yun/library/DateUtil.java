package com.yun.library;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author yun
 * @date 2018/11/26 13:14
 * @Description:
 */
public class DateUtil {

    /**
     * 获取日期
     *
     * @return
     */
    public static String getFileName() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date(System.currentTimeMillis()));
        return date;// 2012年10月03日 23:41:31
    }

    /**
     * 获取具体时间
     *
     * @return
     */
    public static String getDateTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = format.format(new Date(System.currentTimeMillis()));
        return dateTime;
    }

    /**
     * 判断时间大小
     * 返回ture
     *
     * @param dateStr
     * @param day
     * @return
     */
    public static boolean checkTimeCompare(String dateStr, int day) {
        //格式化时间
        SimpleDateFormat CurrentTime = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Calendar calendar = Calendar.getInstance(); // 当时的日期和时间
            int currDay = calendar.get(Calendar.DAY_OF_MONTH) - day;
            calendar.set(Calendar.DAY_OF_MONTH, currDay);

            Date endTime = CurrentTime.parse(dateStr);
            //判断是否大
            if (endTime.getTime() < calendar.getTime().getTime()) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
