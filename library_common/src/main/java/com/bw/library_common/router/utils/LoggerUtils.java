package com.bw.library_common.router.utils;

import android.util.Log;

import com.blankj.utilcode.util.SPUtils;

/**
 * log工具类
 */
public class LoggerUtils {
    static String className;//类名
    static String methodName;//方法名
    static int lineNumber;//行数

    //log格式
    private static String createLog(String log){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("=======");
        stringBuffer.append(methodName);
        stringBuffer.append("(").append(className).append(":").append(lineNumber).append(")================:");
        stringBuffer.append(log);
        return stringBuffer.toString();
    }



    /**
     * 获取文件名、方法名、行数
     */
    private static void getMethodNames(StackTraceElement[] traceElements){
        className = traceElements[1].getFileName();
        methodName = traceElements[1].getMethodName();
        lineNumber = traceElements[1].getLineNumber();
    }

    public static void e(String message){
        getMethodNames(new Throwable().getStackTrace());
        Log.e(className, createLog(message));
    }

    public static void i(String message){
        getMethodNames(new Throwable().getStackTrace());
        Log.i(className, createLog(message));
    }

    public static void d(String message){
        getMethodNames(new Throwable().getStackTrace());
        Log.d(className, createLog(message));
    }

    public static void v(String message){
        getMethodNames(new Throwable().getStackTrace());
        Log.v(className, createLog(message));
    }

    public static void w(String message){
        getMethodNames(new Throwable().getStackTrace());
        Log.w(className, createLog(message));
    }

}
