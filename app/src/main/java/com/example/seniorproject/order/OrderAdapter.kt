package com.example.seniorproject.order


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.seniorproject.databinding.ViewOrderItemBinding
import com.example.seniorproject.network.productDtos.ProductInOrder


class OrderAdapter() :ListAdapter<ProductInOrder,
        OrderAdapter.OrderViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderAdapter.OrderViewHolder {
        return OrderViewHolder(
            ViewOrderItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(
        holder: OrderAdapter.OrderViewHolder,
        position: Int
    ) {
        val product = getItem(position)
        holder.bind(product)
    }
    companion object DiffCallback : DiffUtil.ItemCallback<ProductInOrder>() {
        override fun areItemsTheSame(oldItem: ProductInOrder, newItem: ProductInOrder): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ProductInOrder, newItem: ProductInOrder): Boolean {
            return oldItem.product.id == newItem.product.id
        }
    }
    class OrderViewHolder(private var binding:
                            ViewOrderItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductInOrder) {
            binding.product = product
            binding.executePendingBindings()
        }
    }



}