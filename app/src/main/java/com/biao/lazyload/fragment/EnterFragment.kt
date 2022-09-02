package com.biao.lazyload.fragment

import android.util.Log
import android.view.View
import android.widget.TextView
import com.biao.lazyload.AppContext
import com.biao.lazyload.R

class EnterFragment : LazyFragment() {
    private val TAG: String = "SecondFragment"
    private lateinit var tvTipSecond: TextView

    override fun initView(view: View) {
        tvTipSecond = view.findViewById(R.id.tv_tip_enter)
    }

    override fun getLayoutRes(): Int {
        return R.layout.enter_fragment_layout
    }

    override fun onDataLoad() {
        Log.i(TAG, "加载数据-2")
        tvTipSecond.text = "加载中..."
        AppContext.mainHandler.postDelayed(run, 2000)
    }

    private var run: Runnable = Runnable {
        tvTipSecond.text = "数据加载完成"
        setLoadSuccess(true)
    }

    override fun onDataLoadStop() {
        Log.i(TAG, "停止加载数据-2")
        AppContext.mainHandler.removeCallbacks(run)
    }

}