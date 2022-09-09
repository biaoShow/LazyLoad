package com.biao.lazyload

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.biao.lazyload.databinding.ActivityDetailBinding
import com.biao.lazyload.viewmodel.DetailViewModel


class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_detail)
    }

    override fun initBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun initData() {
        binding.titelBar.ivSearch.visibility = View.INVISIBLE
        webView = WebView(this)
        val webSettings: WebSettings = webView.getSettings()
        // 设置与Js交互的权限
        webSettings.javaScriptEnabled = true
        // 设置允许JS弹窗
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        //跨域
        webSettings.allowUniversalAccessFromFileURLs = true
        //JS弹窗相关
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = WebViewClient()
        binding.wvContent.addView(webView)

        viewModel.details.observe(this, {
            binding.titelBar.tvTitle.text = it.title
            binding.tvTime.text = "${it.author_name} ${it.date}"
//            binding.thumbnailPicS.load(it.thumbnail_pic_s)
//            binding.thumbnailPicS02.load(it.thumbnail_pic_s02)
//            binding.thumbnailPicS03.load(it.thumbnail_pic_s03)

            webView.loadUrl(it.url)
//            webView.loadUrl("http://toutiao.com/group/6708637225535406606/")
        })

        binding.titelBar.ivBack.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                finish()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNewsDetails(intent.getStringExtra("uniquekey")!!)
    }

    override fun viewModelClass(): Class<DetailViewModel> {
        return DetailViewModel::class.java
    }
}