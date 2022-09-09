package com.biao.lazyload.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.biao.lazyload.R
import com.biao.lazyload.databinding.CategoryItemLayoutBinding

class NewsCategoryAdapter(private val context: Context?) :
    RecyclerView.Adapter<NewsCategoryAdapter.MyViewHolder>() {

    private var categoryList =
        arrayOf("头条", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "游戏", "汽车", "健康")
    private var itemOnclickListener: ItemOnclickListener? = null
    private var selectItem = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CategoryItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.tvCategory.text = categoryList[position]

        if (position == selectItem) {
            holder.binding.tvCategory.setTextColor(context!!.resources.getColor(R.color.category_select))
        } else {
            holder.binding.tvCategory.setTextColor(context!!.resources.getColor(R.color.category_unselect))
        }

        holder.binding.tvCategory.setOnClickListener(View.OnClickListener {
            if (itemOnclickListener != null) itemOnclickListener!!.onClick(position)
        })
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun setSelectItem(itemNum: Int) {
        this.selectItem = itemNum
        notifyDataSetChanged()
    }

    fun setItemOnclickListener(itemOnclickListener: ItemOnclickListener) {
        this.itemOnclickListener = itemOnclickListener
    }

    class MyViewHolder(itemView: CategoryItemLayoutBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val binding: CategoryItemLayoutBinding = itemView
    }

    interface ItemOnclickListener {
        fun onClick(position: Int)
    }
}