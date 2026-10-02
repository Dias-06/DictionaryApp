package com.example.englishwords.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.englishwords.DictionaryViewModel
import com.example.englishwords.model.WordEntity

@Composable
fun WordListScreen(viewModel: DictionaryViewModel) {
    val words = viewModel.savedWords.collectAsState(initial = emptyList()).value
    WordListContent(
        words = words,
        onDeleteClick = { word -> viewModel.deleteWord(word) }
    )
}

@Composable
fun WordListContent(
    words: List<WordEntity>,
    onDeleteClick: (WordEntity) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(words) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${item.word} — ${item.definition}",
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = { onDeleteClick(item) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "delete"
                        )
                    }
                }
            }
        }
    }
}