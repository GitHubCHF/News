package test.message.com.messagetestdemo.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import test.message.com.messagetestdemo.views.MyViewPager;
import test.message.com.messagetestdemo.R;
import test.message.com.messagetestdemo.base.BaseActivity;
import test.message.com.messagetestdemo.base.MyApp;
import test.message.com.messagetestdemo.utils.GlideUtils;
import test.message.com.messagetestdemo.views.MyViewPager;


public class WelComeActivity extends BaseActivity {

    private MyViewPager wel_vp;
    private LinearLayout start;

//    private String[] images = new String[]{"http://img4.duitang.com/uplo21/20130621203803_KSWVk.thumb.700_0.jpeg",
//            "http://img3.duitang.com/uploads/item/201507/27/20150727120420_NLTRy.jpeg",
//            "http://imgsrc.baidu.com/imgad/pic/item/caef76094b36acafddb8da2876d98d1000e99c8e.jpg",
//            "http://img.zcool.cn/community/01d7c65902c095a801214550a706a5.jpg@1280w_1l_2o_100sh.jpg"};

    private int[] images_img = new int[]{R.drawable.guide_page_1, R.drawable.guide_page_2,R.drawable.guide_page_3,R.drawable.guide_page_4};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);

        initView();
    }

    private void initView() {
        start = (LinearLayout) findViewById(R.id.start);
        final SharedPreferences sharedPreferences = this.getSharedPreferences(MyApp.SP_NAME, 0);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 存储信息，已经不是第一次安装了
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putBoolean("isFirst", true);
                edit.commit();
                Intent intent = new Intent();
                intent.setClass(WelComeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        wel_vp = (MyViewPager) findViewById(R.id.wel_vp);
        setVp(images_img);
    }

    /**
     * 设置ViewPager
     *
     * @param images_img
     */
    private void setVp(final int[] images_img) {

        ArrayList<ImageView> imgs = new ArrayList<>();

        for (int i = 0; i < images_img.length; i++) {
            ImageView img = new ImageView(this);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            img.setBackgroundResource(this.images_img[i]);
            // GlideUtils.getInstance().loadImgFIT_XY_NoDefate(this, this.images[i], img);
            imgs.add(img);
        }

        wel_vp.setAdapter(new MyPageAdapter(imgs));
        wel_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (position == images_img.length - 1) {
                    //最后一个页面，可以操作显示点击进入操作
                    start.setVisibility(View.VISIBLE);
                } else {
                    start.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private class MyPageAdapter extends PagerAdapter {

        ArrayList<ImageView> imgs;

        public MyPageAdapter(ArrayList<ImageView> imgs) {
            this.imgs = imgs;
        }

        @Override
        public int getCount() {
            return imgs.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView view = imgs.get(position);

//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent();
//                    intent.setClass(WelConmeActivity.this, LoginActivty.class);
//                    startActivity(intent);
//                    finish();
//                }
//            });

            //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
            ViewParent vp = view.getParent();
            if (vp != null) {
                ViewGroup parent = (ViewGroup) vp;
                parent.removeView(view);
            }
            container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //Warning：不要在这里调用removeView
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
