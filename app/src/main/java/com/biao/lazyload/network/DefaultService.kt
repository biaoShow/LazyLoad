package com.biao.lazyload.network

import com.biao.lazyload.bean.DetailsReqsBean
import com.biao.lazyload.bean.NewsRequestBean
import com.biao.lazyload.bean.NewsRespBean
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface DefaultService {
    //请求新闻list
    @POST("/toutiao/index")
    fun getNewsData(@Body newsRequestBean: NewsRequestBean): Observable<NewsRespBean>

    //    @POST("v1/bot")
//    fun getData(@Body requestBean: RequestBean): Call<TestDataBean>
    //请求新闻详情
    @POST("/toutiao/content")
    fun getNewsDetails(@Body detailsReqsBean: DetailsReqsBean): Observable<DetailsReqsBean>
}