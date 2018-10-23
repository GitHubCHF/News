package test.message.com.messagetestdemo.base;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.telephony.TelephonyManager;

import org.xutils.x;

import java.lang.reflect.Method;
import java.util.List;

/**
 * application类
 */
public class MyApp extends Application {
    /**
     * Tag
     */
    public static final String TAG = "MyApp";

    /**
     * application
     */
    public static MyApp application;

    /**
     * application单利
     */
    public static MyApp getInstance() {
        return application;
    }

    /**
     * sharedPrefres 常量值
     */
    public static final String SP_NAME = "SP_NAME";

    // 首页新闻+banner数据接口
    public static final String NEWSBANNERURL = "http://api.news.m.zhixiaoren.com/?m=news201708&a=category_news_list&p=1&num=20";
    //新闻导航栏
    public static final String NEWSNAVURL = "http://api.news.m.zhixiaoren.com/?m=news2&a=category&include_zxh=1";


    /**
     * 版本号
     */
    public String vercode;
    /**
     * 渠道号
     */
    public String source;
    /**
     * 设备id-device_id
     */
    public String deviceId;

    // 首页数据接口
    public static final String FIRSTURL = "http://dingyue.zhixiaoren.com/mobile/interface/recommend_read/p/1&num=20";

    /**
     * oncreate
     */
    public void onCreate() {
        super.onCreate();
        getAppInfo();//获取app信息以及设备信息
        application = this;

        x.Ext.init(this);
//        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.

    }


    //获取NavigationBar的高度
    public static int getNavigationBarHeight(Context context) {
        int navigationBarHeight = 0;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("navigation_bar_height", "dimen", "android");
        if (id > 0 && checkDeviceHasNavigationBar(context)) {
            navigationBarHeight = rs.getDimensionPixelSize(id);
        }
        return navigationBarHeight;
    }

    /**
     * 获取所有进程
     */
    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps != null) {
            for (RunningAppProcessInfo procInfo : runningApps) {
                if (procInfo.pid == pid) {
                    return procInfo.processName;
                }
            }
        }
        return null;
    }

    /**
     * 获取app的信息
     * 获取设备信息
     */
    private void getAppInfo() {
        try {
            PackageManager packageManager = getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
            vercode = packInfo.versionCode + "";
//            ApplicationInfo appInfo = packageManager.getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
//            source = appInfo.metaData.getString("UMENG_CHANNEL");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        try {
            TelephonyManager tm = (TelephonyManager) this.getSystemService(this.TELEPHONY_SERVICE);
            deviceId = tm.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取是否存在NavigationBar
     **/
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        if (Build.VERSION.SDK_INT <= 14) {
            hasNavigationBar = false;
        }
        return hasNavigationBar;
    }
}
