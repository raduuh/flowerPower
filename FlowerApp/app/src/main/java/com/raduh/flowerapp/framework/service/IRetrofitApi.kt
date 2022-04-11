package com.raduh.flowerapp.framework.service

interface IRetrofitApi {
    fun <T : Any> getInstance(className: Class<T>): T
}