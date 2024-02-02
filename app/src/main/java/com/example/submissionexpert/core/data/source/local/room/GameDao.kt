package com.example.submissionexpert.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.submissionexpert.core.data.source.local.entity.GameEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface GameDao {
    @Query("SELECT * FROM game")
    fun getAllGame(): Flowable<List<GameEntity>>

    @Query("SELECT * FROM game where isFavorite = 1")
    fun getFavoriteGame(): Flowable<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(game: List<GameEntity>): Completable

    @Update
    fun updateFavoriteGame(game: GameEntity)
}