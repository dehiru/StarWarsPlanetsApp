package com.example.starwarsplanets.network

data class PlanetsNetworkData(
    val count: String,
    val next: String?,
    val previous: String?,
    val results: List<Planet>
)