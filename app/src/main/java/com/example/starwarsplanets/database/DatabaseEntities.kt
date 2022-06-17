package com.example.starwarsplanets.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.starwarsplanets.domain.Planet

@Entity
data class DatabasePlanet(
    @PrimaryKey
    val name: String,
    val rotationPeriod: String,
    val orbitalPeriod: String,
    val diameter: String,
    val climate: String,
    val gravity: String,
    val terrain: String,
    val surfaceWater: String,
    val population: String
)

/**
 * Map DatabasePlanet to domain entities
 */
fun List<DatabasePlanet>.asDomainModel(): List<Planet> {
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