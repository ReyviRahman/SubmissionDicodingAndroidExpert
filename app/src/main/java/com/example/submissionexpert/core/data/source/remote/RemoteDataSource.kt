package com.example.submissionexpert.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.example.submissionexpert.core.data.source.remote.network.ApiResponse
import com.example.submissionexpert.core.data.source.remote.network.ApiService
import com.example.submissionexpert.core.data.source.remote.response.GameResponse
import com.example.submissionexpert.core.data.source.remote.response.ResultsItem
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RemoteDataSource private constructor(private val apiService: ApiService){
    @SuppressLint("CheckResult")
    fun getAllGames(): Flowable<ApiResponse<List<ResultsItem>>> {
        val resultData = PublishSubject.create<ApiResponse<List<ResultsItem>>>()
        val client = apiService.getList()

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.results
                resultData.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }
}