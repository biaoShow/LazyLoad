package com.biao.lazyload.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.biao.lazyload.network.DefaultService

abstract class BaseViewModel : ViewModel() {
    var errMsg: MutableLiveData<String> = MutableLiveData<String>()//错误提示
    protected lateinit  var api: DefaultService

    init {
        initModel()
    }

    abstract fun initModel()
}