package com.biao.lazyload.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.biao.lazyload.bean.Data
import com.biao.lazyload.databinding.NewsItemLayoutBinding

class NewsListAdapter(private val context: Context?) :
    RecyclerView.Adapter<NewsListAdapter.MyViewHolder>() {

    private var newsList: MutableList<Data> = mutableListOf<Data>()
    private var itemOnclickListener: ItemOnclickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            NewsItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.tvTitle.text = newsList[position].title
        if (TextUtils.isEmpty(newsList[position].thumbnail_pic_s) &&
            TextUtils.isEmpty(newsList[position].thumbnail_pic_s02) &&
            TextUtils.isEmpty(newsList[position].thumbnail_pic_s03)
        ) {
            holder.binding.llIvList.visibility = View.GONE
        } else {
            holder.binding.llIvList.visibility = View.VISIBLE
        }
        holder.binding.thumbnailPicS.load(newsList[position].thumbnail_pic_s)
        holder.binding.thumbnailPicS02.load(newsList[position].thumbnail_pic_s02)
        holder.binding.thumbnailPicS03.load(newsList[position].thumbnail_pic_s03)
        holder.binding.tvTime.text = "${newsList[position].author_name} ${newsList[position].date}"

        holder.binding.llNewsItem.setOnClickListener(View.OnClickListener {
            if (itemOnclickListener != null) itemOnclickListener!!.onClick(newsList[position].uniquekey)
        })
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun addNewsList(newList: MutableList<Data>) {
        newsList.addAll(newList)
        notifyDataSetChanged()
    }

    fun setItemOnclickListener(itemOnclickListener: ItemOnclickListener) {
        this.itemOnclickListener = itemOnclickListener
    }

    class MyViewHolder(itemView: NewsItemLayoutBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val binding: NewsItemLayoutBinding = itemView
    }

    interface ItemOnclickListener {
        fun onClick(uniquekey: String)
    }
}