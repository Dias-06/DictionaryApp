package com.example.englishwords

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.englishwords.model.HistoryEntity
import com.example.englishwords.model.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWord(word : WordEntity)

    @Query("SELECT * FROM Words")
    fun getAllWords() : Flow<List<WordEntity>>

    @Delete
    suspend fun deleteWord(word : WordEntity)
}
@Dao
interface HistoryDao{
    @Query("SELECT * FROM History")
    fun getHistory() : Flow<List<HistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWord(word : HistoryEntity)

    @Query("DELETE FROM History WHERE id NOT IN (SELECT id FROM History ORDER BY id DESC LIMIT 10)")
    suspend fun trimHistory()
}