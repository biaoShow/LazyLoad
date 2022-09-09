package com.biao.lazyload.fragment

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.biao.lazyload.R
import com.biao.lazyload.databinding.LoadDialogLayoutBinding

abstract class LazyFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {
    private val LOG_TAG: String = "LazyFragment"
//    private var rootView: View? = null

    private var isViewCreate: Boolean = false //view是否已经创建
    private var isPageVisible: Boolean = false //页面是否可见
    private var isAgainLoad: Boolean = false //第二次进入是否重新加载
    private var isLoadSuccess: Boolean = false //数据是否已经加载成功

    protected lateinit var binding: VB
    protected lateinit var viewModel: VM

    private var loadDialog: Dialog? = null


    //设置每次进入是否重新加载
    fun setAgainLoad(again: Boolean) {
        isAgainLoad = again
    }

    //设置是否成功加载过，第一次进入时结合isAgainLoad判断是否需要加载
    //isAgainLoad=false时解决第一次进入没有加载完后离开，之后再回到界面时无法加载问题
    fun setLoadSuccess(loadSuccess: Boolean) {
        isLoadSuccess = loadSuccess
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isViewCreate) { //view创建之后再分发，避免分发之后对view处理报错
            dispatchUserVisibleHint(isVisibleToUser)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(LOG_TAG, "onViewCreated")
        binding = initBinding(inflater, container)
//        initView(rootView!!)
        isViewCreate = true
        if (userVisibleHint) { //如果可见则主动触发加载数据，解决第一次进入时候加载数据问题
            dispatchUserVisibleHint(true)
        }
        viewModel = ViewModelProvider(this).get(viewModelClass())
        initData()
        return binding.root
    }

    //    //初始化控件
//    abstract fun initView(view: View)
    abstract fun initData()

    //设置布局文件
//    abstract fun getLayoutRes(): Int

    abstract fun initBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    abstract fun viewModelClass():Class<VM>

    override fun onResume() {
        super.onResume()
        Log.i(LOG_TAG, "onResume")
        //界面跳转时/拉起多任务/回到桌面后回来时需要加载数据
        if (!isPageVisible && userVisibleHint) {
            dispatchUserVisibleHint(true)
        }
    }

    override fun onPause() {
        super.onPause()
        Log.i(LOG_TAG, "onPause")
        //界面跳转时/拉起多任务/回到桌面时需要停止加载
        if (isPageVisible && userVisibleHint) {
            dispatchUserVisibleHint(false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(LOG_TAG, "onDestroyView")
    }

    //数据处理接口分发
    private fun dispatchUserVisibleHint(visibleToUser: Boolean) {
        //已经成功加载过且设置为不重新加载，则直接返回部分发加载事件
        if (isLoadSuccess && !isAgainLoad) return
        if (visibleToUser && !isPageVisible) { //现在可见且之前不可见
            onDataLoad()
        } else if (!visibleToUser && isPageVisible) { //现在不可见且之前可见
            onDataLoadStop()
        }
        isPageVisible = visibleToUser
    }

    //加载数据
    open fun onDataLoad() {

    }

    //停止加载数据
    open fun onDataLoadStop() {

    }

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
        loadDialog = Dialog(requireContext(), R.style.Base_Theme_AppCompat_Dialog)
        val binding = LoadDialogLayoutBinding.inflate(layoutInflater)
        loadDialog!!.setContentView(binding.root)

        val window = loadDialog!!.window
        window!!.setLayout(200, 200)
    }
}
