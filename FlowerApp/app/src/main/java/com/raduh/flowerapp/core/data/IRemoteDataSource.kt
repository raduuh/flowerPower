package com.raduh.flowerapp.core.data

interface IRemoteDataSource {

    suspend fun getOrdersFromServer() : OrderResponse<List<OrderModel>>
}