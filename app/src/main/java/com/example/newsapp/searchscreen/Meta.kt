package com.example.newsapp.searchscreen


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    @SerialName("hits")
    val hits: Int,
    @SerialName("offset")
    val offset: Int,
    @SerialName("time")
    val time: Int
)