package com.example.submissionexpert.core.domain.repository

import com.example.submissionexpert.core.data.Resource
import com.example.submissionexpert.core.domain.model.Game
import io.reactivex.Flowable

interface IGameRepository {
    fun getAllGame(): Flowable<Resource<List<Game>>>

    fun getFavoriteGame(): Flowable<List<Game>>

    fun setFavoriteGame(game: Game, state: Boolean)
}