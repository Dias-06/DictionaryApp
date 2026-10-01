package com.example.englishwords.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Words")
data class WordEntity(
    @PrimaryKey val word: String,
    val definition: String
)
@Entity(tableName = "History")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val word: String
)
