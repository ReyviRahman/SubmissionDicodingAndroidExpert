package com.example.submissionexpert.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "game")
data class GameEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "gameId")
    var gameId: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
): Parcelable