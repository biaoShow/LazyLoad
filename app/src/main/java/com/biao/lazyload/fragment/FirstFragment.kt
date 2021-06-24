package com.biao.lazyload.fragment

import android.util.Log
import android.view.View
import android.widget.TextView
import com.biao.lazyload.MyApplication
import com.biao.lazyload.R

class FirstFragment : LazyFragment() {
    private val TAG: String = "FirstFragment"
    private lateinit var tvTipFirst: TextView

    override fun initView(view: View) {
        tvTipFirst = view.findViewById(R.id.tv_tip_first)
    }

    override fun getLayoutRes(): Int {
        return R.layout.first_fragment_layout
    }

    override fun onDataLoad() {
        Log.i(TAG, "加载数据-1")
        tvTipFirst.text = "加载中..."
        MyApplication.mainHandler.postDelayed(run, 2000)
    }

    private var run: Runnable = Runnable {
        tvTipFirst.text = "数据加载完成"
        setLoadSuccess(true)
    }

    override fun onDataLoadStop() {
        Log.i(TAG, "停止加载数据-1")
        MyApplication.mainHandler.removeCallbacks(run)
    }
}