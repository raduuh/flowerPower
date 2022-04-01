package com.raduh.flowerapp.presentaion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.raduh.flowerapp.R
import com.raduh.flowerapp.core.data.Repository
import com.raduh.flowerapp.framework.RetrofitService
import com.raduh.flowerapp.framework.RoomDataSource
import com.raduh.flowerapp.framework.service.LoadingState
import com.raduh.flowerapp.framework.service.RetrofitFactory

private val TAG = OrdersFragment::class.java.name

class OrdersFragment : Fragment() {

    private val viewModel by viewModels<OrdersViewModel> {
        OrdersViewModelFactory(
            Repository(
                RoomDataSource(requireContext()),
                RetrofitService(RetrofitFactory())
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.orders_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.ordersData.observe(viewLifecycleOwner, {
            Log.d(TAG, "Data received in UI layer: $it")
        })

        viewModel.loading.observe(viewLifecycleOwner, { loadingState ->
            when (loadingState) {
                is LoadingState.Loading -> {
                    Log.d(TAG, "LOADING state")
                }
                is LoadingState.Loaded -> {
                    Log.d(TAG, "LOADED state")
                }
                is LoadingState.Failed -> {
                    Log.d(TAG, "FAILED state")
                }
            }
        })
        viewModel.retrieveRemoteOrders()
    }
}