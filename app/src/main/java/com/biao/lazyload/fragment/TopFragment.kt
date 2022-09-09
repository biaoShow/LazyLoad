package com.biao.lazyload.fragment

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.biao.lazyload.AppContext
import com.biao.lazyload.Constant
import com.biao.lazyload.DetailActivity
import com.biao.lazyload.R
import com.biao.lazyload.adapter.NewsListAdapter
import com.biao.lazyload.databinding.TopFragmentLayoutBinding
import com.biao.lazyload.viewmodel.TopFragmentViewModel


class TopFragment(private val newCategoryNum: Int) :
    LazyFragment<TopFragmentLayoutBinding, TopFragmentViewModel>() {
    private val TAG: String = "FourthFragment"
    private var newsType = "top"
    private lateinit var newsListAdapter: NewsListAdapter
//    private lateinit var tvTipFourth: TextView
//
//    override fun initView(view: View) {
//        tvTipFourth = view.findViewById(R.id.tv_tip_top)
//        tvTipFourth.setOnClickListener {
//            var intent = Intent(context, MainActivity2::class.java)
//            startActivity(intent)
//        }
//    }

//    override fun getLayoutRes(): Int {
//        return R.layout.top_fragment_layout
//    }

    override fun onDataLoad() {
        Log.i(TAG, "加载数据-top,newCategoryNum = $newCategoryNum")
        binding.tvTipTop.text = "加载中top...$newCategoryNum"
        AppContext.mainHandler.post(run)
    }

    private var run: Runnable = Runnable {
        viewModel.getNewsData(newsType, 1)//请求网络获取新闻数据
        binding.tvTipTop.text = "数据加载完成top,$newCategoryNum"
        setLoadSuccess(true)
    }

    override fun onDataLoadStop() {
        Log.i(TAG, "停止加载数据-top,$newCategoryNum")
        AppContext.mainHandler.removeCallbacks(run)
    }

    override fun initData() {
        when (newCategoryNum) {
            0 -> {
                newsType = Constant.TOP
            }
            1 -> {
                newsType = Constant.GUONEI
            }
            2 -> {
                newsType = Constant.GUOJI
            }
            3 -> {
                newsType = Constant.YULE
            }
            4 -> {
                newsType = Constant.TIYU
            }
            5 -> {
                newsType = Constant.JUNSHI
            }
            6 -> {
                newsType = Constant.KEJI
            }
            7 -> {
                newsType = Constant.CAIJING
            }
            8 -> {
                newsType = Constant.YOUXI
            }
            9 -> {
                newsType = Constant.QICHE
            }
            10 -> {
                newsType = Constant.JIANKANG
            }
        }
        newsListAdapter = NewsListAdapter(context)
        newsListAdapter.setItemOnclickListener(object : NewsListAdapter.ItemOnclickListener {
            override fun onClick(uniquekey: String) {
//                viewModel.getNewsDetails(uniquekey)
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("uniquekey", uniquekey)
                startActivity(intent)
            }
        })

        binding.rvNews.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvNews.adapter = newsListAdapter

        viewModel.errMsg.observe(this, {
            binding.rvNews.visibility = View.GONE
            binding.tvTipTop.text = viewModel.errMsg.value
        })
        viewModel.newsList.observe(this, {
            binding.tvTipTop.visibility = View.GONE
            newsListAdapter.addNewsList(viewModel.newsList.value!!)
        })
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): TopFragmentLayoutBinding {
        return TopFragmentLayoutBinding.inflate(inflater, container, false)
    }

    override fun viewModelClass(): Class<TopFragmentViewModel> {
        return TopFragmentViewModel::class.java
    }
}