package jp.bizloco.kotlinexample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by PC on 11/23/2016.
 */

public class RecyclerViewAdapter extends BaseAdapter {

    /**
     * click item quote interface
     */
    public interface ItemClickListener {
        void onClickItem(int position);
    }

    private ItemClickListener mListener;
    private List<String> list;
    private Context mContext;

    public RecyclerViewAdapter(Context context, List<String> array, ItemClickListener listener) {
        super(context);
        this.mContext = context;
        this.list = array;
        this.mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindImageHolder((ImageHolder) holder, position);
    }

    @SuppressLint("CheckResult")
    private void bindImageHolder(ImageHolder imageHolder, int position) {
        if (list.get(position) !=null){
            imageHolder.mImgItem.layout(0, 0, 0, 0);
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.loading_photo);
            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
                    .load(list.get(position).trim())
                    .into(imageHolder.mImgItem);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mImgItem;

        private ImageHolder(View itemView) {
            super(itemView);
            mImgItem = itemView.findViewById(R.id.mImgItem);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onClickItem(getLayoutPosition());
            }
        }
    }
}
