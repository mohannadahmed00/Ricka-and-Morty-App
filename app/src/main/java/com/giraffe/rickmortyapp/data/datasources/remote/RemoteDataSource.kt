package com.giraffe.rickmortyapp.data.datasources.remote

import android.graphics.Bitmap
import com.giraffe.CharacterInfoQuery
import com.giraffe.CharactersQuery

interface RemoteDataSource {
    suspend fun getCharacters(): List<CharactersQuery.Result?>
    suspend fun getCharacterInfo(id: String): CharacterInfoQuery.Character?
    suspend fun loadImageToBitmap(imageUrl:String):Bitmap
}