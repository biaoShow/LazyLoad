package com.biao.lazyload.model

class BaseModel<M : IModel> {
    lateinit var model:M

    fun initModel(){

    }
}