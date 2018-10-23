package test.message.com.messagetestdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import test.message.com.messagetestdemo.R;
import test.message.com.messagetestdemo.bean.ThreeFrgBean;
import test.message.com.messagetestdemo.bean.TwoFrgBean;
import test.message.com.messagetestdemo.utils.GlideUtils;

public class ThreeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    /***************************  new start **************************************/

    private ThreeFrgBean data;

    public ThreeAdapter(ThreeFrgBean data, Context mContext) {
        this.data = data;
        this.context = mContext;
    }


    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public int getItemCount() {

        if (data != null) {
            if (data.getData() != null && data.getData() != null && data.getData().size() > 0) {
                return data.getData().size();
            }
        }
        return 5;
    }

    @Override


    public int getItemViewType(int position) {
        String dtype = data.getData().get(position).getDtype();
        if ("dingyue".equals(data.getData().get(position).getDtype())) {
            return 1;
        }

        return 2;
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {

        View convertView;
        ImageView img,img2;
        TextView truename, create_time, dtitle, dtext,truewname2,create_time2,dtitle2,newscontent;

        public MyViewHolder(View view, int viewType) {
            super(view);
            convertView = view;
            if (viewType == 1){
                img = (ImageView) view.findViewById(R.id.img);
                truename = (TextView) view.findViewById(R.id.truename);
                create_time = (TextView) view.findViewById(R.id.create_time);
                dtitle = (TextView) view.findViewById(R.id.dtitle);
                dtext = (TextView) view.findViewById(R.id.dtext);
            }
            else{
                    convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int adapterPosition = getAdapterPosition();
                        if (listener != null) {
                            convertView.clearAnimation();
                            listener.onItemClick(v, adapterPosition);
                        }
                    }
                });
            }
            if (viewType == 2){
                img2 = (ImageView) view.findViewById(R.id.imgnews);
                truewname2 = (TextView) view.findViewById(R.id.truenamenews);
                create_time2 = (TextView) view.findViewById(R.id.create_timenews);
                dtitle2 = (TextView) view.findViewById(R.id.dtitlenews);
                newscontent = (TextView) view.findViewById(R.id.newscontent);
            }





        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private TwoAdapter.OnItemClickListener listener;

    public void setOnItemClickListener(TwoAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_three, parent, false), viewType);
        }
        if (viewType == 2){
           return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.frg_threenews, parent, false), viewType);
        }
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_three, parent, false), viewType);
        return holder;
    }

    public void onBindViewHolder(final RecyclerView.ViewHolder mHolder, final int position) {
        Log.i("notify", "notify");
        final MyViewHolder holder = ((MyViewHolder) mHolder);

        // 绑定数据
        if (getItemViewType(position) == 1) {
            if (data != null && data.getData() != null && data.getData() != null) {
                List<ThreeFrgBean.DataBean> feed = data.getData();

                holder.truename.setText("" + feed.get(position).getTruename());
                holder.create_time.setText("" + feed.get(position).getCreate_time());
                holder.dtitle.setText("" + feed.get(position).getDtitle());
                holder.dtext.setText("" + feed.get(position).getDtext());

                GlideUtils.getInstance().loadImg(context, feed.get(position).getIcon(), holder.img);

            }
            return;
        }
        if (getItemViewType(position) == 2) {
            if (data != null && data.getData() != null && data.getData() != null) {
                List<ThreeFrgBean.DataBean> feed = data.getData();

                holder.truewname2.setText("" + feed.get(position).getTruename());
                holder.create_time2.setText("" + feed.get(position).getCreate_time());
                holder.dtitle2.setText("" + feed.get(position).getDtitle());
                holder.newscontent.setText("" + feed.get(position).getDtext());

                GlideUtils.getInstance().loadImg(context, feed.get(position).getIcon(), holder.img2);

            }
            return;
        }
    }
}

