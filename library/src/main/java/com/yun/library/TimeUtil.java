package com.yun.library;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 时间
 *
 * @author yun
 * @date 2015-9-18 18:04
 * @Description:
 */
public class TimeUtil {

    /**
     * 获取日期
     *
     * @param strTime
     * @return
     */
    public static int getDateDay(String strTime) {
        try {
            Date dateTime = new SimpleDateFormat("yyyy-MM-dd").parse(strTime);

            Calendar calendarDate = Calendar.getInstance();
            calendarDate.setTime(dateTime);
            return calendarDate.get(Calendar.DATE);//获取日
        } catch (ParseException e) {
            e.getStackTrace();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return 1;
    }


    /**
     * strTime要转换的String类型的时间
     * formatType时间格式
     * strTime的时间格式和formatType的时间格式必须相同
     *
     * @param strTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static long stringToLong(String strTime, String formatType) throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    /**
     * strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
     * HH时mm分ss秒，
     * strTime的时间格式必须要与formatType的时间格式相同
     *
     * @param strTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    /**
     * currentTime要转换的long类型的时间
     * formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
     *
     * @param currentTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    /**
     * @param currentTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static String longToDateFormatType(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return dateToString(date, formatType);
    }

    /**
     * formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
     * data Date类型的时间
     *
     * @param data
     * @param formatType
     * @return
     */
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }


    /**
     * date要转换的date类型的时间
     *
     * @param date
     * @return
     */
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    /**
     * 日期选择
     *
     * @param context
     * @param themeResId
     * @param tv
     */
    public static void showDatePickerDialog(Context context, int themeResId, final TextView tv) {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(context,
                themeResId,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tv.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                },
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    public static String getDataLine() {
        //获取系统时间的10位的时间戳
        long time = System.currentTimeMillis() / 1000;
        String str = String.valueOf(time);
        return str;
    }

    public static String getZjDateFormat() {
        SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmss");
        return df.format(new Date());
    }


    /**
     * 获取推流Url base+时间后6位
     *
     * @param baseUrl
     * @return
     */
    public static String getPushVideoUrl(String baseUrl) {
        String url = baseUrl
                + String.valueOf((int) (System
                .currentTimeMillis() % 1000000));
        return url;
    }

    /**
     * 时间后6位
     *
     * @return
     */
    public static String getRandomString() {
        return String.valueOf((int) (System
                .currentTimeMillis() % 1000000));
    }

    /**
     * 返回当前的时间
     *
     * @return
     */
    public static String getDateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param startTimeStr 开始时间
     * @param endTimeStr   结束时间
     * @return
     */
    public static boolean isEffectiveDate(String startTimeStr, String endTimeStr) {
        try {
            Date startTime = new SimpleDateFormat("HH:mm:ss").parse(startTimeStr);
            Date endTime = new SimpleDateFormat("HH:mm:ss").parse(endTimeStr);
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);
            Date newTime = new SimpleDateFormat("HH:mm:ss").parse(hour + ":" + minute + ":" + second);
            if (newTime.getTime() == startTime.getTime()
                    || newTime.getTime() == endTime.getTime()) {
                return true;
            }

            Calendar date = Calendar.getInstance();
            date.setTime(newTime);

            Calendar begin = Calendar.getInstance();
            begin.setTime(startTime);

            Calendar end = Calendar.getInstance();
            end.setTime(endTime);

            if (date.after(begin) && date.before(end)) {
                return true;
            }
        } catch (ParseException e) {
            e.getStackTrace();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return false;
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param startTimeStr 开始时间
     * @param endTimeStr   结束时间
     * @return
     */
    public static boolean isEffectiveDateTime(String startTimeStr, String endTimeStr) {
        try {
            Date startTime = new SimpleDateFormat("yy-MM-dd HH:mm:ss").parse(startTimeStr);
            Date endTime = new SimpleDateFormat("yy-MM-dd HH:mm:ss").parse(endTimeStr);
            Date newTime = new SimpleDateFormat("yy-MM-dd HH:mm:ss").parse(DateUtil.getDateTime());
            if (newTime.getTime() == startTime.getTime()
                    || newTime.getTime() == endTime.getTime()) {
                return true;
            }

            Calendar date = Calendar.getInstance();
            date.setTime(newTime);

            Calendar begin = Calendar.getInstance();
            begin.setTime(startTime);

            Calendar end = Calendar.getInstance();
            end.setTime(endTime);

            if (date.after(begin) && date.before(end)) {
                return true;
            }
        } catch (ParseException e) {
            e.getStackTrace();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return false;
    }

}
