package com.example.seniorproject.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.seniorproject.databinding.ViewRestaurantItemBinding
import com.example.seniorproject.network.Restaurant

class RestaurantGridAdapter( private val onClickListener: OnClickListener) : ListAdapter<Restaurant,
        RestaurantGridAdapter.RestaurantViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantGridAdapter.RestaurantViewHolder {
        return RestaurantViewHolder(
            ViewRestaurantItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(
        holder: RestaurantGridAdapter.RestaurantViewHolder,
        position: Int
    ) {
        val restaurant = getItem(position)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(restaurant)
        }
        holder.bind(restaurant)
    }
    companion object DiffCallback : DiffUtil.ItemCallback<Restaurant>() {
        override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
           return oldItem.id == newItem.id
        }
    }
    class RestaurantViewHolder(private var binding:
                                 ViewRestaurantItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: Restaurant) {
            binding.restaurant = restaurant
            binding.executePendingBindings()
        }
    }
    class OnClickListener(val clickListener: (restaurant: Restaurant) -> Unit) {
        fun onClick(restaurant:Restaurant) = clickListener(restaurant)
    }

}