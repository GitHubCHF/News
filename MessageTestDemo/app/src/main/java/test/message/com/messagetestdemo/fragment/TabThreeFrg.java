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
import test.message.com.messagetestdemo.adapter.ThreeAdapter;
import test.message.com.messagetestdemo.adapter.TwoAdapter;
import test.message.com.messagetestdemo.bean.ThreeFrgBean;
import test.message.com.messagetestdemo.bean.TwoFrgBean;



public class TabThreeFrg extends Fragment{

    private View view;
    private RecyclerView list;

    public TabThreeFrg() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_three, null);
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

    private void setData(final ThreeFrgBean threeFrgBean)
    {
        ThreeAdapter threeAdapter = new ThreeAdapter(threeFrgBean, getActivity());
        list.setAdapter(threeAdapter);

        threeAdapter.setOnItemClickListener(new TwoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                try{
                    // 跳转详情页
                    Intent intent = new Intent();
                    intent.setClass(getActivity(),WebActivity.class);
                    intent.putExtra("url",threeFrgBean.getData().get(position).getUrl());
                    startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void initData() {
        String url ="http://api.news.m.zhixiaoren.com/showme/showme_list/?p=1&num=20";
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
                    ThreeFrgBean threeFrgBean = new Gson().fromJson(result, ThreeFrgBean.class);

                    if (threeFrgBean != null && "success".equals(threeFrgBean.getStatus())) {
                        Toast.makeText(x.app(), "请求成功", Toast.LENGTH_LONG).show();
                        setData(threeFrgBean);

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
