package com.raduh.flowerapp.presentaion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raduh.flowerapp.MainActivity
import com.raduh.flowerapp.R
import com.raduh.flowerapp.core.data.OrderModel
import com.raduh.flowerapp.core.data.Repository
import com.raduh.flowerapp.databinding.OrdersFragmentBinding
import com.raduh.flowerapp.framework.RetrofitService
import com.raduh.flowerapp.framework.RoomDataSource
import com.raduh.flowerapp.framework.service.RetrofitFactory

private val TAG = OrdersFragment::class.java.name

class OrdersFragment : Fragment() {

    private lateinit var ordersRecyclerView: RecyclerView
    private lateinit var binding: OrdersFragmentBinding
    private val adapter by lazy { OrdersAdapter(onItemClickListener, optionsMenuClickListener) }

    private val viewModel by viewModels<OrdersViewModel> {
        OrdersViewModelFactory(
            Repository(
                RoomDataSource(requireContext()),
                RetrofitService(RetrofitFactory())
            )
        )
    }

    private val optionsMenuClickListener = object : OptionsMenuClickListener {
        override fun onOptionsMenuClicked(position: Int, item: MenuItem) {
            handleItemMenuClicked(position, item)
        }
    }

    private val onItemClickListener = object : OnItemClickListener {
        override fun onItemClicked(orderModel: OrderModel) {
            val fragment = OrderDetailsFragment(orderModel)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container_view_fragment, fragment)
                .addToBackStack(OrderDetailsFragment::javaClass.name).commit()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.retrieveRemoteOrders()
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

        (requireActivity() as MainActivity).supportActionBar?.title =
            getString(R.string.order_title)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.ordersData.observe(viewLifecycleOwner, { orders ->
            orders.let { adapter.submitList(it) }
            Log.d(TAG, "Data received in UI layer: $orders")
        })
    }

    private fun handleItemMenuClicked(position: Int, item: MenuItem) {
        val orders = viewModel.ordersData.value
        when (item.itemId) {
            R.id.open_status -> {
                orders?.get(position)?.orderStatus = getString(R.string.new_text)
                adapter.notifyItemChanged(position)
            }
            R.id.in_progress_status -> {
                orders?.get(position)?.orderStatus = getString(R.string.pending_text)
                adapter.notifyItemChanged(position)
            }
            R.id.delivered_status -> {
                orders?.get(position)?.orderStatus = getString(R.string.delivered_text)
                adapter.notifyItemChanged(position)
            }
        }
    }
}