package com.example.newsapp.searchscreen


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Byline(
    @SerialName("organization")
    val organization: String,
    @SerialName("original")
    val original: String,
    @SerialName("person")
    val person: List<Person>
)