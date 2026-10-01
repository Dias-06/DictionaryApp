package com.example.englishwords.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.englishwords.AppDatabase
import com.example.englishwords.DictionaryViewModel
import com.example.englishwords.HistoryDao
import com.example.englishwords.WordDao
import com.example.englishwords.data.Error
import com.example.englishwords.data.Initial
import com.example.englishwords.data.Loading
import com.example.englishwords.data.Success
import com.example.englishwords.model.WordEntity



@Composable fun AddWordSection( viewModel: DictionaryViewModel){
    var text by remember { mutableStateOf("") }
    Row {
        OutlinedTextField(
            modifier = Modifier.width(200.dp),
            value = text,
            onValueChange = {curVal -> text = curVal}
        )
        Button(onClick = {
            viewModel.searchWord(text.trim())
        }) {
            Text(text = "Search")
        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryScreen(modifier: Modifier = Modifier,

                     viewModel: DictionaryViewModel){

    val uiState = viewModel.uiState.collectAsState().value
    Column(modifier = modifier.fillMaxSize()) {
        AddWordSection( viewModel = viewModel)
        when (uiState){
            is Initial -> {Text(text = "Search something")}
            is Loading -> CircularProgressIndicator()
            is Error -> Text(text = uiState.message, color = Color.Red)
            is Success -> Column() {
                Text(text = "${uiState.res.entries?.firstOrNull()?.senses?.firstOrNull()?.definition ?: "Word did not find or loss internet connection"}")
                Text(text = "Example: ${uiState.res.entries?.firstOrNull()?.senses?.firstOrNull()?.examples?.first() ?: "No example"}")
                Text(text = "Pronunciation: ${uiState.res.entries?.firstOrNull()?.pronunciations?.firstOrNull()?.text ?: "No pronunciation"}")
                val obj : WordEntity = WordEntity(word = uiState.res.word, definition = uiState.res.entries?.firstOrNull()?.senses?.firstOrNull()?.definition ?: "Unknown")
                Button(onClick = {viewModel.saveWord(obj)}) {
                    Text("Save word")
                }
            }
        }

    }
    }

