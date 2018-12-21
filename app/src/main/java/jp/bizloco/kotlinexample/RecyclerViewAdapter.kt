package jp.bizloco.kotlinexample

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_recycler_view.view.*

/**
 * Created by PC on 11/23/2016.
 */

class RecyclerViewAdapter(var mContext: Context, var mList: ArrayList<String>, var mListener: ItemClickListener) : BaseAdapter<RecyclerViewAdapter.ImageHolder>(mContext) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
        return ImageHolder(view)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        bindImageHolder(holder as ImageHolder, position)
    }

    @SuppressLint("CheckResult")
    private fun bindImageHolder(imageHolder: ImageHolder, position: Int) {
        imageHolder.mImgItem.layout(0, 0, 0, 0)
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.loading_photo)
        Glide.with(mContext)
                .setDefaultRequestOptions(requestOptions)
                .load(mList.get(position).trim())
                .into(imageHolder.mImgItem)

        imageHolder.mImgItem.setOnClickListener { mListener.onClickItem(position) }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mImgItem = itemView.mImgItem
    }
}
