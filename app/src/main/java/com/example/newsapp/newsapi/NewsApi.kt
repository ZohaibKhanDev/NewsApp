package com.example.newsapp.newsapi

import com.example.newsapp.navigation.Screen
import com.example.newsapp.searchscreen.Search

interface NewsApi {
    suspend  fun getAllNews():News
    suspend  fun Healthy():News
    suspend  fun Technlogy():News
    suspend  fun Politics():News
    suspend  fun Art():News
    suspend  fun Sports():News
    suspend  fun SundayReview():News
    suspend  fun WorldNews():News
    suspend  fun Search(query: String):Search


}