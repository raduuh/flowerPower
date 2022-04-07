package com.raduh.flowerapp.presentaion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raduh.flowerapp.core.data.Repository
import com.raduh.flowerapp.databinding.OrdersFragmentBinding
import com.raduh.flowerapp.framework.RetrofitService
import com.raduh.flowerapp.framework.RoomDataSource
import com.raduh.flowerapp.framework.service.RetrofitFactory

private val TAG = OrdersFragment::class.java.name

class OrdersFragment : Fragment() {

    private lateinit var ordersRecyclerView: RecyclerView
    private lateinit var binding: OrdersFragmentBinding
    private val adapter = OrdersAdapter()

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
        binding = OrdersFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = this.viewModel

        ordersRecyclerView = binding.ordersList
        ordersRecyclerView.adapter = adapter
        ordersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        ordersRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.ordersData.observe(viewLifecycleOwner, { orders ->
            orders.let { adapter.submitList(it) }
            Log.d(TAG, "Data received in UI layer: $orders")
        })

        viewModel.retrieveRemoteOrders()
    }
}