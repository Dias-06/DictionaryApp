package com.example.englishwords

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.englishwords.model.HistoryEntity
import com.example.englishwords.model.WordEntity

@Database(entities = [WordEntity::class, HistoryEntity::class], version = 2)
abstract class AppDatabase() : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun historyDao() : HistoryDao
}
