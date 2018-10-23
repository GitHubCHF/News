package test.message.com.messagetestdemo.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import test.message.com.messagetestdemo.R;
import test.message.com.messagetestdemo.activity.MainActivity;
import test.message.com.messagetestdemo.activity.WebActivity;
import test.message.com.messagetestdemo.adapter.FirstAdapter;
import test.message.com.messagetestdemo.base.MyApp;
import test.message.com.messagetestdemo.bean.FirstFrgBean;
import test.message.com.messagetestdemo.bean.NewsBannerBean;



public class TabOneFrg extends Fragment {

    private View view;
    private String[] titles;
    private RecyclerView list;
    FirstAdapter firstAdapter;
    private int class_id;
    private static final String TAG = "TabOneFrg";

    public TabOneFrg() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_one, null);

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
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        list.setLayoutManager(linearLayoutManager);
    }

    /**
     * 设置数据
     *
     * @param firstFrgBean
     */
    private void setData(final NewsBannerBean firstFrgBean) {

        firstAdapter = new FirstAdapter(firstFrgBean , getActivity());
        list.setAdapter(firstAdapter);

        firstAdapter.setOnItemClickListener(new FirstAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "点击的item是：" + position, Toast.LENGTH_SHORT).show();
                try{
                    // 跳转详情页
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), WebActivity.class);
                    intent.putExtra("url",firstFrgBean.getNews_list().get(position).getUrl());
                    startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    /**请求数据*/
    private void initData()
    {
        RequestParams params = new RequestParams(MyApp.NEWSBANNERURL+"&cid="+getArguments().getString("Class_id"));//NEWSBANNERURL是首页新闻+banner数据接口
//        params.addBodyParameter("cid","1");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG,"" + result);
                try {
                    NewsBannerBean newsBannerBean = new Gson().fromJson(result, NewsBannerBean.class);

                    if (newsBannerBean != null && newsBannerBean.getNews_list().size()>0){
                        Toast.makeText(getActivity(),"请求成功",Toast.LENGTH_SHORT).show();
                        setData(newsBannerBean);
                    }else {
                        Toast.makeText(getActivity(),"请求失败",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),"解析失败",Toast.LENGTH_SHORT).show();

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
