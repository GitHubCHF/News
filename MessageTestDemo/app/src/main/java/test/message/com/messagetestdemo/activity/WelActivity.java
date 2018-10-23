package test.message.com.messagetestdemo.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import test.message.com.messagetestdemo.R;
import test.message.com.messagetestdemo.base.BaseActivity;
import test.message.com.messagetestdemo.base.MyApp;
import test.message.com.messagetestdemo.utils.GlideUtils;



public class WelActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel);

        initView();

    }

    private void initView() {
        ImageView start_img = (ImageView) findViewById(R.id.start_img);
        GlideUtils.getInstance().loadImgFIT_XY_NoDefate(this, "http://img.zcool.cn/community/01ddfa57d82a080000018c1b071195.jpg@1280w_1l_2o_100sh.jpg", start_img);
        start_img.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 延迟操作，间隔时间后执行run方法里的内容
                readData();
            }
        }, 2000);

    }

    /**
     * 自动登录判断
     */
    private void readData() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(MyApp.SP_NAME, 0);//SP_NAME在MyApp中自己添加常量值
        boolean isLogined = sharedPreferences.getBoolean("isLogined", false);
        boolean isFirst = sharedPreferences.getBoolean("isFirst", false);

        // 是否是第一次安装
        if (!isFirst) {
            // 存储信息，已经不是第一次安装了
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean("isFirst", true);
            edit.commit();
            // 跳转欢迎页面
            Intent intent = new Intent();
            intent.setClass(WelActivity.this, WelComeActivity.class);
            startActivity(intent);
        } else { // 不是第一次安装
            // 判断是否已登录
            if (!isLogined) {
                Intent intent = new Intent();
                intent.setClass(WelActivity.this, LoginActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent();
                intent.setClass(WelActivity.this, TwoActivity.class);
                startActivity(intent);
            }
        }
        finish();
    }

}

