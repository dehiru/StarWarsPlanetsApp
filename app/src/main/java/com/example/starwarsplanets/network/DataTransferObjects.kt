package com.example.starwarsplanets.network

import com.example.starwarsplanets.database.DatabasePlanet
import com.example.starwarsplanets.domain.Planet
import com.squareup.moshi.Json

/**
 * Used to parse first level of network result
 */
data class FirstLevelNetworkData(
    val count: String,
    val next: String?,
    val previous: String?,
    val results: List<NetworkPlanet>
)

data class NetworkPlanet(
    val name: String,
    @Json(name = "rotation_period") val rotationPeriod: String,
    @Json(name = "orbital_period") val orbitalPeriod: String,
    val diameter: String,
    val climate: String,
    val gravity: String,
    val terrain: String,
    @Json(name = "surface_water") val surfaceWater: String,
    val population: String,
    val residents: List<String>,
    val films: List<String>,
    val created: String,
    val edited: String,
    val url: String
)

/**
 * Convert network results to domain objects
 */
fun List<NetworkPlanet>.asDomainModel(): List<Planet> {
    return map {
        Planet(
            name = it.name,
            rotationPeriod = it.rotationPeriod,
            orbitalPeriod = it.orbitalPeriod,
            diameter = it.diameter,
            climate = it.climate,
            gravity = it.gravity,
            terrain = it.terrain,
            surfaceWater = it.surfaceWater,
            population = it.population
        )
    }
}

/**
 * Convert network results to database objects
 */
fun List<NetworkPlanet>.asDatabaseModel(): List<DatabasePlanet> {
    return map {
        DatabasePlanet(
            name = it.name,
            rotationPeriod = it.rotationPeriod,
            orbitalPeriod = it.orbitalPeriod,
            diameter = it.diameter,
            climate = it.climate,
            gravity = it.gravity,
            terrain = it.terrain,
            surfaceWater = it.surfaceWater,
            population = it.population
        )
    }
}
