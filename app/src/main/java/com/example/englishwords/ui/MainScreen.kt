package com.example.englishwords.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.englishwords.DictionaryViewModel
import com.example.englishwords.data.Error
import com.example.englishwords.data.Initial
import com.example.englishwords.data.Loading
import com.example.englishwords.data.Success
import com.example.englishwords.model.WordEntity
import com.example.englishwords.data.WordItem
@Composable
fun AddWordSectionRef(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier.weight(1f),
            placeholder = { Text("Search word...") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {onSearchClick()})
        )
        Button(
            onClick = onSearchClick,
            enabled = query.isNotBlank()
        ) {
            Text(text = "Search")
        }
    }
}
@Composable
fun DictionaryScreen(modifier: Modifier = Modifier,
                     viewModel: DictionaryViewModel){
    var queryText by remember { mutableStateOf("") }
    val uiState = viewModel.uiState.collectAsState().value
    Column(modifier = modifier.fillMaxSize()) {
        AddWordSectionRef(query = queryText,
            onQueryChange = {text -> queryText = text},
            onSearchClick = {viewModel.searchWord(queryText.trim())})
        when (uiState){
            is Initial -> {Text(text = "Search something")}
            is Loading -> CircularProgressIndicator()
            is Error -> Text(text = uiState.message, color = Color.Red)
            is Success -> Column() {
                WordResultContent(result = uiState.res, onSaveClick = {definition ->
                    val obj : WordEntity = WordEntity(word = uiState.res.word, definition)
                    viewModel.saveWord(obj)
                })
            }
        }

    }
}
@Composable
private fun WordResultContent(
    result: WordItem,
    onSaveClick: (definition: String) -> Unit
) {
    val firstSense = result.entries?.firstOrNull()?.senses?.firstOrNull()
    val definition = firstSense?.definition ?: "Определение не найдено"
    val example = firstSense?.examples?.firstOrNull() ?: "Нет примера"
    val pronunciation = result.entries?.firstOrNull()?.pronunciations?.firstOrNull()?.text ?: "Нет транскрипции"

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Значение: $definition")
        Text(text = "Пример: $example")
        Text(text = "Произношение: $pronunciation")

        Button(
            onClick = { onSaveClick(definition) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Save word")
        }
    }
}
