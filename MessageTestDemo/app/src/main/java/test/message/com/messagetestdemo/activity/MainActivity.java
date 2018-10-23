package test.message.com.messagetestdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import test.message.com.messagetestdemo.R;
import test.message.com.messagetestdemo.base.BaseActivity;

/**
 * 主启动activity
 * 可以用来设置启动页以及广告页
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    // 申明布局文件

    /**
     * 多行注释
     * 申明布局文件
     */
    ImageView head;
    TextView name;
    TextView company;
    TextView name_tv;
    TextView address;
    TextView phone;
    TextView phone_num;
    TextView job;
    EditText edit;
    Button button;
    Button login;

    /**
     * 初始化文本内容
     * 1：名字，2：公司。3：地址。4：电话
     */
    String[] string = new String[]{"王思聪", "上海万达股份实业有限公司", "上海市浦东新区XX街道80号", "13888889999"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        getData();

    }

    /**
     *  请求数据
     */
    private void getData() {
        initData();
    }


    /**
     * 设置数据
     */
    private void initData(){

        head.setImageResource(R.drawable.head_imgs);

        /*name_tv.setText("我是第一个安卓app");
        company.setText("计算机软件机房");
        address.setText("湖北省武汉市江夏区");
        phone_num.setText("15527067306");
//        job.setVisibility(View.VISIBLE);//显示，可见
        job.setVisibility(View.GONE);//不可见，看不到了*/

        name_tv.setText(string[0]);
        company.setText(string[1]);
        address.setText(string[2]);
        phone_num.setText(string[3]);
//        job.setVisibility(View.VISIBLE);//显示，可见
        job.setVisibility(View.GONE);//不可见，看不到了

    }
    /**
     * @auther zbl
     * @Data 2018-09-06 16:19:07
     * 初始化布局文件
     */
    private void initView(){
        // 初始化布局
        head = (ImageView) findViewById(R.id.head);
        name = (TextView) findViewById(R.id.name);
        company = (TextView) findViewById(R.id.company);
        name_tv = (TextView) findViewById(R.id.name_tv);
        address = (TextView) findViewById(R.id.address);
        phone = (TextView) findViewById(R.id.phone);
        phone_num = (TextView) findViewById(R.id.phone_num);
        job = (TextView) findViewById(R.id.job);
        edit = (EditText) findViewById(R.id.edit);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 吐司
                Toast.makeText(MainActivity.this,"编辑框的文本内容：" + edit.getText(),Toast.LENGTH_SHORT).show();
            }
        });



        /**文本变化登录按钮变化*/
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 文本发生改变之前
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if ("".equals(s)){
                    button.setBackgroundResource(R.color.gray);
                }else {
                    // 文本变化中
                    button.setBackgroundResource(R.color.activity_bg);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                // 文本变化后
            }
        });


        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new MyOnClick());//登录跳转到登录页面

    }

    private class MyOnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.login:

                    Intent intent = new Intent();
                    // 从哪里跳到哪里去
                    intent.setClass(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
//                    finish();// 管理当前页面
                    break;
            }
        }
    }
    /**
     * onstart
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");

    }
}
