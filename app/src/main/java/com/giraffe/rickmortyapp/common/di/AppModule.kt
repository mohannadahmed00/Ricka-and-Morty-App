package com.giraffe.rickmortyapp.common.di

import android.content.Context
import androidx.room.Room
import com.apollographql.apollo3.ApolloClient
import com.giraffe.rickmortyapp.common.database.AppDao
import com.giraffe.rickmortyapp.common.database.AppDatabase
import com.giraffe.rickmortyapp.common.preferences.DataStorePreferences
import com.giraffe.rickmortyapp.common.utils.Constants
import com.giraffe.rickmortyapp.data.datasources.local.LocalDataSource
import com.giraffe.rickmortyapp.data.datasources.local.LocalDataSourceImp
import com.giraffe.rickmortyapp.data.datasources.remote.RemoteDataSource
import com.giraffe.rickmortyapp.data.datasources.remote.RemoteDataSourceImp
import com.giraffe.rickmortyapp.data.repository.RepositoryImp
import com.giraffe.rickmortyapp.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient = ApolloClient.Builder()
        .serverUrl(Constants.BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        @ApplicationContext context: Context,
        apolloClient: ApolloClient
    ): RemoteDataSource {
        return RemoteDataSourceImp(context,apolloClient)
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "rick_and_morty_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideContentDao(appDataBase: AppDatabase): AppDao {
        return appDataBase.getAppDao()
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStorePreferences {
        return DataStorePreferences(context)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        appDao: AppDao,
        dataStorePreferences: DataStorePreferences
    ): LocalDataSource {
        return LocalDataSourceImp(appDao, dataStorePreferences)
    }

    @Provides
    @Singleton
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): Repository =
        RepositoryImp(remoteDataSource, localDataSource)
}