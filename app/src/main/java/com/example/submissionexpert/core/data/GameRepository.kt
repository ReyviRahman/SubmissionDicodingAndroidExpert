package com.example.submissionexpert.core.data

import com.example.submissionexpert.core.data.source.local.LocalDataSource
import com.example.submissionexpert.core.data.source.remote.RemoteDataSource
import com.example.submissionexpert.core.data.source.remote.network.ApiResponse
import com.example.submissionexpert.core.data.source.remote.response.GameResponse
import com.example.submissionexpert.core.data.source.remote.response.ResultsItem
import com.example.submissionexpert.core.domain.model.Game
import com.example.submissionexpert.core.domain.repository.IGameRepository
import com.example.submissionexpert.core.utils.AppExecutors
import com.example.submissionexpert.core.utils.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GameRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IGameRepository {
    override fun getAllGame(): Flowable<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<ResultsItem>>() {
            override fun loadFromDB(): Flowable<List<Game>> {
                return localDataSource.getAllGame().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun createCall(): Flowable<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getAllGames()

            override fun saveCallResult(data: List<ResultsItem>) {
                val gameList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGame(gameList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

            override fun shouldFetch(data: List<Game>?): Boolean =
                data.isNullOrEmpty()

        }.asFlowable()

    override fun getFavoriteGame(): Flowable<List<Game>> {
        return localDataSource.getFavoriteGame().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteGame(game: Game, state: Boolean) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute { localDataSource.setFavoriteGame(gameEntity, state)}
    }

    companion object {
        @Volatile
        private var instance: GameRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): GameRepository =
            instance ?: synchronized(this) {
                instance ?: GameRepository(remoteData, localData, appExecutors)
            }
    }
}