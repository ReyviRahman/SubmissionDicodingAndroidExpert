package com.example.submissionexpert.core.di

import android.content.Context
import com.example.submissionexpert.core.data.GameRepository
import com.example.submissionexpert.core.data.source.local.LocalDataSource
import com.example.submissionexpert.core.data.source.local.room.GameDatabase
import com.example.submissionexpert.core.data.source.remote.RemoteDataSource
import com.example.submissionexpert.core.data.source.remote.network.ApiConfig
import com.example.submissionexpert.core.domain.repository.IGameRepository
import com.example.submissionexpert.core.domain.usecase.GameInteractor
import com.example.submissionexpert.core.domain.usecase.GameUseCase
import com.example.submissionexpert.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): IGameRepository {
        val database = GameDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.gameDao())
        val appExecutors = AppExecutors()

        return GameRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideTourismUseCase(context: Context): GameUseCase {
        val repository = provideRepository(context)
        return GameInteractor(repository)
    }

}