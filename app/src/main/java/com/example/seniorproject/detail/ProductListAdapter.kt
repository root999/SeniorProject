package com.example.seniorproject.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.seniorproject.databinding.ViewProductItemBinding
import com.example.seniorproject.databinding.ViewRestaurantItemBinding
import com.example.seniorproject.network.Product
import com.example.seniorproject.network.Restaurant
import com.example.seniorproject.overview.RestaurantGridAdapter
import kotlinx.android.synthetic.main.fragment_product_detail.view.*
import kotlinx.android.synthetic.main.view_product_item.view.*

class ProductListAdapter(private val onClickProductDetailsListener: OnClickProductDetailsListener
,private val onClickAddToCartListener: OnClickAddToCartListener) : ListAdapter<Product,
        ProductListAdapter.ProductViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductListAdapter.ProductViewHolder {
        return ProductViewHolder(
            ViewProductItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(
        holder: ProductListAdapter.ProductViewHolder,
        position: Int
    ) {
        val product = getItem(position)
        holder.itemView.setOnClickListener{
            onClickProductDetailsListener.onClick(product)
        }

        holder.bind(product,onClickAddToCartListener)
    }
    companion object DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }
    }
    class ProductViewHolder(private var binding:
                               ViewProductItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product,listener: OnClickAddToCartListener) {
            binding.product = product
            binding.addToCart =listener
            binding.executePendingBindings()
        }
    }
    class OnClickProductDetailsListener(val clickListener: (product: Product) -> Unit) {
        fun onClick(product:Product) = clickListener(product)
    }
    class OnClickAddToCartListener(val clickListener: (product: Product) -> Unit) {
        fun onClick(product:Product) = clickListener(product)
    }


}