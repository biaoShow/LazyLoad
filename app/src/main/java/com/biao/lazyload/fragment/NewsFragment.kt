package com.biao.lazyload.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.biao.lazyload.AppContext
import com.biao.lazyload.R
import com.biao.lazyload.adapter.NewsCategoryAdapter
import com.biao.lazyload.adapter.NewsCategoryAdapter.ItemOnclickListener
import com.biao.lazyload.databinding.NewsFragmentLayoutBinding
import com.biao.lazyload.viewmodel.NewsFragmentViewModel

class NewsFragment : LazyFragment<NewsFragmentLayoutBinding, NewsFragmentViewModel>(),
    ViewPager.OnPageChangeListener {
    private val TAG: String = "FirstFragment"

    private lateinit var topFragment: TopFragment
    private var fragments: ArrayList<Fragment> = ArrayList()

    private lateinit var fragmentAdapter: FragmentAdapter

    private lateinit var newsCategoryAdapter: NewsCategoryAdapter

//    override fun getLayoutRes(): Int {
//        return R.layout.news_fragment_layout
//    }

    override fun onDataLoad() {
        Log.i(TAG, "加载数据-1")
//        binding.tvTipFirst.text = "加载中1..."
        AppContext.mainHandler.postDelayed(run, 2000)
    }

    private var run: Runnable = Runnable {
//        binding.tvTipFirst.text = "数据加载完成1"
        setLoadSuccess(true)
    }

    override fun onDataLoadStop() {
        Log.i(TAG, "停止加载数据-1")
        AppContext.mainHandler.removeCallbacks(run)
    }

//    override fun onClick(v: View?) {
//        when (v!!.id) {
//            R.id.tv_news_page1 -> {
//                selectMenu(0)
//            }
//            R.id.tv_news_page2 -> {
//                selectMenu(1)
//            }
//            R.id.tv_news_page3 -> {
//                selectMenu(2)
//            }
//            R.id.tv_news_page4 -> {
//                selectMenu(3)
//            }
//        }
//    }

    private fun selectMenu(index: Int) {
//        setTextColors(index)
        newsCategoryAdapter.setSelectItem(index)
        binding.vpNews.currentItem = index
    }

//    private fun setTextColors(index: Int) {
//        binding.tvNewsPage1.setTextColor(0x77000000)
//        binding.tvNewsPage2.setTextColor(0x77000000)
//        binding.tvNewsPage3.setTextColor(0x77000000)
//        binding.tvNewsPage4.setTextColor(0x77000000)
//        when (index) {
//            0 -> {
//                binding.tvNewsPage1.setTextColor(0x770000ff)
//            }
//            1 -> {
//                binding.tvNewsPage2.setTextColor(0x770000ff)
//            }
//            2 -> {
//                binding.tvNewsPage3.setTextColor(0x770000ff)
//            }
//            3 -> {
//                binding.tvNewsPage4.setTextColor(0x770000ff)
//            }
//        }
//    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        selectMenu(position)
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun initData() {
//        binding.tvNewsPage1.setOnClickListener(this)
//        binding.tvNewsPage2.setOnClickListener(this)
//        binding.tvNewsPage3.setOnClickListener(this)
//        binding.tvNewsPage4.setOnClickListener(this)

        newsCategoryAdapter = NewsCategoryAdapter(context)
        newsCategoryAdapter.setItemOnclickListener(object : ItemOnclickListener {
            override fun onClick(position: Int) {
                Log.i(TAG, "点击了，position = $position")
                selectMenu(position)
            }
        })

        binding.vpNews.setOnPageChangeListener(this)
        binding.vpNews.offscreenPageLimit = 10 //设置viewpage左右允许缓存多少个页面

        //循环创建不同类型新闻页面的fragment
        repeat(11) {//目前固定新闻分类，一共八个类型
            fragments.add(TopFragment(it))
        }
//        topFragment = TopFragment()
//        domesticFragment = DomesticFragment()
//        enterFragment = EnterFragment()
//        sportsFragment = SportsFragment()
//
//        fragments.add(topFragment)
//        fragments.add(domesticFragment)
//        fragments.add(enterFragment)
//        fragments.add(sportsFragment)

        fragmentAdapter = FragmentAdapter(parentFragmentManager, fragments)

        binding.vpNews.adapter = fragmentAdapter

        binding.rvCategory.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategory.adapter = newsCategoryAdapter

//        binding.tvNewsPage1.setTextColor(0x770000ff)

    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): NewsFragmentLayoutBinding {
        return NewsFragmentLayoutBinding.inflate(inflater, container, false)
    }

    override fun viewModelClass(): Class<NewsFragmentViewModel> {
        return NewsFragmentViewModel::class.java
    }
}