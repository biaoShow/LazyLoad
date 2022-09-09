package com.biao.lazyload.fragment

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.biao.lazyload.AppContext
import com.biao.lazyload.VideoActivity
import com.biao.lazyload.adapter.VideoListAdapter
import com.biao.lazyload.databinding.VedioFragmentLayoutBinding
import com.biao.lazyload.viewmodel.VedioFragmentViewModel

class VedioFragment : LazyFragment<VedioFragmentLayoutBinding, VedioFragmentViewModel>() {
    private val TAG: String = "SecondFragment"

    private lateinit var videoListAdapter: VideoListAdapter
//    private lateinit var tvTipSecond: TextView

//    override fun initView(view: View) {
//        tvTipSecond = view.findViewById(R.id.tv_tip_second)
//    }

//    override fun getLayoutRes(): Int {
//        return R.layout.vedio_fragment_layout
//    }

    override fun onDataLoad() {
        Log.i(TAG, "加载数据-2")
        binding.tvTipSecond.text = "加载中2..."
        AppContext.mainHandler.postDelayed(run, 2000)
    }

    private var run: Runnable = Runnable {
        binding.tvTipSecond.text = "数据加载完成2"
        binding.tvTipSecond.visibility = View.GONE
        setLoadSuccess(true)
    }

    override fun onDataLoadStop() {
        Log.i(TAG, "停止加载数据-2")
        AppContext.mainHandler.removeCallbacks(run)
    }

    override fun initData() {
        videoListAdapter = VideoListAdapter(context)
        videoListAdapter.setItemOnclickListener(object : VideoListAdapter.ItemOnclickListener {
            override fun onClick(url: String) {
                val intent = Intent(context, VideoActivity::class.java)
                intent.putExtra("url", url)
                startActivity(intent)
            }
        })

        binding.rvNewsVideoList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvNewsVideoList.adapter = videoListAdapter
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): VedioFragmentLayoutBinding {
        return VedioFragmentLayoutBinding.inflate(inflater, container, false)
    }

    override fun viewModelClass(): Class<VedioFragmentViewModel> {
        return VedioFragmentViewModel::class.java
    }

}