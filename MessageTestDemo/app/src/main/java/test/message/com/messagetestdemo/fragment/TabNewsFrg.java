package test.message.com.messagetestdemo.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import test.message.com.messagetestdemo.R;
import test.message.com.messagetestdemo.activity.WebActivity;
import test.message.com.messagetestdemo.adapter.FirstAdapter;
import test.message.com.messagetestdemo.adapter.NvaAdapter;
import test.message.com.messagetestdemo.adapter.TwoAdapter;
import test.message.com.messagetestdemo.base.MyApp;
import test.message.com.messagetestdemo.bean.NavBean;
import test.message.com.messagetestdemo.bean.NewsBannerBean;
import test.message.com.messagetestdemo.utils.GlideUtils;


public class TabNewsFrg extends Fragment {

    private View view;
    private NavBean navbean;
    private NvaAdapter nvaAdapter;
    private ViewPager viewPager;
    private RadioGroup rgChannel = null;
    private HorizontalScrollView hvChannel;
    private PageFragmentAdapter adapter = null;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    public static String cid;

    private List<String> tabTitle = new ArrayList<>();
    private List<String> tabClss_id = new ArrayList<>();

    public TabNewsFrg() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_tabnews, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData();
//        initView();
    }
    private void setData(NavBean navbean)
    {
        this.navbean = navbean;
        initView();
    }

    private void initData()
    {
        RequestParams params = new RequestParams(MyApp.NEWSNAVURL);//NEWSNAVURL是首页新闻+banner数据接口
        params.addBodyParameter("cid","1");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    NavBean navBean = new Gson().fromJson(result, NavBean.class);

                    if (navBean != null && navBean.getList().size()>0){
                        Toast.makeText(getActivity(),"请求成功",Toast.LENGTH_SHORT).show();
                        setData(navBean);
                    }else {
                        Toast.makeText(getActivity(),"请求失败",Toast.LENGTH_SHORT).show();
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

    private void initView() {
        rgChannel = (RadioGroup) view.findViewById(R.id.rgChannel);
        viewPager = (ViewPager) view.findViewById(R.id.vpNewsList);
        hvChannel = (HorizontalScrollView) view.findViewById(R.id.hvChannel);
        rgChannel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                viewPager.setCurrentItem(checkedId);
            }
        });
        viewPager.setOnPageChangeListener(new MyOnPagerChangeListener());
        //动态产生RadioButton
        initTab();
        // 初始化viewPager
        initViewPager();
        rgChannel.check(0);

    }

    private void initTab() {
        for (int i = 0; i < navbean.getList().size(); i++) {
            // 生成一个RadioButton，并设置文本内容
            RadioButton rb = (RadioButton) LayoutInflater.from(getActivity()).inflate(R.layout.tab_rb, null);
            rb.setId(i);
            rb.setText(navbean.getList().get(i).getClass_name());
            // 生成一个RadioGroup布局
            RadioGroup.LayoutParams params = new
                    RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT);
            // 把创建的RadioButton和RadioGroup添加到我们布局中的RadioGroup中来
            rgChannel.addView(rb, params);
        }
    }

    private void initViewPager() {
        for (int i = 0; i < navbean.getList().size(); i++) {
            TabOneFrg frag = new TabOneFrg();
           Bundle bundle = new Bundle();
           bundle.putString("Class_id",navbean.getList().get(i).getClass_id());
           frag.setArguments(bundle); //向Fragment传入数据
           fragmentList.add(frag);
            //setcid(navbean.getList().get(i).getClass_id());
          //  TabOneFrg tabOneFrg = new TabOneFrg();
         //   fragmentList.add(tabOneFrg);

        }
        // 初始化viewPager适配器，在fragment里嵌套fragment的时候，viewPager使用的fragmentManage必须是getChildFragmentManager
        adapter = new PageFragmentAdapter(getChildFragmentManager(), navbean, fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(0);
    }

    /**
     * 滑动ViewPager时调整ScroollView的位置以便显示按钮
     *
     * @param idx
     */
    private void setTab(int idx) {
        RadioButton rb = (RadioButton) rgChannel.getChildAt(idx);
        rb.setChecked(true);
        int left = rb.getLeft();
        int width = rb.getMeasuredWidth();
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        int len = left + width / 2 - screenWidth / 2;
        hvChannel.smoothScrollTo(len, 0);//滑动ScroollView
    }

    public void setcid(String cid)
    {
        this.cid=cid;
    }
    public static String getCid()
    {
        return cid;
    }
    // 监听滑动
    private class MyOnPagerChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setTab(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    private static class PageFragmentAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList;
        private FragmentManager fm;
        NavBean navBean;

        public PageFragmentAdapter(FragmentManager fm, NavBean navBean, List<Fragment> fragmentList) {
            super(fm);
            this.navBean = navBean;
            this.fragmentList = fragmentList;
            this.fm = fm;
        }


        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
//            return fragmentList.get(position % fragmentList.size());
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;  //没有找到child要求重新加载
        }
    }

}
