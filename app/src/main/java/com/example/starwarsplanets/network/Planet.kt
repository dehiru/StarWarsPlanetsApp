package com.example.starwarsplanets.network

import com.squareup.moshi.Json
import java.text.NumberFormat

data class Planet(
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
) {
    fun getFormattedPopulation(): String {
        return try {
            NumberFormat.getInstance().format(population.toLong())
        } catch (e: Exception) {
            population
        }
    }
}