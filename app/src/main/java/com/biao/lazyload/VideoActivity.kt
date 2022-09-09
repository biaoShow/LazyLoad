package com.biao.lazyload

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.biao.lazyload.databinding.ActivityVedioBinding
import com.biao.lazyload.viewmodel.VedioViewModel

class VideoActivity : BaseActivity<ActivityVedioBinding, VedioViewModel>() {
    private var webView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_vedio)
    }

    override fun initBinding(): ActivityVedioBinding {
        return ActivityVedioBinding.inflate(layoutInflater)
    }

    override fun initData() {
        webView = WebView(this)
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
        binding.flVideo.addView(webView)

        binding.titleBar.ivSearch.visibility = View.INVISIBLE
    }

    override fun onResume() {
        super.onResume()
        webView!!.loadUrl(intent.getStringExtra("url")!!)
    }

    override fun viewModelClass(): Class<VedioViewModel> {
        return VedioViewModel::class.java
    }

    override fun onDestroy() {
        super.onDestroy()
        webView = null
    }
}