package com.example.starwarsplanets.domain

import java.text.NumberFormat

data class Planet(
    val name: String,
    val rotationPeriod: String,
    val orbitalPeriod: String,
    val diameter: String,
    val climate: String,
    val gravity: String,
    val terrain: String,
    val surfaceWater: String,
    val population: String
) {
    fun getFormattedNumberString(numString: String): String {
        return try {
            NumberFormat.getInstance().format(numString.toLong())
        } catch (e: Exception) {
            numString
        }
    }
}