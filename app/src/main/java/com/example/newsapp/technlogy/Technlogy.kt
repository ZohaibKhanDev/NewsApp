package com.example.newsapp.technlogy


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Technlogy(
    @SerialName("copyright")
    val copyright: String,
    @SerialName("last_updated")
    val lastUpdated: String,
    @SerialName("num_results")
    val numResults: Int,
    @SerialName("results")
    val results: List<Result>,
    @SerialName("section")
    val section: String,
    @SerialName("status")
    val status: String
)