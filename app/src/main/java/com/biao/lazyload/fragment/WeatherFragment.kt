package com.biao.lazyload.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.biao.lazyload.AppContext
import com.biao.lazyload.databinding.WeatherFragmentLayoutBinding
import com.biao.lazyload.viewmodel.WeatherFragmentViewModel

class WeatherFragment : LazyFragment<WeatherFragmentLayoutBinding, WeatherFragmentViewModel>() {
    private val TAG: String = "ThirdFragment"
    private var webView: WebView? = null
//    private lateinit var tvTipThird: TextView

//    override fun initView(view: View) {
//        tvTipThird = view.findViewById(R.id.tv_tip_third)
//    }

//    override fun getLayoutRes(): Int {
//        return R.layout.weather_fragment_layout
//    }

    override fun onDataLoad() {
        Log.i(TAG, "加载数据-3")
        binding.tvTipThird.text = "加载中3..."
        AppContext.mainHandler.postDelayed(run, 2000)
    }

    private var run: Runnable = Runnable {
        binding.tvTipThird.text = "数据加载完成3"
        binding.tvTipThird.visibility = View.GONE
        setLoadSuccess(true)
    }


    override fun onDataLoadStop() {
        Log.i(TAG, "停止加载数据-3")
        AppContext.mainHandler.removeCallbacks(run)
    }

    override fun initData() {
        webView = WebView(requireContext())
        val webSettings: WebSettings = webView!!.getSettings()
        // 设置与Js交互的权限
        webSettings.javaScriptEnabled = true
        // 设置允许JS弹窗
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        //跨域
        webSettings.allowUniversalAccessFromFileURLs = true
        //JS弹窗相关
        webView!!.webChromeClient = WebChromeClient()
        webView!!.webViewClient = WebViewClient()
        binding.flWeather.addView(webView)

    }

    override fun onResume() {
        super.onResume()
        webView!!.loadUrl("https://www.qweather.com/weather/xiangzhou-101280704.html")
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): WeatherFragmentLayoutBinding {
        return WeatherFragmentLayoutBinding.inflate(inflater, container, false)
    }

    override fun viewModelClass(): Class<WeatherFragmentViewModel> {
        return WeatherFragmentViewModel::class.java
    }

    override fun onDestroy() {
        super.onDestroy()
        webView = null
    }
}