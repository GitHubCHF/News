package test.message.com.messagetestdemo.utils;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;



public class MyUtils {

    /**
     * 转化时间（年月日时分）
     *
     * @param time (时间戳)
     * @return
     */
    public static String getDate(long time) {
        Date date = new Date(time * 1000);
        SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return mDateFormat.format(date);
    }

    public static float scale ;
    public static int dip2px(Context context, float dpValue) {
        scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
