package com.giraffe.rickmortyapp.data.datasources.local

import com.giraffe.rickmortyapp.common.database.AppDao
import com.giraffe.rickmortyapp.common.preferences.DataStorePreferences
import com.giraffe.rickmortyapp.common.utils.Constants
import com.giraffe.rickmortyapp.data.datasources.local.models.CharacterModel

class LocalDataSourceImp(
    private val appDao: AppDao,
    private val dataStorePreferences: DataStorePreferences
) : LocalDataSource {
    override suspend fun insertCharacter(characterModel: CharacterModel) =
        appDao.insertCharacter(characterModel)

    override suspend fun getCharacterById(id: String) = appDao.getCharacterById(id)

    override suspend fun isCharactersDownloaded() =
        dataStorePreferences.readBoolean(Constants.PreferenceKeys.IS_DOWNLOADED.name)

    override suspend fun setCharactersDownloadFlag(flag: Boolean) =
        dataStorePreferences.save(Constants.PreferenceKeys.IS_DOWNLOADED.name, flag)

    override suspend fun getCharacters() = appDao.getCharacters()

}