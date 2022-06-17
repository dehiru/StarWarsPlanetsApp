package com.example.starwarsplanets.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PlanetsDao {
    @Query("select * from databaseplanet")
    fun getPlanets(): LiveData<List<DatabasePlanet>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(planets: List<DatabasePlanet>)
}

@Database(entities = [DatabasePlanet::class], version = 1)
abstract class PlanetsDatabase: RoomDatabase() {
    abstract val planetsDao: PlanetsDao
}

private lateinit var INSTANCE: PlanetsDatabase

fun getDatabase(context: Context): PlanetsDatabase {
    synchronized(PlanetsDatabase::class.java) {
        if(!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
            PlanetsDatabase::class.java,
            "planets").build()
        }
    }
    return INSTANCE
}