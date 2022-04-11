package com.raduh.flowerapp.framework.service

import com.raduh.flowerapp.core.data.OrderModel
import retrofit2.Response
import retrofit2.http.GET

interface IOrderService {

    @GET("/orders")
    suspend fun getOrders(): Response<List<OrderModel>>
}