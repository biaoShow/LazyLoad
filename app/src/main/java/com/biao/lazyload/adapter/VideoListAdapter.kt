package com.biao.lazyload.adapter

import android.content.Context
import android.os.Build
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.biao.lazyload.R
import com.biao.lazyload.bean.Data
import com.biao.lazyload.bean.VideoNewsBean
import com.biao.lazyload.databinding.NewsItemLayoutBinding
import com.biao.lazyload.databinding.VideoItemLayoutBinding
import java.time.LocalDateTime

class VideoListAdapter(private val context: Context?) :
    RecyclerView.Adapter<VideoListAdapter.MyViewHolder>() {

    private var videoNewsList = arrayOf(
        VideoNewsBean(
            "https://www.ixigua.com/7140077943341974050?logTag=516c441a2e66d89cd9f2   ",
            "【禁毒】毒瘾发作时的真实场景！珍爱生命，远离毒品"
        ),
        VideoNewsBean(
            "https://www.ixigua.com/7140068868567302691?logTag=70654eceaa7b2dd92d76",
            "美国特务机关这次栽了！国家安全局NSA窃密被中国活逮！"
        ),
        VideoNewsBean(
            "https://www.ixigua.com/6708637225535406606",
            "女子独自一人深夜约见男网友 却被困民房五小时 结果被伤害四次"
        ),
    )
    private var itemOnclickListener: ItemOnclickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            VideoItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.tvVideoTitle.text = videoNewsList[position].title
        when (position) {
            0 -> {
                holder.binding.ivVideoImage.background =
                    context!!.resources.getDrawable(R.mipmap.abc)
            }
            1 -> {
                holder.binding.ivVideoImage.background =
                    context!!.resources.getDrawable(R.mipmap.abe)
            }
            2 -> {
                holder.binding.ivVideoImage.background =
                    context!!.resources.getDrawable(R.mipmap.abd)
            }
        }
        holder.binding.tvVideoTime.text = LocalDateTime.now().toString()

        holder.binding.ivVideoImage.setOnClickListener(View.OnClickListener {
            if (itemOnclickListener != null) itemOnclickListener!!.onClick(videoNewsList[position].url)
        })
    }

    override fun getItemCount(): Int {
        return videoNewsList.size
    }

    fun setItemOnclickListener(itemOnclickListener: ItemOnclickListener) {
        this.itemOnclickListener = itemOnclickListener
    }

    class MyViewHolder(itemView: VideoItemLayoutBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val binding: VideoItemLayoutBinding = itemView
    }

    interface ItemOnclickListener {
        fun onClick(url: String)
    }
}