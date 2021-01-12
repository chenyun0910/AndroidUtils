package com.yun.library;

/**
 * description:防止多次点击工具类
 * Created by yun
 * date: 2017/4/18  15:43
 */
public class NoDoubleClickUtils {
    private static long lastClickTime;
    private final static int SPACE_TIME = 600;

    public static void initLastClickTime() {
        lastClickTime = 0;
    }


    public synchronized static boolean isDoubleClick() {
        long currentTime = System.currentTimeMillis();
        boolean isClick2;
        if (currentTime - lastClickTime > SPACE_TIME) {
            isClick2 = false;
        } else {
            isClick2 = true;
        }
        lastClickTime = currentTime;
        return isClick2;
    }
}
