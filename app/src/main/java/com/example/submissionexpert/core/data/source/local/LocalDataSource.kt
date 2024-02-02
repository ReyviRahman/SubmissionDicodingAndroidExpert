package com.example.submissionexpert.core.data.source.local

import com.example.submissionexpert.core.data.source.local.entity.GameEntity
import com.example.submissionexpert.core.data.source.local.room.GameDao
import io.reactivex.Flowable

class LocalDataSource private constructor(private val gameDao: GameDao){
    fun getAllGame(): Flowable<List<GameEntity>> = gameDao.getAllGame()

    fun getFavoriteGame(): Flowable<List<GameEntity>> = gameDao.getFavoriteGame()

    fun insertGame(gameList: List<GameEntity>) = gameDao.insertGame(gameList)

    fun setFavoriteGame(game: GameEntity, newState: Boolean) {
        game.isFavorite = newState
        gameDao.updateFavoriteGame(game)
    }

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(gameDao: GameDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(gameDao)
            }
    }
}