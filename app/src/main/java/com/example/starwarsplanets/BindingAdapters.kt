package com.example.starwarsplanets

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsplanets.network.Planet
import com.example.starwarsplanets.ui.PlanetsApiStatus
import com.example.starwarsplanets.ui.PlanetsListAdapter

/**
 * Updates the data shown in the [RecyclerView]
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Planet>?) {
    val adapter = recyclerView.adapter as PlanetsListAdapter
    adapter.submitList(data)
}

/**
 * This binding adapter displays the [PlanetsApiStatus] of the network request in an image view.
 */
@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: PlanetsApiStatus?) {
    when(status) {
        PlanetsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        PlanetsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        PlanetsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}