package com.raduh.flowerapp.core.data

import android.util.Log
import com.raduh.flowerapp.core.data.ILocalDataSource
import com.raduh.flowerapp.core.data.IRemoteDataSource

private val TAG = Repository::class.java.name

class Repository(
    private val localDataSource: ILocalDataSource,
    private val remoteDataSource: IRemoteDataSource
) {

    val ordersData = localDataSource.getData()

    suspend fun retrieveRemoteOrders(): OrderResponse<List<OrderModel>> {
        Log.d(TAG, "Trigger call to server")
        val orderResponse = remoteDataSource.getOrdersFromServer()
        if (orderResponse is OrderResponse.Success) {
            localDataSource.saveData(orderResponse.value)
        }
        return orderResponse
    }
}