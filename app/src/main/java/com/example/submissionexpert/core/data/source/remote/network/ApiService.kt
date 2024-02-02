package com.example.submissionexpert.core.data.source.remote.network

import com.example.submissionexpert.core.data.source.remote.response.GameResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    fun getList(
        @Query("key") key: String = "93636d3412ad496ba223200c67459d7d"
    ): Flowable<GameResponse>

}