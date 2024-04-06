package com.example.newsapp.searchscreen


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Search(
    @SerialName("copyright")
    val copyright: String,
    @SerialName("response")
    val response: Response,
    @SerialName("status")
    val status: String
)