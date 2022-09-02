package com.biao.lazyload.fragment

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.biao.lazyload.AppContext
import com.biao.lazyload.R

class FirstFragment : LazyFragment(), View.OnClickListener, ViewPager.OnPageChangeListener {
    private val TAG: String = "FirstFragment"
    private lateinit var tvTipFirst: TextView
    private lateinit var tvNewsPage1: TextView
    private lateinit var tvNewsPage2: TextView
    private lateinit var tvNewsPage3: TextView
    private lateinit var tvNewsPage4: TextView
    private lateinit var vpNews: ViewPager

    private lateinit var topFragment: TopFragment
    private lateinit var domesticFragment: DomesticFragment
    private lateinit var enterFragment: EnterFragment
    private lateinit var sportsFragment: SportsFragment
    private var fragments: ArrayList<Fragment> = ArrayList()

    private lateinit var fragmentAdapter: FragmentAdapter

    override fun initView(view: View) {
        tvTipFirst = view.findViewById(R.id.tv_tip_first)
        tvNewsPage1 = view.findViewById(R.id.tv_news_page1)
        tvNewsPage2 = view.findViewById(R.id.tv_news_page2)
        tvNewsPage3 = view.findViewById(R.id.tv_news_page3)
        tvNewsPage4 = view.findViewById(R.id.tv_news_page4)

        tvNewsPage1.setOnClickListener(this)
        tvNewsPage2.setOnClickListener(this)
        tvNewsPage3.setOnClickListener(this)
        tvNewsPage4.setOnClickListener(this)
        vpNews = view.findViewById(R.id.vp_news)
        vpNews.setOnPageChangeListener(this)

        topFragment = TopFragment()
        domesticFragment  = DomesticFragment()
        enterFragment = EnterFragment()
        sportsFragment = SportsFragment()

        fragments.add(topFragment)
        fragments.add(domesticFragment)
        fragments.add(enterFragment)
        fragments.add(sportsFragment)

        fragmentAdapter = FragmentAdapter(parentFragmentManager, fragments)

        vpNews.adapter = fragmentAdapter

        tvNewsPage1.setTextColor(0x770000ff)

    }

    override fun getLayoutRes(): Int {
        return R.layout.first_fragment_layout
    }

    override fun onDataLoad() {
        Log.i(TAG, "加载数据-1")
        tvTipFirst.text = "加载中..."
        AppContext.mainHandler.postDelayed(run, 2000)
    }

    private var run: Runnable = Runnable {
        tvTipFirst.text = "数据加载完成"
        setLoadSuccess(true)
    }

    override fun onDataLoadStop() {
        Log.i(TAG, "停止加载数据-1")
        AppContext.mainHandler.removeCallbacks(run)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tv_news_page1 -> {
                selectMenu(0)
            }
            R.id.tv_news_page2 -> {
                selectMenu(1)
            }
            R.id.tv_news_page3 -> {
                selectMenu(2)
            }
            R.id.tv_news_page4 -> {
                selectMenu(3)
            }
        }
    }

    private fun selectMenu(index: Int) {
        setTextColors(index)
        vpNews.currentItem = index
    }

    private fun setTextColors(index: Int) {
        tvNewsPage1.setTextColor(0x77000000)
        tvNewsPage2.setTextColor(0x77000000)
        tvNewsPage3.setTextColor(0x77000000)
        tvNewsPage4.setTextColor(0x77000000)
        when (index) {
            0 -> {
                tvNewsPage1.setTextColor(0x770000ff)
            }
            1 -> {
                tvNewsPage2.setTextColor(0x770000ff)
            }
            2 -> {
                tvNewsPage3.setTextColor(0x770000ff)
            }
            3 -> {
                tvNewsPage4.setTextColor(0x770000ff)
            }
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        selectMenu(position)
    }

    override fun onPageScrollStateChanged(state: Int) {
    }
}