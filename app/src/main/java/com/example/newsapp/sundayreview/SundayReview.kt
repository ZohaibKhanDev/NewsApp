package com.example.newsapp.sundayreview


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SundayReview(
    @SerialName("copyright")
    val copyright: String?=null,
    @SerialName("last_updated")
    val lastUpdated: String?=null,
    @SerialName("num_results")
    val numResults: Int?=null,
    @SerialName("results")
    val results: List<Result>,
    @SerialName("section")
    val section: String,
    @SerialName("status")
    val status: String
)