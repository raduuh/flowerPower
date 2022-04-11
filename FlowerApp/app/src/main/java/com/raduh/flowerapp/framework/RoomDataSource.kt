package com.raduh.flowerapp.framework

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.raduh.flowerapp.core.data.ILocalDataSource
import com.raduh.flowerapp.core.data.OrderModel
import com.raduh.flowerapp.framework.db.DataBase
import com.raduh.flowerapp.framework.db.OrderEntity

private val TAG = RoomDataSource::class.java.name

class RoomDataSource(context: Context) : ILocalDataSource {

    private val ordersDao = DataBase.getInstance(context).ordersDao()

    override suspend fun saveData(orders: List<OrderModel>) {
        val orderEntity = OrderEntity(1, orders)
        try {
            Log.d(TAG, "Save data locally -> $orders")
            ordersDao.saveOrders(orderEntity)
        } catch (ex: Exception) {
            Log.d(TAG, "Insert failed: $ex")
        }
    }

    override fun getData(): LiveData<List<OrderModel>> {
        return ordersDao.getOrders().map { orderEntity ->
            if (orderEntity == null) {
                emptyList()
            } else {
                orderEntity.orders
            }
        }
    }
}