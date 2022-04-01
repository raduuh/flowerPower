package com.raduh.flowerapp.core.data

import androidx.lifecycle.LiveData

interface ILocalDataSource {

    suspend fun saveData(orders: List<OrderModel>)

    fun getData(): LiveData<List<OrderModel>>
}