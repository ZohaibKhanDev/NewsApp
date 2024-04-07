package com.example.newsapp.searchscreen


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Person(
    @SerialName("firstname")
    val firstname: String,
    @SerialName("lastname")
    val lastname: String,
    @SerialName("middlename")
    val middlename: String?=null,
    @SerialName("organization")
    val organization: String,
    @SerialName("qualifier")
    val qualifier: String?=null,
    @SerialName("rank")
    val rank: Int,
    @SerialName("role")
    val role: String,
    @SerialName("title")
    val title: String?=null
)