package com.example.englishwords.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import coil.compose.AsyncImage
import com.example.englishwords.AppDatabase
import com.example.englishwords.DictionaryViewModel
import com.example.englishwords.data.PredictionItem
import com.example.englishwords.data.PredictionUiState

@Composable
fun Prediction(modifier: Modifier = Modifier, viewModel: DictionaryViewModel){

    val prediction : PredictionUiState = viewModel.predictionState.collectAsState().value
    when(prediction){
        is PredictionUiState.Inital -> {}
        is PredictionUiState.Loading -> CircularProgressIndicator()
        is PredictionUiState.Success -> Column(modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            AsyncImage(model = prediction.prediction.image, modifier = Modifier.size(200.dp), contentDescription = "image")
            Text(prediction.prediction.answer)

        }
    }

}