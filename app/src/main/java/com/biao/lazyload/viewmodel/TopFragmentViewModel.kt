package com.biao.lazyload.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.biao.lazyload.model.NewWorkModel
import com.biao.lazyload.Constant
import com.biao.lazyload.bean.*
import com.biao.lazyload.network.DefaultApi
import com.biao.lazyload.network.DefaultService
import com.biao.lazyload.network.RetrofitHelper
import io.reactivex.observers.DisposableObserver

class TopFragmentViewModel : BaseViewModel() {
    private val TAG = "TopFragmentViewModel"
    var newsList: MutableLiveData<MutableList<Data>> = MutableLiveData<MutableList<Data>>()
    var details: MutableLiveData<Detail> = MutableLiveData<Detail>()

    fun getNewsData(type: String, page: Int) {
        api.getNewsData(Constant.NEWS_KEY, type, page)
            .compose(RetrofitHelper.rxIoToMain())
//            .repeatWhen { it.delay(5000, TimeUnit.MILLISECONDS) }//5s轮询一次
//            .takeUntil { it.answer_new.contains("换个") }//成立则不发送下一次事件
            .subscribe(object : DisposableObserver<NewsRespBean>() {
                override fun onStart() {
                    super.onStart()
                    Log.i(TAG, "start request...")
                }

                override fun onNext(newsRespBean: NewsRespBean) {
                    if (newsRespBean.error_code == 0) {
                        newsList.postValue(newsRespBean.result.data)
                    } else {
                        errMsg.postValue(newsRespBean.reason)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "error request, e = ${e.message}")
                    errMsg.postValue(e.message)
                }

                override fun onComplete() {
                    Log.i(TAG, "complete request! ")
                }

            })
    }

    fun getNewsDetails(uniquekey: String) {
        api.getNewsDetails(Constant.NEWS_KEY, uniquekey)
            .compose(RetrofitHelper.rxIoToMain())
//            .repeatWhen { it.delay(5000, TimeUnit.MILLISECONDS) }//5s轮询一次
//            .takeUntil { it.answer_new.contains("换个") }//成立则不发送下一次事件
            .subscribe(object : DisposableObserver<DetailsRespBean>() {
                override fun onStart() {
                    super.onStart()
                    Log.i(TAG, "start request...")
                }

                override fun onNext(detailsRespBean: DetailsRespBean) {
                    if (detailsRespBean.error_code == 0) {
                        details.postValue(detailsRespBean.result.detail)
                    } else {
                        errMsg.postValue(detailsRespBean.reason)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "error request, e = ${e.message}")
                    errMsg.postValue(e.message)
                }

                override fun onComplete() {
                    Log.i(TAG, "complete request! ")
                }

            })
    }

    override fun initModel() {
        api = DefaultApi("http://v.juhe.cn").createService(DefaultService::class.java)
    }
}