package com.example.newsapp.newsapi

import androidx.room.Database
import com.example.newsapp.newsapi.NewsApi

class Repository:NewsApi{
    override suspend fun getAllNews(): News {
        return NewsApiClient.getAllNews()
    }

    override suspend fun Healthy(): News {
        return NewsApiClient.Healthy()
    }

    override suspend fun Technlogy(): News {
        return NewsApiClient.Technlogy()
    }

    override suspend fun Politics(): News {
        return NewsApiClient.Politics()
    }

    override suspend fun Art(): News {
        return NewsApiClient.Art()
    }

    override suspend fun Sports(): News {
        return NewsApiClient.Sports()
    }

    override suspend fun SundayReview(): News {
        return NewsApiClient.SundayReview()
    }

    override suspend fun WorldNews(): News {
        return NewsApiClient.WorldNews()
    }

}