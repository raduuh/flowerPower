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

    suspend fun retrieveRemoteOrders(){
        Log.d(TAG,"Trigger call to server")
        val orders = remoteDataSource.getOrdersFromServer()
        Log.d(TAG,"Received data from server -> ${orders}")
        localDataSource.saveData(orders)
    }
}