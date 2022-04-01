package com.raduh.flowerapp.framework

import android.util.Log
import com.raduh.flowerapp.core.data.IRemoteDataSource
import com.raduh.flowerapp.core.data.OrderModel
import com.raduh.flowerapp.core.data.OrderResponse
import com.raduh.flowerapp.framework.service.IOrderService
import com.raduh.flowerapp.framework.service.IRetrofitApi

private val TAG = RetrofitService::class.java.name

class RetrofitService(retrofitApi: IRetrofitApi) : IRemoteDataSource {

    private val orderService = retrofitApi.getInstance(IOrderService::class.java)
    override suspend fun getOrdersFromServer(): OrderResponse<List<OrderModel>> {
        val response = orderService.getOrders()
        if (response.isSuccessful) {
            response.body().let {
                Log.d(TAG, "Data received: ${response.body()}")
                return OrderResponse.Success(response.body()!!)
            }
        } else {
            Log.d(
                TAG,
                "Something went wrong -> code:${response.code()} message: ${response.message()}"
            )
            return OrderResponse.Failure("Request error -> code: ${response.code()}")
        }
    }
}