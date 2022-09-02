package com.biao.lazyload.network

import okhttp3.Interceptor

class DefaultApi(baseUrl: String) : RetrofitHelper(baseUrl) {
    //单例
    companion object {
        private const val BASE_URL: String =
            "http://bot-gateway-service-px-dialogue.stage.dm-ai.cn/"
        private var instance: DefaultApi? = null
        fun getInstance(): DefaultApi {
            if (instance == null) {
                synchronized(DefaultApi::class) {
                    if (instance == null) {
                        instance = DefaultApi(BASE_URL)
                    }
                }
            }
            return instance!!
        }
    }

    override fun headerInterceptor(): Interceptor {
        return Interceptor { chain ->
            //请求定制(添加请求头)
            val requestBuilder = chain.request().newBuilder().apply {
                //请求定制(添加请求头)
                addHeader("key", "value")
            }
            chain.proceed(requestBuilder.build())
        }
    }

    fun <T> createService(service: Class<T>?): T {
        return getRetrofit().create(service)
    }

}