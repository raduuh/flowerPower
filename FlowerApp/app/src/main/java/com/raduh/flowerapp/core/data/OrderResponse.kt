package com.raduh.flowerapp.core.data

sealed class OrderResponse<out T> {
    data class Success<out T>(val value: T) : OrderResponse<T>()
    data class Failure(val error: String) : OrderResponse<Nothing>()
}
