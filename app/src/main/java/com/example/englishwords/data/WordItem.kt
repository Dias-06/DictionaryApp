package com.example.englishwords.data

import kotlinx.serialization.Serializable

@Serializable
data class WordItem(
    val word: String = "",
    val entries: List<Entry>? = null
)

@Serializable
data class Entry(
    val senses: List<Sense>? = null,
    val pronunciations: List<Pronunciation>
)

@Serializable
data class Sense(
    val definition: String,
    val examples : List<String>
)
@Serializable
data class Pronunciation(
    val text : String? = null
)