package test.message.com.messagetestdemo.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import test.message.com.messagetestdemo.R;
import test.message.com.messagetestdemo.base.BaseActivity;
import test.message.com.messagetestdemo.base.MyApp;
import test.message.com.messagetestdemo.bean.CodeBean;
import test.message.com.messagetestdemo.bean.LoginBean;




public class LoginActivity extends BaseActivity {

    private ImageView back,hide,clear;
    private TextView login,register;
    private EditText phonenum,psw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        initView();

    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        hide = (ImageView) findViewById(R.id.hide);
        clear = (ImageView) findViewById(R.id.clear);
        register=(TextView)findViewById(R.id.register);

        phonenum = (EditText) findViewById(R.id.phonenum);
        psw = (EditText) findViewById(R.id.psw);
        login = (TextView) findViewById(R.id.login);

        back.setOnClickListener(new MyOnClick());
        login.setOnClickListener(new MyOnClick());
        hide.setOnClickListener(new MyOnClick());
        clear.setOnClickListener(new MyOnClick());
        register.setOnClickListener(new MyOnClick());


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
                    login.setBackgroundResource(R.color.set_up);
                }else { // 输入内容满足判断条件
                    //按钮颜色设置为高亮
                    login.setBackgroundResource(R.color.orange);
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
                case R.id.back:
                    finish();
                    showToast("点击了返回按钮，关闭页面");
                    break;

                case R.id.login:
                    showToast("点击了登录按钮，执行登录操作...");

                    if ("".equals(phonenum.getText().toString().trim())){
                        // 账号为空
                        showToast("请输入账号");
                        return;
                    }
                    if ("".equals(psw.getText().toString().trim())){
                        showToast("请输入密码");
                        return;
                    }
                    // 存储登录信息
                    savaData();
                    initData(phonenum.getText().toString().trim(),psw.getText().toString().trim());
                    intent.setClass(LoginActivity.this, TwoActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.register:
                    intent.setClass(LoginActivity.this, CodeActivity.class);
                    startActivity(intent);
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
            }
        }
    }



    /**请求数据*/
    private void initData(String ph,  String psw){
        String url = "http://api.news.m.zhixiaoren.com/?m=login&a=index&password="+psw+"&username="+ph+"";//登陆
        RequestParams params = new RequestParams(url);
       // params.addBodyParameter("mobile",phonenum.getText().toString());

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    //解析数据后那到蹄片验证码，再次请求这个接口
                    LoginBean codeBean =new LoginBean();
                    Toast.makeText(x.app(),codeBean.getTruename(), Toast.LENGTH_LONG).show();
                    Toast.makeText(x.app(),"登陆成功", Toast.LENGTH_LONG).show();
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
