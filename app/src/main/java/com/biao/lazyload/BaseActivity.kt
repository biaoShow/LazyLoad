package com.biao.lazyload

import android.app.Dialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.biao.lazyload.databinding.LoadDialogLayoutBinding


abstract class BaseActivity<VB : ViewBinding, VM : ViewModel> : AppCompatActivity() {
    private val TAG = "BaseActivity"

    protected lateinit var binding: VB
    protected lateinit var viewModel: VM

    private var loadDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        hideStatusBar()
        super.onCreate(savedInstanceState)

        binding = initBinding()

        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(viewModelClass())

        initData()
    }

    /**
     * 全透状态栏
     */
    private fun hideStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) { //21表示5.0
            val window: Window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        } else if (Build.VERSION.SDK_INT >= 19) { //19表示4.4
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            //虚拟键盘也透明
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
    }

    abstract fun initBinding(): VB

    abstract fun viewModelClass(): Class<VM>

    abstract fun initData()

    protected fun showLoading() {
        if (loadDialog == null) {
            createDialog()
        }
        loadDialog!!.show()
    }

    protected fun hideLoading() {
        if (loadDialog != null && loadDialog!!.isShowing) loadDialog!!.dismiss()
    }

    private fun createDialog() {
        loadDialog = Dialog(this, R.style.Base_Theme_AppCompat_Dialog)
        val binding = LoadDialogLayoutBinding.inflate(layoutInflater)
        loadDialog!!.setContentView(binding.root)

        val window = loadDialog!!.window
        window!!.setLayout(200, 200)
    }

}