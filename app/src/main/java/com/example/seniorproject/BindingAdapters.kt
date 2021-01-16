package com.example.seniorproject

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.seniorproject.detail.ProductListAdapter
import com.example.seniorproject.network.Product
import com.example.seniorproject.network.Restaurant
import com.example.seniorproject.overview.RestaurantGridAdapter
import com.example.seniorproject.overview.RestaurantStatus

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri =
            imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}
@BindingAdapter("AppApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: RestaurantStatus?) {
    when(status){
        RestaurantStatus.LOADING ->{
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        RestaurantStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        RestaurantStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<Restaurant>?) {
    val adapter = recyclerView.adapter as RestaurantGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("productData")
fun bindProductRecyclerView(recyclerView: RecyclerView,
                     data: List<Product>?) {
    val adapter = recyclerView.adapter as ProductListAdapter
    adapter.submitList(data)
}