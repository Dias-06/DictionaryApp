package com.example.englishwords.data

import kotlinx.serialization.Serializable

@Serializable
data class PredictionItem(
    val answer: String = "",
    val forced: Boolean = true,
    val image: String = ""
)
