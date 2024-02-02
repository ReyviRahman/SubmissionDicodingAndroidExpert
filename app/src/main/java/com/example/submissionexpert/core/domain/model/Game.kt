package com.example.submissionexpert.core.domain.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val gameId: String,
    val name: String,
    val releaseDate: String,
    val image: String,
    val isFavorite: Boolean = false
): Parcelable
