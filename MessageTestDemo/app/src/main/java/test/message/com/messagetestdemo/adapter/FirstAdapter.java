package test.message.com.messagetestdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import test.message.com.messagetestdemo.bean.NewsBannerBean;
import test.message.com.messagetestdemo.views.MyViewPager;
import test.message.com.messagetestdemo.R;
import test.message.com.messagetestdemo.bean.FirstFrgBean;
import test.message.com.messagetestdemo.utils.GlideUtils;
import test.message.com.messagetestdemo.utils.MyUtils;
import test.message.com.messagetestdemo.views.MyViewPager;



public class FirstAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    /***************************  new start **************************************/
    private NewsBannerBean data;
    // banner是否有数据
    boolean isBanner = true;

    /**在创建这个适配器对象的时候，将所有数据都传入，以便进行之后的操作。*/
    public FirstAdapter(NewsBannerBean data, Context mContext) {
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
        if (data != null) {
            // 判断banner的数据是否为空
            if (data.getPic_list() != null && data.getPic_list().size() > 0) {
                isBanner = true;
            } else {
                isBanner = false;
            }
            // 判断新闻的数据是否为空
            if (data.getNews_list() != null && data.getNews_list().size() > 0) {
                if (isBanner) {
                    return data.getNews_list().size() + 1;
                } else {
                    return data.getNews_list().size();
                }
            }
        }
        if (isBanner) {
            return 1;
        }
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
            if(position == 1 ) {
                return 1 ;
            }
            if(position == 2 ) {
                return 2;
            }
        }
        return 3;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        View convertView;
        TextView title,ctvtop2,ctvtop3,ctvtop4,ctvtop21,ctvtop22,ctvtop23;
        ImageView img,civtop2,civtop21;
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
            }
            else {
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
            if(viewType == 1){
                civtop2 = (ImageView)view.findViewById(R.id.civtop2);
                ctvtop2 = (TextView) view.findViewById(R.id.ctvtop2);
                ctvtop3 = (TextView) view.findViewById(R.id.ctvtop3);
                ctvtop4 = (TextView) view.findViewById(R.id.ctvtop4);

            }
            if (viewType == 2) {
                civtop21 = (ImageView) view.findViewById(R.id.civtop21);
                ctvtop21 = (TextView) view.findViewById(R.id.ctvtop21);
                ctvtop22 = (TextView) view.findViewById(R.id.ctvtop22);
                ctvtop23 = (TextView) view.findViewById(R.id.ctvtop23);
            }
            if (viewType == 3) {
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

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 0) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first_zero, parent, false), viewType);
        }
        if (viewType == 1) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first_top, parent, false), viewType);
        }
        if (viewType == 2) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_firs_top2t, parent, false), viewType);
        }
        if (viewType == 3) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first, parent, false), viewType);
        }
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_first, parent, false), viewType);

        return holder;
    }

    /**绑定数据*/
    public void onBindViewHolder(final RecyclerView.ViewHolder mHolder, final int position) {
        final MyViewHolder holder = ((MyViewHolder) mHolder);

        if (getItemViewType(position) == 0) {
            List<NewsBannerBean.PicListBean> pic_list = data.getPic_list();
            setBannerVP(holder, pic_list);
        }

        if (getItemViewType(position) == 1) {
            if (data != null && data.getNews_list() != null && data.getNews_list().size() > 0) {

                int mPos = position;
                if (isBanner) {
                    mPos = position - 1;
                }



                NewsBannerBean.NewsListBean dataBean = data.getNews_list().get(mPos);
                holder.ctvtop2.setText("" + dataBean.getTitle());
                holder.ctvtop3.setText("" + dataBean.getMarks());
                holder.ctvtop3.setTextColor(Color.parseColor("#" + dataBean.getMarks_style()));
                holder.ctvtop4.setText(dataBean.getClick_cnt() + "人看过");

                GlideUtils.getInstance().loadImg(context, dataBean.getPic(), holder.civtop2);

            }
            return;
        }
        if (getItemViewType(position) == 2) {
            if (data != null && data.getNews_list() != null && data.getNews_list().size() > 0) {

                int mPos = position;
                if (isBanner) {
                    mPos = position - 1;
                }

                NewsBannerBean.NewsListBean dataBean = data.getNews_list().get(mPos);
                holder.ctvtop21.setText("" + dataBean.getTitle());
                holder.ctvtop22.setText("" + dataBean.getMarks());
                holder.ctvtop22.setTextColor(Color.parseColor("#" + dataBean.getMarks_style()));
                holder.ctvtop23.setText(dataBean.getClick_cnt() + "人看过");

                GlideUtils.getInstance().loadImg(context, dataBean.getPic(), holder.civtop21);

            }
            return;
        }
        if (getItemViewType(position) == 3) {
            if (data != null && data.getNews_list() != null && data.getNews_list().size()  > 0) {

                int mPos = position;
                if (isBanner) {
                    mPos = position - 1;
                }
                NewsBannerBean.NewsListBean dataBean = data.getNews_list().get(mPos);

                holder.title.setText("" + dataBean.getTitle());
                holder.from.setText("" + dataBean.getMarks());
                holder.from.setTextColor(Color.parseColor("#" + dataBean.getMarks_style()));
                holder.time.setText(dataBean.getClick_cnt() + "人看过");

                GlideUtils.getInstance().loadImg(context, dataBean.getPic(), holder.img);
            }
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

    public static MyHandler myHandler;
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

    private MyRunnable mRunnable = new MyRunnable();

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

        private MyViewHolder holder;
        private List<NewsBannerBean.PicListBean> bannerBean;

        public OnPChange(MyViewHolder holder, List<NewsBannerBean.PicListBean> bannerBean) {
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
    private void setBannerVP(final MyViewHolder holder, final List<NewsBannerBean.PicListBean> bannerBean) {

        datas = new ArrayList<>();
        for (int i = 0; i < bannerBean.size(); i++) {
            // 设置banner下面的标题
            holder.banner_tv.setText("" + bannerBean.get(i).getTitle());
            // 创建banner显示的图片控件
            ImageView iv = new ImageView(context);
            // 设置缩放类型，铺满控件
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            int index = i;
            GlideUtils.getInstance().LoadDefaltFitXY(context, bannerBean.get(i).getPic(), iv, R.drawable.head_img);
            // 把创建的banner图控件添加到一个集合中，后面方便调用
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

        if (datas.size() > 1 && myHandler == null) {
            myHandler = new MyHandler(FirstAdapter.this, holder.item_vp);
        }
        removeRunnable();
        if (datas.size() > 1) {
            autoCurrIndex = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % datas.size();
        }

        holder.item_vp.setAdapter(new ImageAdapter(context, datas, bannerBean));
        OnPChange onPChange = new OnPChange(holder, bannerBean);
        holder.item_vp.setOnPageChangeListener(onPChange);
        onPChange.onPageSelected(0);
        //将第一张图片设置为中间位置，可以循环右滑
        if (datas.size() > 1) {
            holder.item_vp.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % datas.size());
        }

    }
}