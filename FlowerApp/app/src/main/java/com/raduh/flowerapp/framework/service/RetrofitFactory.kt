package com.raduh.flowerapp.framework.service

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TAG = "RetrofitFactory"
private const val TIMEOUT = 30L
private const val HOST = "http://demo3114474.mockable.io/"

class RetrofitFactory : IRetrofitApi {

    private val loggingInterceptor =
        HttpLoggingInterceptor { message -> Log.d(TAG, "OKHttp - $message") }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    override fun <T : Any> getInstance(className: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(HOST)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(className)
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
    }
}