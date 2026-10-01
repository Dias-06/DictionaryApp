package com.example.englishwords.network

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object Network{
    private val customJson = Json { ignoreUnknownKeys = true }
    private const val BASE_URL = "https://freedictionaryapi.com/"
    private const val PREDICTION_URL = "https://yesno.wtf/"
    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(customJson.asConverterFactory("application/json".toMediaType())).build()
    private  val retrofitPrediction = Retrofit.Builder().baseUrl(PREDICTION_URL)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())).build()
    // Вот тут Retrofit и генерирует тело для твоего интерфейса!
    val dictionaryApi: DictionaryApi = retrofit.create(DictionaryApi::class.java)
    val predictionApi : YesNoApi = retrofitPrediction.create(YesNoApi::class.java)
}