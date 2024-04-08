package com.example.newsapp.newsapi

import com.example.newsapp.art.Art
import com.example.newsapp.healthy.Healthy
import com.example.newsapp.politices.Politices
import com.example.newsapp.searchscreen.Search
import com.example.newsapp.sports.Sports
import com.example.newsapp.sundayreview.SundayReview
import com.example.newsapp.technlogy.Technlogy
import com.example.newsapp.worldnews.World

interface NewsApi {
    suspend  fun getAllNews():News
    suspend  fun Healthy():Healthy
    suspend  fun Technlogy():Healthy
    suspend  fun Politics():Healthy
    suspend  fun Art():Healthy
    suspend  fun Sports():Healthy
    suspend  fun SundayReview():Healthy
    suspend  fun WorldNews():World
    suspend  fun Search(query: String):Search


}