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

private val TAG = OrdersFragment::class.java.name
class OrdersFragment : Fragment() {

    private val viewModel by viewModels<OrdersViewModel> {
        OrdersViewModelFactory(Repository(RoomDataSource(requireContext()), RetrofitService()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.orders_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.ordersData.observe(viewLifecycleOwner, {
            Log.d(TAG,"Data received in UI layer: $it")
        })
        viewModel.retrieveRemoteOrders()
    }
}