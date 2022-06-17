package com.example.starwarsplanets.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.starwarsplanets.database.PlanetsDatabase
import com.example.starwarsplanets.database.asDomainModel
import com.example.starwarsplanets.domain.Planet
import com.example.starwarsplanets.network.PlanetsAPI
import com.example.starwarsplanets.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlanetsRepository(private val database: PlanetsDatabase) {

    val planets: LiveData<List<Planet>> = Transformations.map(database.planetsDao.getPlanets()) {
        it.asDomainModel()
    }

    suspend fun refreshPlanets() {
        withContext(Dispatchers.IO) {
            val networkData = PlanetsAPI.retrofitService.getPlanets()
            val planetsList = networkData.results
            database.planetsDao.insertAll(planetsList.asDatabaseModel())
        }
    }

}