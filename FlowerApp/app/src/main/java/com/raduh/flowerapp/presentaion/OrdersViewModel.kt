package com.raduh.flowerapp.presentaion

import androidx.lifecycle.*
import com.raduh.flowerapp.core.data.OrderModel
import com.raduh.flowerapp.core.data.OrderResponse
import com.raduh.flowerapp.core.data.Repository
import kotlinx.coroutines.launch

class OrdersViewModel(private val repository: Repository) : ViewModel() {

    val ordersData: LiveData<List<OrderModel>> = repository.ordersData

    private val _error = MutableLiveData(false)
    val error: LiveData<Boolean>
        get() = _error

    fun retrieveRemoteOrders() {
        viewModelScope.launch {
            val response = repository.retrieveRemoteOrders()
            if (response is OrderResponse.Success) {
                _error.postValue(false)
            } else {
                _error.postValue(true)
            }
        }
    }


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