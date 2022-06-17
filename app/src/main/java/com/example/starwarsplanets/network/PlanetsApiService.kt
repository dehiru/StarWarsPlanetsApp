package com.example.starwarsplanets.network

import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://swapi.dev/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PlanetsApiService {
    @GET("planets")
    suspend fun getPlanets(): FirstLevelNetworkData
}

object PlanetsAPI {
    val retrofitService : PlanetsApiService by lazy {
        retrofit.create(PlanetsApiService::class.java)
    }
}