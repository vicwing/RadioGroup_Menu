package com.sunfusheng.StickyHeaderListView.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author aben
 */
public class UrlUtils {

    private static final String tag = UrlUtils.class.getSimpleName().toString();

    public static int getLongest(Context context) {
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels;
        final int width = displayMetrics.widthPixels;
        int longest = (height > width ? height : width) / 2;
        return longest;
    }

    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }

    /**
     * Get方式请求网络连接时拼接Url
     *
     * @param parameters get参数
     * @return
     */
    public static String spliceUrl(String url, Map<String, String> parameters) {
        try {
            if (parameters != null && parameters.size() > 0) {
                int i = 0;
                StringBuffer sbParameter = new StringBuffer();
                for (String key : parameters.keySet()) {
                    String value = parameters.get(key);
                    if (isValidString(value)) {
                        if (i == 0)
                            sbParameter.append("?");
                        else
                            sbParameter.append("&");

                        value = URLEncoder.encode(value, "utf-8");
                        sbParameter.append(key).append("=").append(value);
                        i++;
                    }
                }
                url += sbParameter.toString();
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return url;
    }


    /**
     * 判断是否为wifi make true current connect service is wifi
     *
     * @param mContext
     * @return
     */
    public static boolean isWifi(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 判断是否为有效字符串(空或空字符串) 不为空对象不为空字符串返回真
     *
     * @return
     */
    public static boolean isValidString(String str) {
        return null != str && !"".equals(str.trim());
    }

    public static String subStr(int length, String value) {
        String testValue = null;
        if (null != value && value.length() > length) {
            testValue = value.substring(0, length) + "..";
        } else {
            testValue = value;
        }
        return testValue;
    }


    /**
     * 获取当前本地版本号
     *
     * @param context
     * @return
     */
    public static String getLocalVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            String versionName = info.versionName;
            return versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getTime(String second, String format) {
        String str = "暂无数据";
        if (str.equals(second) || !isValidString(second)) {
            return str;
        } else {
            try {
                long time = Long.valueOf(second);
                SimpleDateFormat formatter_f = new SimpleDateFormat(format);
                str = formatter_f.format((new Date(time)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }
    }


    /**
     * 字符串转化JSONObject
     *
     * @param str
     * @return
     */
    public static JSONObject strToJSON(String str) {
        if (!isValidString(str)) {
            return null;
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
