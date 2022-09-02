package com.biao.lazyload

import android.app.Application
import android.os.Handler
import android.os.Looper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AppContext : Application() {
    companion object {
        val mainHandler: Handler = Handler(Looper.getMainLooper())
        val bgHandler: ExecutorService = Executors.newCachedThreadPool()
    }
}