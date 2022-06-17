package com.example.starwarsplanets.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.starwarsplanets.R
import com.example.starwarsplanets.databinding.FragmentPlanetsListBinding

class PlanetsListFragment : Fragment() {

    private val viewModel: PlanetsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlanetsListBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = PlanetsListAdapter(PlanetsListener { planet ->
            viewModel.onPlanetClicked(planet)
            findNavController()
                .navigate(R.id.action_planetsListFragment_to_planetsDetailFragment)
        })
        return binding.root
    }

}