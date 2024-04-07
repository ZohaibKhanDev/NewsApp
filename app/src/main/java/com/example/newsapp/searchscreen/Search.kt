package com.example.newsapp.searchscreen


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Search(
    @SerialName("copyright")
    val copyright: String?=null,
    @SerialName("response")
    val response: Response?=null,
    @SerialName("status")
    val status: String?=null
)