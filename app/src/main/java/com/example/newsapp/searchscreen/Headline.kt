package com.example.newsapp.searchscreen


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Headline(
    @SerialName("content_kicker")
    val contentKicker: String?=null,
    @SerialName("kicker")
    val kicker: String?=null,
    @SerialName("main")
    val main: String,
    @SerialName("name")
    val name: String?=null,
    @SerialName("print_headline")
    val printHeadline: String?=null,
    @SerialName("seo")
    val seo: String?=null,
    @SerialName("sub")
    val sub: String?=null
)