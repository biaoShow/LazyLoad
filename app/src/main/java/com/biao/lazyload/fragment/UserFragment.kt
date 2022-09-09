package com.biao.lazyload.fragment

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.biao.lazyload.VideoActivity
import com.biao.lazyload.AppContext
import com.biao.lazyload.R
import com.biao.lazyload.databinding.UserFragmentLayoutBinding
import com.biao.lazyload.viewmodel.UserFragmentViewModel

class UserFragment : LazyFragment<UserFragmentLayoutBinding, UserFragmentViewModel>() {
    private val TAG: String = "FourthFragment"
//    private lateinit var tvTipFourth: TextView
//
//    override fun initView(view: View) {
//        tvTipFourth = view.findViewById(R.id.tv_tip_fourth)
//
//    }

//    override fun getLayoutRes(): Int {
//        return R.layout.user_fragment_layout
//    }

    override fun onDataLoad() {
        Log.i(TAG, "加载数据-4")
        binding.tvTipFourth.text = "加载中4..."
        AppContext.mainHandler.postDelayed(run, 2000)
    }

    private var run: Runnable = Runnable {
        binding.tvTipFourth.text = "数据加载完成4"
        setLoadSuccess(true)
    }

    override fun onDataLoadStop() {
        Log.i(TAG, "停止加载数据-4")
        AppContext.mainHandler.removeCallbacks(run)
    }

    override fun initData() {
        binding.tvTipFourth.setOnClickListener {
            var intent = Intent(context, VideoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): UserFragmentLayoutBinding {
        return UserFragmentLayoutBinding.inflate(inflater, container, false)
    }

    override fun viewModelClass(): Class<UserFragmentViewModel> {
        return UserFragmentViewModel::class.java
    }
}