package com.biao.lazyload.model

import android.util.Log
import com.biao.lazyload.bean.NewsRequestBean
import com.biao.lazyload.bean.NewsRespBean
import com.biao.lazyload.Constant
import com.biao.lazyload.network.DefaultApi
import com.biao.lazyload.network.DefaultService
import com.biao.lazyload.network.RetrofitHelper
import io.reactivex.observers.DisposableObserver

object NewWorkModel {
//    public const val TAG = "NewWorkModel";
//    public fun getNewsData() {
//        val newsRequestBean = NewsRequestBean(Constant.NEWS_KEY, "top", 1)
//        val newsData = DefaultApi.getInstance().createService(DefaultService::class.java)
//            .getNewsData(newsRequestBean)
//        newsData.compose(RetrofitHelper.rxIoToMain())
////            .repeatWhen { it.delay(5000, TimeUnit.MILLISECONDS) }//5s轮询一次
////            .takeUntil { it.answer_new.contains("换个") }//成立则不发送下一次事件
//            .subscribe(object : DisposableObserver<NewsRespBean>() {
//                override fun onStart() {
//                    super.onStart()
//                    Log.i(TAG, "start request...")
//                }
//
//                override fun onNext(newsRespBean: NewsRespBean) {
//                    if (newsRespBean.error_code == 0) {
//
//                    } else {
//
//                    }
//                }
//
//                override fun onError(e: Throwable) {
//                    Log.e(TAG, "error request, e = ${e.message}")
//                }
//
//                override fun onComplete() {
//                    Log.i(TAG, "complete request! ")
//                }
//
//            })
//    }
}