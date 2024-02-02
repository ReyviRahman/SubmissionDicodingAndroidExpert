package com.example.submissionexpert.home

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.submissionexpert.core.domain.usecase.GameUseCase

class HomeViewModel(gameUseCase: GameUseCase): ViewModel() {
    val game = LiveDataReactiveStreams.fromPublisher(gameUseCase.getAllGame())
}