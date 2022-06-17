package com.example.starwarsplanets.ui

import android.app.Application
import androidx.lifecycle.*
import com.example.starwarsplanets.database.getDatabase
import com.example.starwarsplanets.domain.Planet
import com.example.starwarsplanets.repository.PlanetsRepository
import kotlinx.coroutines.launch

enum class PlanetsApiStatus {LOADING, ERROR, DONE}

class PlanetsViewModel(application: Application) : AndroidViewModel(application) {

    private val planetsRepository = PlanetsRepository(getDatabase(application))

    val planets = planetsRepository.planets

    private val _planet = MutableLiveData<Planet>()
    val planet: LiveData<Planet> = _planet

    private val _status = MutableLiveData<PlanetsApiStatus>()
    val status: LiveData<PlanetsApiStatus> =_status

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            _status.value = PlanetsApiStatus.LOADING
            try {
                planetsRepository.refreshPlanets()
                _status.value = PlanetsApiStatus.DONE
            } catch (e: Exception) {
                if (planets.value.isNullOrEmpty()) {
                    _status.value = PlanetsApiStatus.ERROR
                }
            }
        }
    }

    fun onPlanetClicked(planet: Planet) {
        _planet.value = planet
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T: ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PlanetsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PlanetsViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}