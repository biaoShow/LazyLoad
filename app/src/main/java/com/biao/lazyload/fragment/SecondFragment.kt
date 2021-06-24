package com.biao.lazyload.fragment

import android.util.Log
import android.view.View
import android.widget.TextView
import com.biao.lazyload.MyApplication
import com.biao.lazyload.R

class SecondFragment : LazyFragment() {
    private val TAG: String = "SecondFragment"
    private lateinit var tvTipSecond: TextView

    override fun initView(view: View) {
        tvTipSecond = view.findViewById(R.id.tv_tip_second)
    }

    override fun getLayoutRes(): Int {
        return R.layout.second_fragment_layout
    }

    override fun onDataLoad() {
        Log.i(TAG, "加载数据-2")
        tvTipSecond.text = "加载中..."
        MyApplication.mainHandler.postDelayed(run, 2000)
    }

    private var run: Runnable = Runnable {
        tvTipSecond.text = "数据加载完成"
        setLoadSuccess(true)
    }

    override fun onDataLoadStop() {
        Log.i(TAG, "停止加载数据-2")
        MyApplication.mainHandler.removeCallbacks(run)
    }

}