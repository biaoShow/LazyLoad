package com.biao.lazyload.network

import com.biao.lazyload.bean.DetailsReqsBean
import com.biao.lazyload.bean.DetailsRespBean
import com.biao.lazyload.bean.NewsRequestBean
import com.biao.lazyload.bean.NewsRespBean
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface DefaultService {
    //请求新闻list
    @FormUrlEncoded
    @POST("/toutiao/index")
    fun getNewsData(
        @Field("key") key: String,
        @Field("type") type: String,
        @Field("page") page: Int
    ): Observable<NewsRespBean>

    //    @POST("v1/bot")
//    fun getData(@Body requestBean: RequestBean): Call<TestDataBean>
    //请求新闻详情
    @FormUrlEncoded
    @POST("/toutiao/content")
    fun getNewsDetails(
        @Field("key") key: String,
        @Field("uniquekey") uniquekey: String
    ): Observable<DetailsRespBean>
}