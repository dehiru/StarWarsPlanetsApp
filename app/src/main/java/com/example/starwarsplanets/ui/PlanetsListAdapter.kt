package com.example.starwarsplanets.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsplanets.databinding.ListViewItemBinding
import com.example.starwarsplanets.domain.Planet

class PlanetsListAdapter(val clickListener: PlanetsListener) :
    ListAdapter<Planet, PlanetsListAdapter.PlanetsViewHolder>(DiffCallback){

    class PlanetsViewHolder(
        var binding: ListViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: PlanetsListener, planet: Planet) {
            binding.planet = planet
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Planet>() {

        override fun areItemsTheSame(oldItem: Planet, newItem: Planet): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Planet, newItem: Planet): Boolean {
            return oldItem.rotationPeriod == newItem.rotationPeriod && oldItem.orbitalPeriod == newItem.orbitalPeriod
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PlanetsViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PlanetsViewHolder, position: Int) {
        val planet = getItem(position)
        holder.bind(clickListener, planet)
    }

}

class PlanetsListener(val clickListener: (planet: Planet) -> Unit) {
    fun onClick(planet: Planet) = clickListener(planet)
}