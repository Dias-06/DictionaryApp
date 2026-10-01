package com.example.englishwords.network
import com.example.englishwords.data.WordItem
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {
    @GET("api/v1/entries/en/{word}")
    suspend fun getWordDefinition(@Path("word") word : String) : WordItem
}
