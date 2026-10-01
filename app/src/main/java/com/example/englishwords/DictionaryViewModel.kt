package com.example.englishwords

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.englishwords.data.DictionaryUiState
import com.example.englishwords.data.Error
import com.example.englishwords.data.Initial
import com.example.englishwords.data.Loading
import com.example.englishwords.data.PredictionItem
import com.example.englishwords.data.Success
import com.example.englishwords.model.HistoryEntity
import com.example.englishwords.model.WordEntity
import com.example.englishwords.network.Network
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okio.IOException

class DictionaryViewModel(private  val dao: WordDao, private val history : HistoryDao) : ViewModel() {

    val uiState = MutableStateFlow<DictionaryUiState>(Initial)
    val predictionState = MutableStateFlow<PredictionItem>(PredictionItem())
    val savedWords = dao.getAllWords()
    val searchedWords = history.getHistory()
    fun searchWord(text : String){
        viewModelScope.launch {
            val entity : HistoryEntity = HistoryEntity(word = text)
            try {
                history.trimHistory()
                history.addWord(entity)

            }catch (e : Exception){
                println(e)
            }
        }
        viewModelScope.launch {
            try {
                uiState.value = Loading
                val res = Network.dictionaryApi.getWordDefinition(text)
                uiState.value = Success(res)
            }catch (e : IOException){
                uiState.value = Error("Check the internet connection")
                println(e.message)
            }catch (e : Exception){
                uiState.value = Error("Something went wrong, try again later")
            }
        }
    }
    fun saveWord(word: WordEntity){
        viewModelScope.launch {
            dao.addWord(word)

        }
    }

    fun deleteWord(word : WordEntity){
        viewModelScope.launch {
            dao.deleteWord(word)

        }
    }

    fun getPrediction(){
        viewModelScope.launch {
            try {
                Log.d("MyApp", "Запрос начался")
                val result = Network.predictionApi.getPrediction()
                Log.d("MyApp", "Результат: $result")
                predictionState.value = result
            }catch (e : Exception){
                Log.d("MyApp", "Ошибка: ${e.message}")
            }
        }
    }
}