package test.message.com.messagetestdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import test.message.com.messagetestdemo.fragment.TabNewsFrg;
import test.message.com.messagetestdemo.views.MyViewPager;
import test.message.com.messagetestdemo.R;
import test.message.com.messagetestdemo.fragment.TabFourFrg;
import test.message.com.messagetestdemo.fragment.TabOneFrg;
import test.message.com.messagetestdemo.fragment.TabThreeFrg;
import test.message.com.messagetestdemo.fragment.TabTwoFrg;
import test.message.com.messagetestdemo.fragment.TabFiveFrg;
import test.message.com.messagetestdemo.base.BaseActivity;

/**
 * class ：
 * auther:  benlongzhu
 * date  :  2018/9/6 下午3:09
 * readme:  首页activity
 */

public class TwoActivity extends BaseActivity {

    //    private ImageView back;
    private LinearLayout tab_ones, tab_twos, tab_threes, tab_fours,tab_fives;
    private TextView title;
    private View[] views;
    private ImageView img,i1,i2,i3,i4,i5;

    /** 各个栏目fragment */
    private Fragment tabOneFrg,tabTwoFrg,tabThreeFrg ,tabFourFrg,tabFiveFrg;

    /** viewpager */
    public MyViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        initView();
    }

    private void initView() {

        // 单个点击事件
//        back = (ImageView) findViewById(R.id.back);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tab_ones = (LinearLayout) findViewById(R.id.tab_ones);
        tab_twos = (LinearLayout) findViewById(R.id.tab_twos);
        tab_threes = (LinearLayout) findViewById(R.id.tab_threes);
        tab_fours = (LinearLayout) findViewById(R.id.tab_fours);
        tab_fives=(LinearLayout) findViewById(R.id.tab_fives);


        title = (TextView) findViewById(R.id.title);

        img = (ImageView) findViewById(R.id.back);
        i1 = (ImageView) findViewById(R.id.i1);
        i2 = (ImageView) findViewById(R.id.i2);
        i3 = (ImageView) findViewById(R.id.i3);
        i4 = (ImageView) findViewById(R.id.i4);
        i5 = (ImageView) findViewById(R.id.i5);

        /**内容*/
        viewPager = (MyViewPager) findViewById(R.id.viewPager);

        // 把底部栏四个tab添加到数组里
        views = new View[]{tab_ones,tab_twos,tab_threes,tab_fours,tab_fives};

        /** 根据点击位置选择相应的Fragment,例如0是首页 **/
        setTabSelect(0);

        /**默认变更成初始菜单为tab_one*/
        tab_ones.setSelected(true);

        /**初始化viewPager*/
        initVierPager();

        for (int i = 0; i < 5; i++) {
            views[i].setOnClickListener(new MyOnClick());
        }
    }

    /**
     * 底部按钮点击事件
     */
    private class MyOnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.tab_ones:

                    if (viewPager.getCurrentItem() == 0){
                        if (tabOneFrg.isVisible()) {
//                            ((TabOneFrg) tabOneFrg).tabClick();
                        }
                    }else{
                        viewPager.setCurrentItem(0);
                        setTabSelect(0);
                    }

                    break;

                case R.id.tab_twos:

                    if (viewPager.getCurrentItem() == 1){
                        if (tabTwoFrg.isVisible()) {
//                            ((TabTwoFrg) tabTwoFrg).tabClick();
                        }
                    }else{
                        viewPager.setCurrentItem(1);
                        setTabSelect(1);
                    }

                    break;

                case R.id.tab_threes:

                    if (viewPager.getCurrentItem() == 2){
                        if (tabThreeFrg.isVisible()) {
//                            ((TabThreeFrg) tabThreeFrg).tabClick();
                        }
                    }else{
                        viewPager.setCurrentItem(2);
                        setTabSelect(2);
                    }

                    break;

                case R.id.tab_fours:

                    if (viewPager.getCurrentItem() == 3){
                        if (tabFourFrg.isVisible()) {
//                            ((TabFourFrg) tabFourFrg).tabClick();
                        }
                    }else{
                        viewPager.setCurrentItem(3);
                        setTabSelect(3);
                    }

                    break;

                case R.id.tab_fives:

                    if (viewPager.getCurrentItem() == 4){
                        if (tabFiveFrg.isVisible()) {
//                            ((TabFiveFrg) tabFiveFrg).tabClick();
                        }
                    }else{
                        viewPager.setCurrentItem(4);
                        setTabSelect(4);
                    }

                    break;


                default:
                    break;
            }
        }
    }

    /**
     * 初始化viewPager
     */
    private void initVierPager() {
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(5);// viewPager的默认加载
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            float x;

            @Override
            public void onPageSelected(int arg0) {
                if(viewPager.isCanScroll){
                    setTabSelect(arg0);
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                x = arg1;
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    /**
     * viewPager适配器
     */
    private class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int arg0) {

            switch (arg0) {
                case 0://首页
                    if (tabOneFrg == null) {
                        tabOneFrg = new TabNewsFrg();
                    }
                    return tabOneFrg;

                case 1://订阅
                    if (tabTwoFrg == null) {
                        tabTwoFrg = new TabTwoFrg();
                    }
                    return tabTwoFrg;

                case 2://发现
                    if (tabThreeFrg == null) {
                        tabThreeFrg = new TabThreeFrg();
                    }
                    return tabThreeFrg;
                case 3://商城
                    if (tabFiveFrg == null) {
                        tabFiveFrg = new TabFiveFrg();
                    }
                    return tabFiveFrg;
                case 4://我的
                    if (tabFourFrg == null) {
                        tabFourFrg = new TabFourFrg(TwoActivity.this);
                    }
                    return tabFourFrg;

                default:
                    return tabOneFrg;
            }

        }

        @Override
        public int getCount() {
            return views.length;
        }

    }

    private int position;

    /** 根据点击位置选择相应的Fragment **/
    public void setTabSelect(int pos) {
        position = pos;
        setTextSelect(pos);

        if (pos == 0){
            title.setVisibility(viewPager.GONE);
            img.setVisibility(viewPager.VISIBLE);
            img.setImageResource(R.drawable.icon_txt_logo);
            i1.setImageResource(R.drawable.tab_onetrue);
            i2.setImageResource(R.drawable.tab_two);
            i3.setImageResource(R.drawable.tab_three);
            i4.setImageResource(R.drawable.tab_four);
            i5.setImageResource(R.drawable.tab_five);
        }else if (pos == 1){
            title.setVisibility(viewPager.VISIBLE);
            img.setVisibility(viewPager.GONE);
            title.setText("直销号");
            i1.setImageResource(R.drawable.tab_one);
            i2.setImageResource(R.drawable.tab_twotrue);
            i3.setImageResource(R.drawable.tab_three);
            i4.setImageResource(R.drawable.tab_four);
            i5.setImageResource(R.drawable.tab_five);
        }else if (pos == 2){
            title.setVisibility(viewPager.VISIBLE);
            img.setVisibility(viewPager.GONE);
            title.setText("秀我");
            i1.setImageResource(R.drawable.tab_one);
            i2.setImageResource(R.drawable.tab_two);
            i3.setImageResource(R.drawable.tab_threetrue);
            i4.setImageResource(R.drawable.tab_four);
            i5.setImageResource(R.drawable.tab_five);
        }else if (pos == 3){
            title.setVisibility(viewPager.VISIBLE);
            img.setVisibility(viewPager.GONE);
            title.setText("分享秀");
            i1.setImageResource(R.drawable.tab_one);
            i2.setImageResource(R.drawable.tab_two);
            i3.setImageResource(R.drawable.tab_three);
            i4.setImageResource(R.drawable.tab_foutrue);
            i5.setImageResource(R.drawable.tab_five);
        }else
        {
            title.setVisibility(viewPager.VISIBLE);
            img.setVisibility(viewPager.GONE);
            title.setText("我的");
            i1.setImageResource(R.drawable.tab_one);
            i2.setImageResource(R.drawable.tab_two);
            i3.setImageResource(R.drawable.tab_three);
            i4.setImageResource(R.drawable.tab_four);
            i5.setImageResource(R.drawable.tab_fivetrue);
        }

    }

    /** 选中菜单变换 **/
    private void setTextSelect(int pos) {
        for (int i = 0; i < 5; i++) {
            if (i == pos) {
                views[i].setSelected(true);
            } else {
                views[i].setSelected(false);
            }
        }
    }



}
