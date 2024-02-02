package com.example.submissionexpert.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.submissionexpert.core.di.Injection
import com.example.submissionexpert.core.domain.usecase.GameUseCase
import com.example.submissionexpert.home.HomeViewModel

class ViewModelFactory private constructor(private val gameUseCase: GameUseCase): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(gameUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }


    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance
                ?: synchronized(this) {
                    instance ?: ViewModelFactory(Injection.provideTourismUseCase(context))
                }
    }

}