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
import test.message.com.messagetestdemo.bean.TwoFrgBean;
import test.message.com.messagetestdemo.utils.GlideUtils;



public class TwoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    /***************************  new start **************************************/

    private TwoFrgBean data;

    public TwoAdapter(TwoFrgBean data, Context mContext) {
        this.data = data;
        this.context = mContext;
    }


    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public int getItemCount() {

        if (data != null ){
            if (data.getData() != null && data.getData()!=null && data.getData().size()>0){
                return data.getData().size();
            }
        }
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        View convertView;
        ImageView img;
        TextView title,click,subscribe;

        public MyViewHolder(View view, int viewType) {
            super(view);
            convertView = view;

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

            // 初始化布局

            img = (ImageView) view.findViewById(R.id.img);
            title = (TextView) view.findViewById(R.id.title);
            click = (TextView) view.findViewById(R.id.click);
            subscribe=(TextView) view.findViewById(R.id.subscribe);


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

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_two, parent, false), viewType);
        return holder;

    }

    public void onBindViewHolder(final RecyclerView.ViewHolder mHolder, final int position) {
        Log.i("notify", "notify");
        final MyViewHolder holder = ((MyViewHolder) mHolder);

        // 绑定数据

        if (data != null && data.getData() != null && data.getData() != null){
            List<TwoFrgBean.DataBean> feed = data.getData();

            holder.title.setText(""+ feed.get(position).getTitle());
            holder.click.setText("" + feed.get(position).getClick()+"人看过");
            holder.subscribe.setText("" + feed.get(position).getSubscribe_name());

            GlideUtils.getInstance().loadImg(context,feed.get(position).getThumb(),holder.img);

        }
        return;


    }

}