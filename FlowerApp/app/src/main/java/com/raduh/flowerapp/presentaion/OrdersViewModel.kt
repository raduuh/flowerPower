package com.raduh.flowerapp.presentaion

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.raduh.flowerapp.core.data.OrderModel
import com.raduh.flowerapp.core.data.Repository
import kotlinx.coroutines.launch

class OrdersViewModel(private val repository: Repository) : ViewModel() {

    val ordersData: LiveData<List<OrderModel>> = repository.ordersData

    fun retrieveRemoteOrders() = viewModelScope.launch { repository.retrieveRemoteOrders() }
}


class OrdersViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (OrdersViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(Repository::class.java)
                .newInstance(repository)
        } else {
            throw IllegalStateException("ViewModel cannot be created")
        }
    }
}