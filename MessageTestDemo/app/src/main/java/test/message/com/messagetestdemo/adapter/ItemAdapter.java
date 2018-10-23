package test.message.com.messagetestdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import test.message.com.messagetestdemo.R;
import test.message.com.messagetestdemo.utils.GlideUtils;



public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    /***************************  new start **************************************/

    private String[] data;

    public ItemAdapter(String[] data, Context mContext) {
        this.data = data;
        this.context = mContext;
    }


    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public int getItemCount() {

        if (data != null && data.length > 0) {
            return data.length;
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

        public MyViewHolder(View view, int viewType) {
            super(view);
            convertView = view;

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = getAdapterPosition();
                    if (listener != null) {
                        convertView.clearAnimation();
                        listener.onItemClick(v, adapterPosition, data[adapterPosition]);
                    }
                    Toast.makeText(context, "点击了横向list的第" + adapterPosition + "个图片", Toast.LENGTH_SHORT).show();
                }
            });

            img = (ImageView) view.findViewById(R.id.tu1);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, String title);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_item, parent, false), viewType);
        return holder;

    }

    public void onBindViewHolder(final RecyclerView.ViewHolder mHolder, final int position) {
        Log.i("notify", "notify");
        final MyViewHolder holder = ((MyViewHolder) mHolder);

        GlideUtils.getInstance().loadImg(context,
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536684579066&di=604e4920d67c01c9dc825ba34aca2a88&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F9%2F54d85d785d196.jpg", holder.img);

        return;
    }


}