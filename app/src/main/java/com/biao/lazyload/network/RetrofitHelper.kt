package com.biao.lazyload.network

import com.biao.lazyload.BuildConfig
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class RetrofitHelper(var baseUrl: String) {
    companion object {
        const val CONNECT_TIME_OUT: Long = 12
        const val DEFAULT_TIME_OUT: Long = 12

        //compose简化线程切换
        fun <T> rxIoToMain(): ObservableTransformer<T, T> {
            return ObservableTransformer { upstream ->
                upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            }
        }
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    private fun okHttpClient(): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
        builder.writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
        builder.readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
        builder.retryOnConnectionFailure(true)//错误重连
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(interceptor)
        }
        builder.addInterceptor(headerInterceptor())//头信息
        return builder.build()
    }

    //设置头信息
    abstract fun headerInterceptor(): Interceptor
}