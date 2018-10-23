package test.message.com.messagetestdemo.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import test.message.com.messagetestdemo.R;
import test.message.com.messagetestdemo.activity.LoginActivity;
import test.message.com.messagetestdemo.activity.SetupActivity;
import test.message.com.messagetestdemo.base.MyApp;
import test.message.com.messagetestdemo.utils.GlideUtils;



@SuppressLint("ValidFragment")
public class TabFourFrg extends Fragment {

    private ImageView head_portrait,tu_1,tu_2;
    private LinearLayout set_up;
    private LinearLayout exitapp;
    private View view;
    private Context mContext;
    private TextView login;


    @SuppressLint("ValidFragment")
    public TabFourFrg(Context context) {
        super();
        this.mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_four, null);
        mContext = getActivity();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        set_up=(LinearLayout) view.findViewById(R.id.set_up);
        exitapp=(LinearLayout) view.findViewById(R.id.exitapp);
        head_portrait = (ImageView) view.findViewById(R.id.head_img);
        login=(TextView)view.findViewById(R.id.login);

        /**图片地址*/
        String url ="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536820592617&di=95507df48fadb75f1732ce1f33913da2&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201605%2F27%2F20160527004759_8V35R.jpeg";
        String url_1 = "http://imgsrc.baidu.com/imgad/pic/item/aec379310a55b31929bc988349a98226cefc17fa.jpg";
        String url_2 = "http://img2.imgtn.bdimg.com/it/u=2574899439,2545798563&fm=26&gp=0.jpg";


        /**跳转到设置*/
        set_up.setOnClickListener(new MyOnClick());
        login.setOnClickListener(new MyOnClick());

        /**设置头像以及小图标圆角*/
        GlideUtils.getInstance().loadImg(mContext,url,head_portrait);

        GlideUtils.getInstance().LoadContextRoundBitmap(getActivity(),url,head_portrait,15);


        LinearLayout exitapp = (LinearLayout) view.findViewById(R.id.exitapp);
        exitapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sp = mContext.getSharedPreferences(MyApp.SP_NAME, 0);
                SharedPreferences.Editor edit = sp.edit();
                edit.putBoolean("isLogined", false);
                edit.putBoolean("isFirst", false);
                edit.commit();

                Intent intent = new Intent();
                intent.setClass(mContext, LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

    }

    private class MyOnClick implements View.OnClickListener {
        Intent intent = new Intent();
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.set_up:
                    intent.setClass(getActivity(), SetupActivity.class);
                    startActivity(intent);
                case R.id.login:
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivity(intent);

            }
        }

    }


}
