package com.example.cdj.myapplication.base;

/**
 * Created by vic on 2016/6/17.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.example.cdj.myapplication.constant.GloabalConstant;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * UncaughtException处理类,当程序发生Uncaught异常的时候
 * @author user 注意修改文件的路径和文件名，在Manifest中添加文件读写权限； *
 */
public class CrashHandlerNew implements Thread.UncaughtExceptionHandler {

    public static final int TAG_ERROR_CODE = 10;

    /**
     * 错误日志在cachedir下面的crash文件夹下面
     */
    public static final String Error_DIR_NAME = "crash";

    /**
     * 通过该字段，进行判断，程序是否发生过crash ,使用getDefaultSharedPreferences
     */
    public static final String TAG_OCCURRED_ERROR = "crashed";

    public static final String SP_CRASH_TAG = "CrashHandlerNew";



    // 错误日志文件夹的位置
    private String mCrashLogDirPath = "";
    public static final String TAG = CrashHandlerNew.class.getSimpleName();
    // 系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    // CrashHandler实例
    private static CrashHandlerNew INSTANCE = new CrashHandlerNew();
    private Context mContext;
    // 用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();
    // 用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss",
            Locale.CHINA);

    private CrashHandlerNew() {
    }

    public static CrashHandlerNew getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        mContext = context;
        mCrashLogDirPath = context.getExternalCacheDir() + File.separator + Error_DIR_NAME + File.separator;
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理 如果导入项目@Override报错，请修改project编译的jdk版本到1.5以上
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
//        ex.printStackTrace();
//        if (GloabalConstant.isDebug){
//            mDefaultHandler.uncaughtException(thread, ex);
//        }
        Logger.d(" 11111111111111111111111111111111111111111111  ");
        if (GloabalConstant.isDebug ||(!handleException(ex) && mDefaultHandler != null)) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            Logger.d("mDefaultHandler . uncaughtException              22222222222222222222  ");
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            // 退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }

    }

    /**
     * 自定义错误处理,收集错误信息
     * * * @param ex *
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        // 收集设备参数信息
        collectDeviceInfo(mContext);
        saveCrashInfo2File(ex);
        // 向配置文件中写入标识位
        flagCrash();
        return true;
    }

    public void flagCrash() {
//        SharedPreferences.Editor editor = PreferenceManager .getDefaultSharedPreferences(mContext).edit();
        SharedPreferences.Editor editor = mContext.getSharedPreferences(SP_CRASH_TAG, Context.MODE_PRIVATE).edit();
        editor.putBoolean(TAG_OCCURRED_ERROR, true);
        editor.commit();
    }

    /**
     * 收集设备参数信息 * *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
//            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null"
                        : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }

    /**
     * 保存错误信息到文件中 * *
     * @param ex *
     * @return Boolean 判断文件保存到本地是否成功
     */
    private Boolean saveCrashInfo2File(Throwable ex) {
        Boolean saveFlag = false;
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        // TODO
//        System.err.println("*****下面打印错误信息*****");
//        System.err.println(result);
        sb.append(result);
        try {
            long timestamp = System.currentTimeMillis();
            String time = formatter.format(new Date());
            String fileName = "crash-" + time + "-" + timestamp + ".log";
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {

                File dir = new File(mCrashLogDirPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(mCrashLogDirPath
                        + fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
            }
            saveFlag = true;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }
        return saveFlag;
    }
}