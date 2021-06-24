package com.biao.lazyload.fragment

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.TextView
import com.biao.lazyload.MainActivity2
import com.biao.lazyload.MyApplication
import com.biao.lazyload.R

class FourthFragment : LazyFragment() {
    private val TAG: String = "FourthFragment"
    private lateinit var tvTipFourth: TextView

    override fun initView(view: View) {
        tvTipFourth = view.findViewById(R.id.tv_tip_fourth)
        tvTipFourth.setOnClickListener {
            var intent = Intent(context, MainActivity2::class.java)
            startActivity(intent)
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.fourth_fragment_layout
    }

    override fun onDataLoad() {
        Log.i(TAG, "加载数据-4")
        tvTipFourth.text = "加载中..."
        MyApplication.mainHandler.postDelayed(run, 2000)
    }

    private var run: Runnable = Runnable {
        tvTipFourth.text = "数据加载完成"
        setLoadSuccess(true)
    }

    override fun onDataLoadStop() {
        Log.i(TAG, "停止加载数据-4")
        MyApplication.mainHandler.removeCallbacks(run)
    }
}