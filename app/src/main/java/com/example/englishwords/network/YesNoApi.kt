package com.example.englishwords.network

import com.example.englishwords.data.PredictionItem
import retrofit2.http.GET

interface YesNoApi {
    @GET("api")
    suspend fun getPrediction() : PredictionItem
}