package com.biao.lazyload

import android.app.Application
import android.os.Handler
import android.os.Looper

class MyApplication : Application() {
    companion object {
        val mainHandler: Handler = Handler(Looper.getMainLooper())
    }
}