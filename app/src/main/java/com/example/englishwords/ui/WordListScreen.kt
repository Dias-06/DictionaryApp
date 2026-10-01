package com.example.englishwords.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.englishwords.DictionaryViewModel

@Composable fun WordListScreen(viewModel: DictionaryViewModel ){
    val words = viewModel.savedWords.collectAsState(initial = emptyList()).value
    LazyColumn(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(words){
                item -> Row() {
            Text(text = "${item.word} — ${item.definition}", modifier = Modifier.weight(1f))
            Button(onClick = {viewModel.deleteWord(item)}) {
                Text("Delete")
            }
        }
        }
    }
}