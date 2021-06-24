package com.biao.lazyload.fragment

import android.util.Log
import android.view.View
import android.widget.TextView
import com.biao.lazyload.MyApplication
import com.biao.lazyload.R

class ThirdFragment : LazyFragment() {
    private val TAG: String = "ThirdFragment"
    private lateinit var tvTipThird: TextView

    override fun initView(view: View) {
        tvTipThird = view.findViewById(R.id.tv_tip_third)
    }

    override fun getLayoutRes(): Int {
        return R.layout.third_fragment_layout
    }

    override fun onDataLoad() {
        Log.i(TAG, "加载数据-3")
        tvTipThird.text = "加载中..."
        MyApplication.mainHandler.postDelayed(run, 2000)
    }

    private var run: Runnable = Runnable {
        tvTipThird.text = "数据加载完成"
        setLoadSuccess(true)
    }


    override fun onDataLoadStop() {
        Log.i(TAG, "停止加载数据-3")
        MyApplication.mainHandler.removeCallbacks(run)
    }
}