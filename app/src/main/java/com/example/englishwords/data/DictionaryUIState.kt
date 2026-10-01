package com.example.englishwords.data
sealed class DictionaryUiState{
}
object Initial : DictionaryUiState()
object Loading : DictionaryUiState()
data class Success(val res: WordItem) : DictionaryUiState()
data class Error(val message: String) : DictionaryUiState()
