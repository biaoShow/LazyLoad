package com.biao.lazyload

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.multidex.MultiDex
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AppContext : Application() {
    companion object {
        val mainHandler: Handler = Handler(Looper.getMainLooper())
        val bgHandler: ExecutorService = Executors.newCachedThreadPool()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        // 解决方法超过65536问题
        MultiDex.install(this);
    }
}