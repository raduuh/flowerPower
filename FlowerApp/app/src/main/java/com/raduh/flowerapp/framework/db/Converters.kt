package com.raduh.flowerapp.framework.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.raduh.flowerapp.core.data.OrderModel

class Converters {

    @TypeConverter
    fun fromStringToList(data: String): List<OrderModel> {
        val itemType = object : TypeToken<List<OrderModel>>() {}.type
        return Gson().fromJson(data, itemType)
    }

    @TypeConverter
    fun fromListToString(orders: List<OrderModel>): String {
        return Gson().toJson(orders)
    }
}