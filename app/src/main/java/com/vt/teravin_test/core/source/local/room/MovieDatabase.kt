package com.vt.teravin_test.core.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vt.teravin_test.core.source.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}