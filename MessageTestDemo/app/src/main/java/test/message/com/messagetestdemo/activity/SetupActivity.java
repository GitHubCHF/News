package test.message.com.messagetestdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import test.message.com.messagetestdemo.R;
import test.message.com.messagetestdemo.base.BaseActivity;



public class SetupActivity extends BaseActivity {
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        initView();
    }

    private void initView() {
        back =(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new MyOnClick());
    }

    private class MyOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.back:
                    finish();
                    showToast("点击了返回按钮，关闭页面");
                    break;
            }
        }
    }

}
