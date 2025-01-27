package com.example.seniorproject.order


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.seniorproject.databinding.ViewOrderItemBinding
import com.example.seniorproject.detail.ProductListAdapter
import com.example.seniorproject.network.Product
import com.example.seniorproject.network.Restaurant
import com.example.seniorproject.network.productDtos.ProductInOrder
import com.example.seniorproject.overview.RestaurantGridAdapter


class OrderAdapter( private val onClickListener: OrderAdapter.OnClickListener,
                    private val onClickDeleteFromCartListener: OrderAdapter.OnClickDeleteFromCartListener
) :ListAdapter<ProductInOrder,
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
        holder.itemView.setOnClickListener{
            onClickListener.onClick(product)
        }
        holder.bind(product,onClickDeleteFromCartListener)
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
        fun bind(product: ProductInOrder,listener: OrderAdapter.OnClickDeleteFromCartListener) {
            binding.product = product
            binding.deleteFromCart =listener
            binding.executePendingBindings()
        }
    }
    class OnClickListener(val clickListener: (product: ProductInOrder) -> Unit) {
        fun onClick(restaurant: ProductInOrder) = clickListener(restaurant)
    }
    class OnClickDeleteFromCartListener(val clickListener: (product: ProductInOrder) -> Unit) {
        fun onClick(product: ProductInOrder) = clickListener(product)
    }



}