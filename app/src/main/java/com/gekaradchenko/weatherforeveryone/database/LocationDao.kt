package com.gekaradchenko.weatherforeveryone.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LocationDao {

    @Insert
    fun insertLocation(location: Location)

    @Delete
    fun deleteLocation(location: Location)

    @Query("SELECT * FROM location_table")
    fun getAllLocation(): LiveData<List<Location>>

    @Query("DELETE FROM location_table WHERE locationId =:id")
    fun deleteLocationById(id: Long)
}