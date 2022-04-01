package com.raduh.flowerapp.core.data

import com.google.gson.annotations.SerializedName

data class OrderModel(
    val id: String,
    val price: String,
    val description: String,
    @SerializedName("deliver_to")
    val deliverTo: String
)