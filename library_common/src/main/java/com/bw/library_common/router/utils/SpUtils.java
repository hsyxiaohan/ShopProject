package com.bw.library_common.router.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.blankj.utilcode.util.SPUtils;

/**
 *
 */
public class SpUtils {

    private static SpUtils spUtils;
    static SharedPreferences.Editor edit;
    static SharedPreferences sharedPreferences;

    //单例封装
    public static SpUtils getInstance(String name, int mode, Context context) {
        if (spUtils == null){
            synchronized (SpUtils.class){
                if (spUtils == null){
                    spUtils = new SpUtils();
                    getSp(name, mode, context);
                }
            }
        }
        return spUtils;
    }


    private static SharedPreferences.Editor getSp(String name, int mode, Context context){
        sharedPreferences = context.getSharedPreferences(name, mode);
        edit = sharedPreferences.edit();
        return edit;
    }

    //存值
    public void put(String key,Object value){
        //获取类型
        String type = value.getClass().getSimpleName();
        if ("Integer".equals(type)){
            edit.putInt(key,(Integer)value);
        }else if ("Boolean".equals(type)){
            edit.putBoolean(key,(Boolean)value);
        }else if ("Float".equals(type)){
            edit.putFloat(key,(Float)value);
        }else if ("Long".equals(type)){
            edit.putLong(key,(Long)value);
        }else if ("String".equals(type)){
            edit.putString(key,(String) value);
        }
        edit.apply();
    }

    //取值
    public Object get(String key,Object defValue){
        String type = defValue.getClass().getSimpleName();
        if("Integer".equals(type)){
            return sharedPreferences.getInt(key,(Integer)defValue);
        }else if ("Boolean".equals(type)){
            return sharedPreferences.getBoolean(key,(Boolean)defValue);
        }else if ("Float".equals(type)){
            return sharedPreferences.getFloat(key,(Float)defValue);
        }else if ("Long".equals(type)){
            return sharedPreferences.getLong(key,(Long)defValue);
        }else if ("String".equals(type)){
            return sharedPreferences.getString(key,(String) defValue);
        }
        return null;
    }


}
