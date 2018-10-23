package test.message.com.messagetestdemo.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import test.message.com.messagetestdemo.R;
import test.message.com.messagetestdemo.activity.WebActivity;
import test.message.com.messagetestdemo.adapter.TwoAdapter;
import test.message.com.messagetestdemo.bean.TwoFrgBean;



public class TabTwoFrg extends Fragment{

    private View view;
    private RecyclerView list;

    public TabTwoFrg() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_two, null);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    private void initView() {

        list = (RecyclerView) view.findViewById(R.id.list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        list.setLayoutManager(linearLayoutManager);

    }

    private void setData(final TwoFrgBean twoFrgBean)
    {
        TwoAdapter twoAdapter = new TwoAdapter(twoFrgBean, getActivity());
        list.setAdapter(twoAdapter);

        twoAdapter.setOnItemClickListener(new TwoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                try{
                    // 跳转详情页
                    Intent intent = new Intent();
                    intent.setClass(getActivity(),WebActivity.class);
                    intent.putExtra("url",twoFrgBean.getData().get(position).getUrl());
                    startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void initData() {
     //String url = "http://newsapi.sina.cn/?resource=feed&behavior=manual&lDid=6708249d-16dc-4644-a0d4-821afb6810bf&oldChwm=&weiboUid=&listCount=25&abver=1536658167710&ua=Xiaomi+-MI+6+__sinanews__7.1.0__android__4.4.2&localSign=a_839547e2-42b4-46b0-81f9-07539e715b21&seId=b0a72f4201&deviceModel=Xiaomi+__Xiaomi__MI+6+&andId=5800e3f4bb095891&osVersion=4.4.2&deviceId=72bb877e1a73ad78&imei=863254010580024&chwm=12040_0007&mpName=%E6%8E%A8%E8%8D%90&upTimes=2&sn=3f4bb0958915800e&connectionType=2&loginType=0&lastTimestamp=1536673476&pullTimes=2&resolution=720x1280&replacedFlag=0&osSdk=19&todayReqTime=0&authGuid=6445275680249814054&from=6071095012&downTimes=0&pullDirection=up&authUid=0&link=&location=32.999199452383074%2C113.56949700004407&deviceIdV1=72bb877e1a73ad78&authToken=a096f12489472e0e8149f529738def63&ignoreAd=0&channel=news_toutiao&accessToken=&aId=01AkphsP4Hmyyy0YYfZfPj1u1c-qfQkpHAbigxm6S7J2Tm2Eo.&mac=58%3A00%3AE3%3AF4%3ABB%3A09&city=WMXX1599&weiboSuid=&urlSign=215b7e7a4f&rand=342";
        String url ="http://dingyue.zhixiaoren.com/mobile/interface/recommend_read/p/1&num=20";
                /**post方式提交数据*/
        RequestParams params = new RequestParams(url);

        /**
         *         //数据请求，这里先要设置回到的call接口对象,数据在接口对象的方法中获取
         x.http().get(entity, call);
         用之前在自己的Application中的onCreate方法里注册xutils
         */

        /**要有MyApp才可以使用*/
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    TwoFrgBean twoFrgBean = new Gson().fromJson(result, TwoFrgBean.class);

                    if (twoFrgBean != null && "success".equals(twoFrgBean.getStatus())) {
                        Toast.makeText(x.app(), "请求成功", Toast.LENGTH_LONG).show();
                        setData(twoFrgBean);

                    } else {
                        Toast.makeText(x.app(), "请求失败", Toast.LENGTH_LONG).show();
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


}
