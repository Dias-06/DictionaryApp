package com.example.englishwords.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    Column(modifier = Modifier.fillMaxSize().padding(start = 8.dp, end = 8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        wordList.forEach {
            word -> Card(modifier = Modifier.fillMaxWidth()) {
            Text(text = word.word, modifier = Modifier.padding(10.dp), fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        }
    }
}