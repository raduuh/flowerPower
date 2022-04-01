package com.raduh.flowerapp.framework

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.raduh.flowerapp.core.data.IRemoteDataSource
import com.raduh.flowerapp.core.data.OrderModel
import kotlinx.coroutines.delay

class RetrofitService : IRemoteDataSource {
    override suspend fun getOrdersFromServer(): List<OrderModel> {
        delay(2000)
        val string = "[{\n" +
                "\t\t\"id\": 20,\n" +
                "\t\t\"description\": \"Red roases\",\n" +
                "\t\t\"price\": 17,\n" +
                "\t\t\"deliver_to\": \"Bianca\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": 21,\n" +
                "\t\t\"description\": \"Red tulips\",\n" +
                "\t\t\"price\": 7,\n" +
                "\t\t\"deliver_to\": \"Ioana\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": 243,\n" +
                "\t\t\"description\": \"Red flowers\",\n" +
                "\t\t\"price\": 1,\n" +
                "\t\t\"deliver_to\": \"Ionela\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": 22,\n" +
                "\t\t\"description\": \"Red onions\",\n" +
                "\t\t\"price\": 127,\n" +
                "\t\t\"deliver_to\": \"Cristina\"\n" +
                "\t}\n" +
                "]"
        val itemType = object : TypeToken<List<OrderModel>>() {}.type
        return Gson().fromJson(string, itemType)
    }
}