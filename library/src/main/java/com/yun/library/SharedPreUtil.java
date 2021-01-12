package com.yun.library;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * 数据持久化保存
 *
 * @author yun
 * @date 2015-9-18 18:04
 * @Description:
 */
public class SharedPreUtil {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private static SharedPreUtil sharedPreUtils;

    public static synchronized SharedPreUtil getInstance(Context context) {
        if (sharedPreUtils == null) {
            sharedPreUtils = new SharedPreUtil(context);
        }
        return sharedPreUtils;
    }

    public SharedPreUtil(Context context) {
        sp = context.getSharedPreferences("orange_data", Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key) {
        return sp.getString(key, "");
    }

    public void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public int getInt(String key) {
        return sp.getInt(key, -1);
    }

    public void putFloat(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    public float getFloat(String key) {
        return sp.getFloat(key, -1);
    }

    public void putLong(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public long getLong(String key) {
        return sp.getLong(key, -1);
    }

    /**
     * 存储 T 所有(string, int, float, long)数据 key为 T 字段名
     *
     * @param context 当前页面或AppContext.
     * @param t       需要存储的类
     * @param <T>     需要存储的类
     */
    public static <T> void putAllMode(Context context, T t) {
        if (context == null || t == null) {
            Log.e("SharedPreUtils", "设置信息失败，context 或 数据为 null");
            return;
        }
        Class userClass = t.getClass();
        try {
            Field[] fields = userClass.getDeclaredFields();
            for (Field field :
                    fields) {
                field.setAccessible(true);
                String name = t.getClass().getName() + field.getName();
                Object obj = field.get(t);
                if (obj == null)
                    continue;
                String type = field.getType().toString();
                switch (type) {
                    case "class java.lang.String":
                        SharedPreUtil.getInstance(context).putString(name, obj.toString());
                        break;
                    case "int":
                        SharedPreUtil.getInstance(context).putInt(name, (Integer) obj);
                        break;
                    case "float":
                        SharedPreUtil.getInstance(context).putFloat(name, (Float) obj);
                        break;
                    case "long":
                        SharedPreUtil.getInstance(context).putLong(name, (Long) obj);
                        break;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除缓存在SharedPreferences的数据
     *
     * @param context 当前页面或者AppContext.
     * @param t       需要清除的类
     * @param <T>     需要清除的类
     */
    public static <T> void cleanMode(Context context, T t) {
        if (context == null || t == null) {
            Log.e("SharedPreUtils", "清楚信息失败，context 或 数据为 null");
            return;
        }
        Class userClass = t.getClass();
        try {
            Field[] fields = userClass.getDeclaredFields();
            for (Field field :
                    fields) {
                field.setAccessible(true);
                String name = t.getClass().getName() + field.getName();
                Object obj = field.get(t);
                String type = field.getType().toString();
                switch (type) {
                    case "class java.lang.String":
                        SharedPreUtil.getInstance(context).putString(name, obj == null ? "" : obj.toString());
                        break;
                    case "int":
                        SharedPreUtil.getInstance(context).putInt(name, obj == null ? 0 : (Integer) obj);
                        break;
                    case "float":
                        SharedPreUtil.getInstance(context).putFloat(name, obj == null ? 0 : (Float) obj);
                        break;
                    case "long":
                        SharedPreUtil.getInstance(context).putLong(name, obj == null ? 0 : (Long) obj);
                        break;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取缓存SharedPreferences中的数据
     *
     * @param context 当前页面或者AppContext.
     * @param t       需要获取的类
     * @param <T>     需要获取的类
     * @return 获取查询到的数据并返回实体类
     */
    public static <T> T getAllMode(Context context, T t) {
        if (context == null || t == null) {
            Log.e("SharedPreUtils", "获取信息失败");
            return t;
        }
        Class userClass = t.getClass();
        try {
            Field[] fields = userClass.getDeclaredFields();
            for (Field field :
                    fields) {
                field.setAccessible(true);
                String name = t.getClass().getName() + field.getName();
                String type = field.getType().toString();
                switch (type) {
                    case "class java.lang.String":
                        field.set(t, SharedPreUtil.getInstance(context).getString(name));
                        break;
                    case "int":
                        field.set(t, SharedPreUtil.getInstance(context).getInt(name));
                        break;
                    case "float":
                        field.set(t, SharedPreUtil.getInstance(context).getFloat(name));
                        break;
                    case "long":
                        field.set(t, SharedPreUtil.getInstance(context).getLong(name));
                        break;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }

}
