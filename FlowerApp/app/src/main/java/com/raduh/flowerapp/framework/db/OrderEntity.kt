package com.raduh.flowerapp.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raduh.flowerapp.core.data.OrderModel

@Entity(tableName = "Orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    @ColumnInfo(name = "orders_list")
    val orders: List<OrderModel>
)

