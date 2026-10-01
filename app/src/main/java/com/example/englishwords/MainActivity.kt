package com.example.englishwords

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Launch
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.englishwords.ui.DictionaryScreen
import com.example.englishwords.ui.History
import com.example.englishwords.ui.Prediction
import com.example.englishwords.ui.WordListScreen
import com.example.englishwords.ui.theme.EnglishWordsTheme

class DictionaryViewModelFactory(private val dao: WordDao, private val history : HistoryDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DictionaryViewModel(dao,history) as T
    }
}
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EnglishWordsTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {

                    val context = LocalContext.current
                    val db = remember {
                        Room.databaseBuilder(
                            context,
                            AppDatabase::class.java,
                            "app-database"
                        ).fallbackToDestructiveMigration().build()
                    }
                    val viewModel : DictionaryViewModel = viewModel(factory = DictionaryViewModelFactory(db.wordDao(),db.historyDao()))
                    val navController = rememberNavController()
                    Scaffold(topBar = {TopAppBar(
                        title = {Text("Dictionary")},
                        actions = {
                            IconButton(onClick = {navController.navigate("wordlist")}) {
                                Icon(imageVector = Icons.AutoMirrored.Default.List, contentDescription = "my words")
                            }
                            IconButton(onClick = {navController.navigate("history")}) {
                                Icon(imageVector = Icons.Default.History, contentDescription = "history")
                            }
                            IconButton(onClick = {navController.navigate("prediction")
                                viewModel.getPrediction()}) {
                                Icon(imageVector = Icons.Default.QuestionMark, contentDescription = "prediction")
                            }
                            IconButton(onClick = {navController.navigate("main")}) {
                                Icon(imageVector = Icons.AutoMirrored.Default.Launch, contentDescription = "main")
                            }
                        })}) {
                        padding ->
                        Box(modifier = Modifier.padding(padding)){
                            NavHost(navController = navController, startDestination = "main") {
                                composable(route = "history"){
                                    History(viewModel = viewModel)
                                }
                                composable(route = "main"){
                                    DictionaryScreen(

                                        viewModel = viewModel,
                                       )
                                }
                                composable(route = "prediction"){
                                    Prediction(viewModel = viewModel)
                                }
                                composable(route = "wordlist"){
                                    WordListScreen(viewModel = viewModel)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

