package test.message.com.messagetestdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import test.message.com.messagetestdemo.activity.WebActivity;
import test.message.com.messagetestdemo.bean.FirstFrgBean;
import test.message.com.messagetestdemo.bean.NewsBannerBean;

public  class ImageAdapter extends PagerAdapter {

    private ArrayList<ImageView> viewlist;
    private List<NewsBannerBean.PicListBean> data;
    private FirstFrgBean.DataBean lastItem;
    private Context mContext;

    public ImageAdapter(Context recy, ArrayList<ImageView> viewlist, List<NewsBannerBean.PicListBean> data) {
        this.viewlist = viewlist;
        this.data = data;
        mContext = recy;
    }

    @Override
    public int getCount() {
        //设置成最大，使用户看不到边界
        if (data.size() > 1) {
            return Integer.MAX_VALUE;
        } else {
            return data.size();
        }
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //Warning：不要在这里调用removeView
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //对ViewPager页号求模取出View列表中要显示的项
        position %= viewlist.size();
        if (position < 0) {
            position = viewlist.size() + position;
        }
        ImageView view = viewlist.get(position);
        final int finalPosition = position;
        if (mContext != null) {
            /** banner  图片点击事件 */
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NewsBannerBean.PicListBean picListBean = data.get(finalPosition);
                    Intent intent = new Intent(mContext, WebActivity.class);
                    intent.putExtra("url", picListBean.getUrl());
                    mContext.startActivity(intent);
                    Toast.makeText(mContext, "点击了" + finalPosition + "banner图", Toast.LENGTH_SHORT).show();
                }
            });

            //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
            ViewParent vp = view.getParent();
            if (vp != null) {
                ViewGroup parent = (ViewGroup) vp;
                parent.removeView(view);
            }
            container.addView(view);
        }
        return view;
    }

}