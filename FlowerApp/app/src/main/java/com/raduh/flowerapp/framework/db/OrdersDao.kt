package com.raduh.flowerapp.framework.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OrdersDao {

    @Query("SELECT * FROM Orders")
    fun getOrders(): LiveData<OrderEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOrders(orderEntity: OrderEntity)

}