package com.example.englishwords.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.englishwords.AppDatabase
import com.example.englishwords.DictionaryViewModel
import com.example.englishwords.data.PredictionItem

@Composable
fun Prediction(modifier: Modifier = Modifier, viewModel: DictionaryViewModel){

    val prediction : PredictionItem = viewModel.predictionState.collectAsState().value
    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(prediction.answer, color = Color.Red)

    }
}