package com.biao.lazyload

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.biao.lazyload.fragment.*

class MainActivity : AppCompatActivity(), View.OnClickListener, ViewPager.OnPageChangeListener {
    private val TAG: String = "MainActivity"

    private lateinit var vpMain: ViewPager
    private lateinit var firstFragment: FirstFragment
    private lateinit var secondFragment: SecondFragment
    private lateinit var thirdFragment: ThirdFragment
    private lateinit var fourthFragment: FourthFragment
    private var fragments: ArrayList<Fragment> = ArrayList()
    private lateinit var fragmentAdapter: FragmentAdapter
    private lateinit var tvPage1: TextView
    private lateinit var tvPage2: TextView
    private lateinit var tvPage3: TextView
    private lateinit var tvPage4: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
    }

    private fun initView() {
        vpMain = findViewById(R.id.vp_main)
        vpMain.setOnPageChangeListener(this)
        tvPage1 = findViewById(R.id.tv_page1)
        tvPage1.setOnClickListener(this)
        tvPage2 = findViewById(R.id.tv_page2)
        tvPage2.setOnClickListener(this)
        tvPage3 = findViewById(R.id.tv_page3)
        tvPage3.setOnClickListener(this)
        tvPage4 = findViewById(R.id.tv_page4)
        tvPage4.setOnClickListener(this)
    }

    private fun initData() {
        firstFragment = FirstFragment()
        secondFragment = SecondFragment()
        secondFragment.setAgainLoad(true)//设置每次进入重新加载
        thirdFragment = ThirdFragment()
        fourthFragment = FourthFragment()
        fourthFragment.setAgainLoad(true)//设置每次进入重新加载
        fragments.add(firstFragment)
        fragments.add(secondFragment)
        fragments.add(thirdFragment)
        fragments.add(fourthFragment)

        fragmentAdapter = FragmentAdapter(supportFragmentManager, fragments)

        vpMain.adapter = fragmentAdapter

        tvPage1.setTextColor(0x770000ff)
    }

    private fun setTextColors(index: Int) {
        tvPage1.setTextColor(0x77000000)
        tvPage2.setTextColor(0x77000000)
        tvPage3.setTextColor(0x77000000)
        tvPage4.setTextColor(0x77000000)
        when (index) {
            0 -> {
                tvPage1.setTextColor(0x770000ff)
            }
            1 -> {
                tvPage2.setTextColor(0x770000ff)
            }
            2 -> {
                tvPage3.setTextColor(0x770000ff)
            }
            3 -> {
                tvPage4.setTextColor(0x770000ff)
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
        vpMain.currentItem = index
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        selectMenu(position)
    }

    override fun onPageScrollStateChanged(state: Int) {

    }
}