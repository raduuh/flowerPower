package com.raduh.flowerapp.presentaion

import com.raduh.flowerapp.core.data.OrderModel

interface OnItemClickListener {

    fun onItemClicked(orderModel: OrderModel)
}