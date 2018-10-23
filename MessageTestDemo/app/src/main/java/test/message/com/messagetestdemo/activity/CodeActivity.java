package test.message.com.messagetestdemo.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import test.message.com.messagetestdemo.R;
import test.message.com.messagetestdemo.base.BaseActivity;
import test.message.com.messagetestdemo.base.MyApp;
import test.message.com.messagetestdemo.bean.CodeBean;
import test.message.com.messagetestdemo.bean.NewsBannerBean;

public class CodeActivity extends BaseActivity {

    private ImageView back,clear;
    private TextView code,next,login;
    private EditText phonenum,psw;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        initView();

    }

    private void initView() {
        login=(TextView) findViewById(R.id.login);//登陆
        back = (ImageView) findViewById(R.id.back);//返回
        clear = (ImageView) findViewById(R.id.clear);//清空

        phonenum = (EditText) findViewById(R.id.phonenum);
        psw = (EditText) findViewById(R.id.psw);
        next = (TextView) findViewById(R.id.next);
        code = (TextView) findViewById(R.id.code);

        back.setOnClickListener(new MyOnClick());
        clear.setOnClickListener(new MyOnClick());
        login.setOnClickListener(new MyOnClick());
        next.setOnClickListener(new MyOnClick());
        code.setOnClickListener(new MyOnClick());
        /**输入密码登录按钮变色*/
        psw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 输入密码后改变登录按钮颜色
                psw.getText().length();// 获取输入框文本内容长度，为0的时候就是没有内容的
                if (phonenum.getText().length() == 0 && psw.getText().length() == 0){
                    // 按钮颜色切换成默认色
                    next.setBackgroundResource(R.color.login);
                }else { // 输入内容满足判断条件
                    //按钮颜色设置为高亮
                    next.setBackgroundResource(R.color.activity_bg);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private class MyOnClick implements View.OnClickListener{
        Intent intent = new Intent();
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.login:
                    intent.setClass(CodeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.back:
                    finish();
                    showToast("点击了返回按钮，关闭页面");
                    break;
                case R.id.next:
                    showToast("点击了下一步按钮，执行登录操作...");
                    if ("".equals(phonenum.getText().toString().trim())){
                        // 账号为空
                        showToast("请输入账号");
                        return;
                    }
                    if ("".equals(psw.getText().toString().trim())){
                        showToast("请输入验证码");
                        return;
                    }
                    // 存储登录信息
                    savaData();
                    intent.setClass(CodeActivity.this, RegisterActivity.class);//切换 Activityanother至MainActivity
                    Bundle bundle = new Bundle();// 创建Bundle对象
                    bundle.putString("code",psw.getText().toString().trim());//  放入data值为String型
                    bundle.putString("phonenum",phonenum.getText().toString().trim());
                    intent.putExtras(bundle);// 将Bundle对象放入到Intent上
                    startActivity(intent);//  开始跳转

                    finish();
                    break;
                /**密码显示与不显示*/
                case R.id.hide:
                    if (v.isSelected()) {
                        v.setSelected(false);
                        psw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    } else {
                        v.setSelected(true);
                        psw.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    }
                    break;

                case R.id.clear:
                    phonenum.setText("");
                    break;

                case R.id.code:
                    if("".equals(phonenum.getText().toString()))
                    {
                       // Toast.makeText(x.app(), "手机号不能为空", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                       // Toast.makeText(x.app(), "发送成功", Toast.LENGTH_LONG).show();
                        initData("",1);
                    }
                    break;
            }
        }
    }



    /**请求数据*/
    private void initData(String code, final int type){
        String url = "";
        if (type ==1){
            url = "http://api.news.m.zhixiaoren.com/?m=user&a=check_code";//验证码
        }else {
            url = "http://api.news.m.zhixiaoren.com/?m=user&a=check_mobile";//手机号的验证码
        }

        RequestParams params = new RequestParams(url);

        if (type ==2){
            params.addBodyParameter("captcha",code);
        }

        params.addBodyParameter("mobile",phonenum.getText().toString());

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    //解析数据后那到蹄片验证码，再次请求这个接口
                    CodeBean codeBean =new CodeBean();
                    Toast.makeText(x.app(),codeBean.getData(), Toast.LENGTH_LONG).show();
                    if (type==1){//请求图片验证码，解析数据后那到验证码再次请求
                        initData(codeBean.getData(),2);
                    }else if (type==2){// 获取到短信验证码，去请求登录

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinished() {

            }
        });

    }


    /**
     * 存储登录信息，用来做自动登录处理
     */
    private void savaData() {
        SharedPreferences sp = this.getSharedPreferences(MyApp.SP_NAME, 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("isLogined", true);
        edit.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK){
            System.exit(0);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}

