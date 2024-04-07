package com.example.newsapp.searchscreen


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Response(
    @SerialName("docs")
    val docs: List<Doc>?=null,
    @SerialName("meta")
    val meta: Meta
)