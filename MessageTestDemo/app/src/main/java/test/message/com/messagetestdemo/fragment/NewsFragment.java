package test.message.com.messagetestdemo.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import test.message.com.messagetestdemo.base.BaseActivity;

public class NewsFragment extends Fragment {

    private String channelName;
    private View view;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void setArguments(Bundle bundle) {//接收传入的数据
        channelName = bundle.getString("name");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {//优化View减少View的创建次数
            //该部分可通过xml文件设计Fragment界面，再通过LayoutInflater转换为View组件
            //这里通过代码为fragment添加一个TextView
            TextView tvTitle = new TextView(getActivity());
            tvTitle.setText(channelName);
            tvTitle.setTextSize(16);
            tvTitle.setGravity(Gravity.CENTER);
            tvTitle.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            view = tvTitle;
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {//如果View已经添加到容器中，要进行删除，负责会报错
            parent.removeView(view);
        }
        return view;
    }

}