package test.message.com.messagetestdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import test.message.com.messagetestdemo.R;
import test.message.com.messagetestdemo.bean.NavBean;
import test.message.com.messagetestdemo.bean.NewsBannerBean;
import test.message.com.messagetestdemo.utils.GlideUtils;
import test.message.com.messagetestdemo.utils.MyUtils;
import test.message.com.messagetestdemo.views.MyViewPager;

public class NvaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    /***************************  new start **************************************/
    private NavBean data;
    // banner是否有数据
    boolean isBanner = true;

    /**在创建这个适配器对象的时候，将所有数据都传入，以便进行之后的操作。*/
    public NvaAdapter(NavBean data, Context mContext) {
        this.data = data;
        this.context = mContext;
    }
    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    /**利用创建时传入的数据，获取列表里总共有多少个Item(项目)*/
    public int getItemCount() {
        // 判断数据是否为空
        return 0;
    }

    /**的到item的类型*/
    @Override
    public int getItemViewType(int position) {
        // 判断banner是否加载(前面判断banner数据存在的情况，加载banner，否则不加载banner)
        if (isBanner) {
            // 判断第一条item，后面用做banner判断处理
            if (position == 0) {
                // 返回type类型为banner的布局类型
                return 0;
            }
        }
        return 1;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        View convertView;
        TextView title;
        ImageView img;
        TextView  from, time;
        MyViewPager item_vp;
        TextView banner_tv;
        LinearLayout point;

        public MyViewHolder(View view, int viewType) {
            super(view);
            convertView = view;
            if (viewType == 0) {
                // 初始化布局操作
                item_vp = (MyViewPager) view.findViewById(R.id.item_vp);
                point = (LinearLayout) view.findViewById(R.id.point);
                banner_tv = (TextView) view.findViewById(R.id.banner_tv);
            } else {
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int adapterPosition = getAdapterPosition();
                        if (isBanner) {
                            adapterPosition = getAdapterPosition() - 1;
                        }
                        if (listener != null) {
                            convertView.clearAnimation();
                            listener.onItemClick(v, adapterPosition);
                        }
                    }
                });
            }

            if (viewType == 1) {
                title = (TextView) view.findViewById(R.id.title);
                img = (ImageView) view.findViewById(R.id.img);
                from = (TextView) view.findViewById(R.id.from);
                time = (TextView) view.findViewById(R.id.time);
            }

        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private FirstAdapter.OnItemClickListener listener;

    public void setOnItemClickListener(FirstAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public NvaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 0) {
            return new NvaAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first_zero, parent, false), viewType);
        }
        if (viewType == 1) {
            return new NvaAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first, parent, false), viewType);
        }
        NvaAdapter.MyViewHolder holder = new NvaAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_first, parent, false), viewType);
        return holder;
    }

    /**绑定数据*/
    public void onBindViewHolder(final RecyclerView.ViewHolder mHolder, final int position) {
        Log.i("notify", "notify");
        final NvaAdapter.MyViewHolder holder = ((NvaAdapter.MyViewHolder) mHolder);

        if (getItemViewType(position) == 0) {
        }

        if (getItemViewType(position) == 1) {

            return;
        }
        return;
    }

    /**
     * 页面Image
     */
    private ArrayList<ImageView> datas = new ArrayList<>();


    /**
     * 移除自动跳转
     */
    public void removeRunnable() {
        if (myHandler != null) {
            myHandler.removeCallbacks(mRunnable);
        }
    }

    public static NvaAdapter.MyHandler myHandler;
    //设置当前 第几个图片 被选中
    private int autoCurrIndex = 0;
    private static final int UPTATE_VIEWPAGER = 0;
    private static final int UPTATE_VIEWPAGER2 = 1;

    private static class MyHandler extends Handler {
        private FirstAdapter context;
        public ViewPager vp;
        // 弱引用 ，防止内存泄露
        private WeakReference<FirstAdapter> weakReference;

        public MyHandler(FirstAdapter context, ViewPager vp) {
            weakReference = new WeakReference<>(context);
            this.context = context;
            this.vp = vp;
        }

        public void handleMessage(final Message msg) {
            // 通过  软引用  看能否得到activity示例
            FirstAdapter c = weakReference.get();
            if (c != null) {  // 如果当前Activity，进行UI的更新
                vp.getAdapter().notifyDataSetChanged();
                switch (msg.what) {
                    case UPTATE_VIEWPAGER:
                        if (msg.arg1 != 0) {
                            int count = vp.getChildCount();
                        } else {
                            //false 当从末页调到首页是，不显示翻页动画效果，
                            vp.setCurrentItem(msg.arg1, false);
                        }
                        break;
                    case UPTATE_VIEWPAGER2:
                        if (msg.arg1 != 0) {
                            vp.setCurrentItem(msg.arg1);
                        } else {
                            //false 当从末页调到首页是，不显示翻页动画效果，
                            vp.setCurrentItem(msg.arg1, false);
                        }
                        break;
                }
            }
        }
    }

    /**
     * 跳转到下一个
     */
    public boolean isStop;
    public class MyRunnable implements Runnable {
        ViewPager viewPager;

        public void setViewPager(ViewPager viewPager) {
            this.viewPager = viewPager;
        }

        @Override
        public void run() {
            if (!isStop && viewPager != null) {
                autoCurrIndex++;
                viewPager.setCurrentItem(autoCurrIndex);
            }
        }
    }

    private NvaAdapter.MyRunnable mRunnable = new NvaAdapter.MyRunnable();

    /*************************   推荐列表banner模块 end   ***********************************/


    /**
     * banner 切换监听---------------------------------------------------------
     */
    /**
     * 停止自动跳转
     */
    private boolean isRunning = true;
    public void stopRunnable() {
        isStop = true;
        isRunning = false;
    }

    /**
     * 未选中
     */
    private int prePosition = 0;

    int w;
    private class OnPChange implements ViewPager.OnPageChangeListener {

        private FirstAdapter.MyViewHolder holder;
        private List<NewsBannerBean.PicListBean> bannerBean;

        public OnPChange(FirstAdapter.MyViewHolder holder, List<NewsBannerBean.PicListBean> bannerBean) {
            this.holder = holder;
            this.bannerBean = bannerBean;
        }

        //配合Adapter的currentItem字段进行设置。
        @Override
        public void onPageSelected(int position) {
            autoCurrIndex = position;
            if (myHandler != null) {
                removeRunnable();
                mRunnable.setViewPager(holder.item_vp);
                myHandler.postDelayed(mRunnable, 4000);
            }
            int curP = position % datas.size();
            if (w == 0) {
                w = MyUtils.dip2px(context.getApplicationContext(), 6);
            }
            ViewGroup.LayoutParams lp = holder.point.getChildAt(curP).getLayoutParams();
            lp.width = w;
            lp.height = w;
            try {
                int w2 = MyUtils.dip2px(context.getApplicationContext(), 4);
                ViewGroup.LayoutParams lp2 = holder.point.getChildAt(prePosition).getLayoutParams();
                lp2.width = w2;
                lp2.height = w2;
                if (curP == prePosition) {
                    lp = holder.point.getChildAt(curP).getLayoutParams();
                    lp.width = w;
                    lp.height = w;
                }
                holder.point.getChildAt(prePosition).setLayoutParams(lp2);
                holder.point.getChildAt(curP).setLayoutParams(lp);
                holder.banner_tv.setText("" + bannerBean.get(curP).getTitle());
                holder.point.getChildAt(curP).setBackgroundResource(R.drawable.shape_round_wel_on);// 选中背景色

                holder.point.getChildAt(prePosition).setBackgroundResource(R.drawable.shape_round_wel_off);// 未选中背景色

                if (position % datas.size() != 0) {
                    holder.point.getChildAt(0).setBackgroundResource(R.drawable.shape_round_wel_off);// 未选中背景色
                }
                holder.point.getChildAt(curP).setBackgroundResource(R.drawable.shape_round_wel_on);// 选中状态背景色


            } catch (Exception e) {
                e.printStackTrace();
            }
            /** 选中 */
            prePosition = position % datas.size();

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        //覆写该方法实现轮播效果的暂停和恢复
        @Override
        public void onPageScrollStateChanged(int arg0) {
            switch (arg0) {
                case ViewPager.SCROLL_STATE_DRAGGING: // 滑动中
                    stopRunnable();
                    break;
                case ViewPager.SCROLL_STATE_IDLE: // 滑动结束

                    break;
                default:
                    break;
            }
        }
    }





    /**
     * 设置 banner 的 ViewPager
     *
     * @param holder
     * @param bannerBean
     */
    private void setBannerVP(final NvaAdapter.MyViewHolder holder, final List<NavBean.ListBean> bannerBean) {

        datas = new ArrayList<>();
        for (int i = 0; i < bannerBean.size(); i++) {
            // 设置banner下面的标题
            holder.banner_tv.setText("" + bannerBean.get(i).getClass_name());
            // 创建banner显示的图片控件
            ImageView iv = new ImageView(context);
            // 设置缩放类型，铺满控件
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            datas.add(iv);

            // 设置banner图中的小圆点指示器
            int px2dp = MyUtils.dip2px(context.getApplicationContext(), 4);  //dip2px在MyUtlsd里自己设置
            int w = MyUtils.dip2px(context.getApplicationContext(), 6);
            ImageView view = new ImageView(context);
            // 为这个view设置背景 未选中状态背景色
            view.setBackgroundResource(R.drawable.shape_round_wel_off);
            // 定义布局参数，并给view设置参数(宽，高)
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(px2dp, px2dp);
            if (i != 0) {
                params.leftMargin = w;
            }
            view.setLayoutParams(params);
            // 将定义好的view加入到线性布局中,当指示圆点比图片还多的时候移除当前圆点，保证圆点和图片数量一致
            if (holder.point.getChildCount() > bannerBean.size() - 1) {
                holder.point.removeView(view);
            } else {
                holder.point.addView(view);
            }
            holder.point.getChildAt(0).setBackgroundResource(R.drawable.shape_round_wel_on);// 选中状态背景色

        }

    }
}