package com.raduh.flowerapp.presentaion

import androidx.lifecycle.*
import com.raduh.flowerapp.core.data.OrderModel
import com.raduh.flowerapp.core.data.OrderResponse
import com.raduh.flowerapp.core.data.Repository
import com.raduh.flowerapp.framework.service.LoadingState
import kotlinx.coroutines.launch

class OrdersViewModel(private val repository: Repository) : ViewModel() {

    val ordersData: LiveData<List<OrderModel>> = repository.ordersData

    private val _loading = MutableLiveData<LoadingState>(LoadingState.Loading)
    val loading: LiveData<LoadingState>
        get() = _loading

    fun retrieveRemoteOrders() {
        viewModelScope.launch {
            val response = repository.retrieveRemoteOrders()
            if (response is OrderResponse.Success) {
                _loading.postValue(LoadingState.Loaded)
            } else {
                _loading.postValue(LoadingState.Failed("Something went wrong when trying to get data"))
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