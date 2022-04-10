package com.raduh.flowerapp.presentaion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.raduh.flowerapp.core.data.OrderModel
import com.raduh.flowerapp.databinding.OrderItemBinding


class OrdersAdapter(private val itemClickListener: OnItemClickListener) :
    ListAdapter<OrderModel, OrdersAdapter.OrdersViewHolder>(ORDERS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val binding = OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrdersViewHolder.create(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }


    class OrdersViewHolder(
        private val binding: OrderItemBinding,
        private val onItemClickListener: OnItemClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(orderModel: OrderModel) {
            binding.orderModel = orderModel
            binding.root.setOnClickListener {
                onItemClickListener.onItemClicked(orderModel)
            }
        }

        companion object {
            fun create(
                binding: OrderItemBinding,
                itemClickListener: OnItemClickListener
            ): OrdersViewHolder {
                return OrdersViewHolder(binding, itemClickListener)
            }
        }
    }

    companion object {
        private val ORDERS_COMPARATOR = object : DiffUtil.ItemCallback<OrderModel>() {
            override fun areItemsTheSame(oldItem: OrderModel, newItem: OrderModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: OrderModel, newItem: OrderModel): Boolean {
                return (oldItem.id == newItem.id &&
                        oldItem.description == newItem.description &&
                        oldItem.price == newItem.price &&
                        oldItem.deliverTo == newItem.deliverTo)
            }
        }
    }
}
