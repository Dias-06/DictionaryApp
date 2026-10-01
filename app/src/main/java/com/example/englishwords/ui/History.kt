package com.example.englishwords.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.englishwords.AppDatabase
import com.example.englishwords.DictionaryViewModel
import com.example.englishwords.HistoryDao
import com.example.englishwords.WordDao
import com.example.englishwords.model.HistoryEntity


@Composable
fun History( viewModel: DictionaryViewModel){
    val wordList = viewModel.searchedWords.collectAsState(initial = emptyList()).value
    Column(modifier = Modifier.fillMaxSize()) {
        wordList.forEach {
            word -> Text(text = word.word)
        }
    }
}