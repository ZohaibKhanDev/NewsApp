package com.example.newsapp.newsapi


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class News(
    @SerialName("copyright")
    val copyright: String?=null,
    @SerialName("last_updated")
    val lastUpdated: String?=null,
    @SerialName("num_results")
    val numResults: Int?=null,
    @SerialName("results")
    val results: List<Result>?=null,
    @SerialName("section")
    val section: String?=null,
    @SerialName("status")
    val status: String?=null
)