package com.giraffe.rickmortyapp.common.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.giraffe.rickmortyapp.data.datasources.local.models.CharacterModel


@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCharacter(character: CharacterModel): Long

    @Query("SELECT EXISTS(SELECT * FROM charactermodel WHERE id = :id)")
    fun isExistCharacter(id: String): Boolean

    @Query("SELECT * FROM charactermodel WHERE id= :id LIMIT 1")
    fun getCharacterById(id: String): CharacterModel

    @Query("SELECT * FROM charactermodel")
    fun getCharacters(): List<CharacterModel>
}