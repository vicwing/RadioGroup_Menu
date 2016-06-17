package com.example.cdj.myapplication.utils.device;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.orhanobut.logger.Logger;

/**
 * 获取设备的状态如 id imei号sim卡number
 * Created by vic on 2016/6/17.
 */
public class DeviceStatusUtils {

    public static  String getImieStatus(Context context){
        TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        String IMEI_Id = tm.getDeviceId();
        Logger.d("imie id  "+ IMEI_Id);
        return IMEI_Id;
    }

    /**
     * 在设备首次启动时，系统会随机生成一个64位的数字，并把这个数字以16进制字符串的形式保存下来，
     * 这个16进制的字符串就是ANDROID_ID，当设备被wipe后该值会被重置。可以通过下面的方法获取：
     *
     * ANDROID_ID可以作为设备标识，但需要注意：
     * 厂商定制系统的Bug：不同的设备可能会产生相同的ANDROID_ID：9774d56d682e549c。
     * 厂商定制系统的Bug：有些设备返回的值为null。
     * 设备差异：对于CDMA设备，ANDROID_ID和TelephonyManager.getDeviceId() 返回相同的值。
     * @param context
     * @return
     */
    public static String getAndroidId(Context context){
        String androidId = Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        Logger.e("ANDROID_ID", androidId);
        return androidId;
    }

    /**
     * 装有SIM卡的设备，可以通过下面的方法获取到Sim Serial Number：
     *  注意：对于CDMA设备，返回的是一个空值！
     * @return
     */
    public static String getSimNumber(Context context){
        TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        String simSerialNumber = tm.getSimSerialNumber();
        Logger.d("simSerialNumber  "+simSerialNumber);
        return simSerialNumber;
    }

}
