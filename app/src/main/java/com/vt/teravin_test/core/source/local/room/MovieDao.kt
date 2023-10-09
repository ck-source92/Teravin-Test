package com.vt.teravin_test.core.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vt.teravin_test.core.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie ORDER BY popularity DESC")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie ORDER BY popularity DESC")
    fun getOfflineMovie(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)
}