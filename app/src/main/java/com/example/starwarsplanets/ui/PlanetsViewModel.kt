package com.example.starwarsplanets.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsplanets.network.Planet
import com.example.starwarsplanets.network.PlanetsAPI
import kotlinx.coroutines.launch

enum class PlanetsApiStatus {LOADING, ERROR, DONE}

class PlanetsViewModel : ViewModel() {

    private val _status = MutableLiveData<PlanetsApiStatus>()
    val status: LiveData<PlanetsApiStatus> =_status

    private val _planets = MutableLiveData<List<Planet>>()
    val planets: LiveData<List<Planet>> = _planets

    private val _planet = MutableLiveData<Planet>()
    val planet: LiveData<Planet> = _planet

    fun getPlanetsList() {
        viewModelScope.launch {
            _status.value = PlanetsApiStatus.LOADING
            try {
                val planetsNetworkData = PlanetsAPI.retrofitService.getPlanets()
                _planets.value = planetsNetworkData.results
                _status.value = PlanetsApiStatus.DONE
            } catch (e: Exception) {
                _planets.value = listOf()
                _status.value = PlanetsApiStatus.ERROR
            }
        }
    }

    fun onPlanetClicked(planet: Planet) {
        _planet.value = planet
    }
}