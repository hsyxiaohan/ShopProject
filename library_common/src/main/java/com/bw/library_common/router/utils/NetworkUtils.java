package com.bw.library_common.router.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.util.ArrayList;
import java.util.List;

/**
 * ─────────────────────────────────────────────────────────────────────────
 * ─████████──████████─██████████████─██████──────────██████─██████████████─
 * ─██░░░░██──██░░░░██─██░░░░░░░░░░██─██░░██████████──██░░██─██░░░░░░░░░░██─
 * ─████░░██──██░░████─██░░██████░░██─██░░░░░░░░░░██──██░░██─██░░██████████─
 * ───██░░░░██░░░░██───██░░██──██░░██─██░░██████░░██──██░░██─██░░██─────────
 * ───████░░░░░░████───██░░██████░░██─██░░██──██░░██──██░░██─██░░██─────────
 * ─────████░░████─────██░░░░░░░░░░██─██░░██──██░░██──██░░██─██░░██──██████─
 * ───────██░░██───────██░░██████░░██─██░░██──██░░██──██░░██─██░░██──██░░██─
 * ───────██░░██───────██░░██──██░░██─██░░██──██░░██████░░██─██░░██──██░░██─
 * ───────██░░██───────██░░██──██░░██─██░░██──██░░░░░░░░░░██─██░░██████░░██─
 * ───────██░░██───────██░░██──██░░██─██░░██──██████████░░██─██░░░░░░░░░░██─
 * ───────██████───────██████──██████─██████──────────██████─██████████████─
 * ─────────────────────────────────────────────────────────────────────────
 **/
public class NetworkUtils {


    private NetworkUtils(){
        throw new UnsupportedOperationException("u can't instance me...");
    }

    public static final int NETWORK_WIFI = 1; //WiFi连接
    public static final int NETWORK_4G = 4;    // "4G"连接
    public static final int NETWORK_3G = 3;    // "3G" 连接
    public static final int NETWORK_2G = 2;    // "2G" 连接
    public static final int NETWORK_UNKNOWN = 5;    // 未知连接
    public static final int NETWORK_NO = -1;   // 没网

    private static final int NETWORK_TYPE_GSM = 16;//GSM网络类型
    private static final int NETWORK_TYPE_TD_SCDMA = 17;//TD_SCDMA网络类型
    private static final int NETWORK_TYPE_IWLAN = 18;//工业无线局域网

    public static List<String>  typeList = new ArrayList<>();
    static {
        typeList.add("打开网络设置界面");
        typeList.add("获取活动网络信息");
        typeList.add("判断网络是否可用");
        typeList.add("判断网络是否是4G");
        typeList.add("判断wifi是否连接状态");
        typeList.add("获取移动网络运营商名称");
        typeList.add("获取当前的网络类型");
        typeList.add("获取当前的网络类型(WIFI,2G,3G,4G)");

    }

    /**
     * 打开网络设置界面
     */
    public static void openWirelessSettings(Context context){
        if (Build.VERSION.SDK_INT > 10){
            context.startActivity(new Intent(Settings.ACTION_SETTINGS));//进入系统设置页面
        }else {
            context.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));//进入网络设置页面
        }
    }

    /**
     * 获取活动网络信息
     */
    public static NetworkInfo getActiveNetworkInfo(Context context){
        ConnectivityManager systemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return systemService.getActiveNetworkInfo();
    }

    /**
     * 判断网络是否可用
     */
    public static boolean isConnected(Context context){
        NetworkInfo inf = getActiveNetworkInfo(context);
        return inf != null && inf.isConnected();
    }

    /**
     * 判断网络是否4G
     *
     */
    public static boolean is4G(Context context){
        NetworkInfo info = getActiveNetworkInfo(context);
        return info != null && info.isAvailable() && info.getSubtype() == TelephonyManager.NETWORK_TYPE_LTE;
    }

    /**
     * 判断wifi是否连接状态
     */
    public static boolean isWifiConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager != null && connectivityManager.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }


    /**
     * 获取移动网络运营商名称
     */
    public static String getNetworkOperatorName(Context context){
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager != null ? telephonyManager.getNetworkOperatorName() : null;
    }


    /**
     * 获取移动终端类型
     * 0：手机制式未知
     * 1：手机制式为GSM，移动和联通
     * 2：手机制式为CDMA，电信
     */
    public static int getPhoneType(Context context){
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager != null ? telephonyManager.getPhoneType() : -1;
    }

    /**
     * 获取当前的网络类型
     * 1：wifi
     * 2:4g
     * 3:3g
     * 4:2g
     * 5：网络类型未知
     * 6：无网络连接
     */
    public static int getNetworkType(Context context){
        int netType = NETWORK_NO;
        NetworkInfo info = getActiveNetworkInfo(context);
        if (info != null && info.isAvailable()){
            if (info.getType() == ConnectivityManager.TYPE_WIFI){
                netType = NETWORK_WIFI;
            }else if (info.getType() == ConnectivityManager.TYPE_MOBILE){
                switch (info.getSubtype()){
                    case NETWORK_TYPE_GSM:
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                        netType = NETWORK_2G;
                        break;
                    case NETWORK_TYPE_TD_SCDMA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                        netType = NETWORK_3G;
                        break;
                    case NETWORK_TYPE_IWLAN:
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        netType = NETWORK_4G;
                    default:
                        String subtypeName = info.getSubtypeName();
                        if (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("cdma2000")){
                            netType = NETWORK_3G;
                        }else {
                            netType = NETWORK_UNKNOWN;
                        }
                        break;
                }
            } else {
                netType = NETWORK_UNKNOWN;
            }
        }
        return netType;
    }

    /**
     * 获取当前的网络类型
     */
    public static String getNetWorkTypeName(Context context){
        switch (getNetworkType(context)){
            case NETWORK_WIFI:
                return "NETWORK_WIFI";
            case NETWORK_4G:
                return "NETWORK_4G";
            case NETWORK_3G:
                return "NETWORK_3G";
            case NETWORK_2G:
                return "NETWORK_2G";
            case NETWORK_NO:
                return "NETWORK_NO";
            default:
                return "NETWORK_UNKNOWN";
        }
    }








}
