package com.giraffe.rickmortyapp.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.giraffe.rickmortyapp.data.datasources.local.models.CharacterModel


@Database(entities = [CharacterModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAppDao(): AppDao
}