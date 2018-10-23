package test.message.com.messagetestdemo.base;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    private Toast toast = null;

    /**
     * toast 短 int
     *
     * @param pResId
     */
    protected void showToast(int pResId) {
        showToast(getString(pResId));
    }

    /**
     * toast 长 int
     *
     * @param pResId
     */
    protected void showLongToast(int pResId) {
        showLongToast(getString(pResId));
    }

    /**
     * toast 短 String
     *
     * @param pMsg
     */
    protected void showToast(String pMsg) {
        if (toast == null) {
            initToast();
        }
        TextView view = (TextView) toast.getView();
        view.setText(pMsg);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * toast 长 string
     *
     * @param pMsg
     */
    protected void showLongToast(String pMsg) {
        if (toast == null) {
            initToast();
        }
        TextView view = (TextView) toast.getView();
        view.setText(pMsg);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }


    @SuppressLint("ShowToast")
    private void initToast() {
        toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
        TextView textView = new TextView(getApplicationContext());
        textView.setBackgroundColor(Color.parseColor("#999999"));
//		textView.setBackgroundResource(R.drawable.pop);//自定义toast背景色
        textView.setPadding(15, 10, 15, 10);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(0xffffffff);
        toast.setView(textView);
        toast.setGravity(Gravity.BOTTOM, 0, 50);

    }

}
