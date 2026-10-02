package com.example.englishwords.data

sealed class PredictionUiState{
    object Inital : PredictionUiState()
    object Loading : PredictionUiState()
    data class Success(val prediction : PredictionItem) : PredictionUiState()
}
