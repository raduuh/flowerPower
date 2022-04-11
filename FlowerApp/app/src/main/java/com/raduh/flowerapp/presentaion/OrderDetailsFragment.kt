package com.raduh.flowerapp.presentaion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.raduh.flowerapp.MainActivity
import com.raduh.flowerapp.R
import com.raduh.flowerapp.core.data.OrderModel
import com.raduh.flowerapp.databinding.OrderDetailsFragmentBinding

class OrderDetailsFragment(private val orderModel: OrderModel) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = OrderDetailsFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.orderModel = orderModel

        (requireActivity() as MainActivity).supportActionBar?.title =
            getString(R.string.order_details_title)

        binding.actionButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        return binding.root
    }
}