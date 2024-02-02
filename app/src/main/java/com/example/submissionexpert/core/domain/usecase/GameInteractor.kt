package com.example.submissionexpert.core.domain.usecase

import com.example.submissionexpert.core.data.Resource
import com.example.submissionexpert.core.domain.model.Game
import com.example.submissionexpert.core.domain.repository.IGameRepository
import io.reactivex.Flowable


class GameInteractor(private val gameRepository: IGameRepository): GameUseCase {
    override fun getAllGame() = gameRepository.getAllGame()

    override fun getFavoriteGame() = gameRepository.getFavoriteGame()

    override fun setFavoriteGame(game: Game, state: Boolean) = gameRepository.setFavoriteGame(game, state)
}