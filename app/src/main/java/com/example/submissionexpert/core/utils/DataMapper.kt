package com.example.submissionexpert.core.utils

import com.example.submissionexpert.core.data.source.local.entity.GameEntity
import com.example.submissionexpert.core.data.source.remote.response.ResultsItem
import com.example.submissionexpert.core.domain.model.Game

object DataMapper {
    fun mapResponsesToEntities(input: List<ResultsItem>): List<GameEntity> {
        val gameList = ArrayList<GameEntity>()
        input.map {
            val game = GameEntity(
                gameId = it.id.toString(),
                name = it.name,
                releaseDate = it.released,
                image = it.backgroundImage,
                isFavorite = false
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> =
        input.map {
            Game(
                gameId = it.gameId,
                name = it.name,
                releaseDate = it.releaseDate,
                image = it.image,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Game) = GameEntity(
        gameId = input.gameId,
        name = input.name,
        releaseDate = input.releaseDate,
        image = input.image,
        isFavorite = input.isFavorite
    )
}