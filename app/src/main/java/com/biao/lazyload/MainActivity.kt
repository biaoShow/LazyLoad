package com.biao.lazyload

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.biao.lazyload.viewmodel.MainViewModel
import com.biao.lazyload.databinding.ActivityMainBinding
import com.biao.lazyload.fragment.*

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), View.OnClickListener,
    ViewPager.OnPageChangeListener {
    private val TAG: String = "MainActivity"

    private lateinit var newsFragment: NewsFragment
    private lateinit var vedioFragment: VedioFragment
    private lateinit var weatherFragment: WeatherFragment
    private lateinit var userFragment: UserFragment
    private var fragments: ArrayList<Fragment> = ArrayList()
    private lateinit var fragmentAdapter: FragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        initView()
//        initData()
    }

//    private fun initView() {
//        vpMain = findViewById(R.id.vp_main)
//        vpMain.setOnPageChangeListener(this)
//        tvPage1 = findViewById(R.id.tv_page1)
//        tvPage1.setOnClickListener(this)
//        tvPage2 = findViewById(R.id.tv_page2)
//        tvPage2.setOnClickListener(this)
//        tvPage3 = findViewById(R.id.tv_page3)
//        tvPage3.setOnClickListener(this)
//        tvPage4 = findViewById(R.id.tv_page4)
//        tvPage4.setOnClickListener(this)
//    }

    override fun initData() {
        binding.tvPage1.setOnClickListener(this)
        binding.tvPage2.setOnClickListener(this)
        binding.tvPage3.setOnClickListener(this)
        binding.tvPage4.setOnClickListener(this)

        binding.vpMain.setOnPageChangeListener(this)
        binding.vpMain.offscreenPageLimit = 3 //设置viewpage左右允许缓存多少个页面

        newsFragment = NewsFragment()
        vedioFragment = VedioFragment()
        vedioFragment.setAgainLoad(true)//设置每次进入重新加载
        weatherFragment = WeatherFragment()
        userFragment = UserFragment()
        userFragment.setAgainLoad(true)//设置每次进入重新加载
        fragments.add(newsFragment)
        fragments.add(vedioFragment)
        fragments.add(weatherFragment)
        fragments.add(userFragment)

        fragmentAdapter = FragmentAdapter(supportFragmentManager, fragments)

        binding.vpMain.adapter = fragmentAdapter

        binding.tvPage1.setTextColor(0x770000ff)
        binding.titleBar.ivBack.visibility = View.INVISIBLE
        binding.titleBar.ivSearch.visibility = View.INVISIBLE
        binding.titleBar.tvTitle.text = "新闻"
    }

    private fun setTextColors(index: Int) {
        binding.tvPage1.setTextColor(0x77000000)
        binding.tvPage2.setTextColor(0x77000000)
        binding.tvPage3.setTextColor(0x77000000)
        binding.tvPage4.setTextColor(0x77000000)
        when (index) {
            0 -> {
                binding.tvPage1.setTextColor(0x770000ff)
                binding.titleBar.tvTitle.text = "新闻"
                binding.titleBar.ivSearch.visibility = View.INVISIBLE
            }
            1 -> {
                binding.tvPage2.setTextColor(0x770000ff)
                binding.titleBar.tvTitle.text = "视频"
                binding.titleBar.ivSearch.visibility = View.INVISIBLE
            }
            2 -> {
                binding.tvPage3.setTextColor(0x770000ff)
                binding.titleBar.ivSearch.visibility = View.INVISIBLE
                binding.titleBar.ivSearch.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(v: View?) {

                    }
                })
                binding.titleBar.tvTitle.text = "天气"
            }
            3 -> {
                binding.tvPage4.setTextColor(0x770000ff)
                binding.titleBar.tvTitle.text = "我的"
                binding.titleBar.ivSearch.visibility = View.INVISIBLE
            }
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tv_page1 -> {
                selectMenu(0)
            }
            R.id.tv_page2 -> {
                selectMenu(1)
            }
            R.id.tv_page3 -> {
                selectMenu(2)
            }
            R.id.tv_page4 -> {
                selectMenu(3)
            }
        }
    }

    private fun selectMenu(index: Int) {
        setTextColors(index)
        binding.vpMain.currentItem = index
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        selectMenu(position)
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun initBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun viewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }
}