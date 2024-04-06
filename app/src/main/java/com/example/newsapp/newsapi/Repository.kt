package com.example.newsapp.newsapi

import com.example.newsapp.navigation.Screen
import com.example.newsapp.searchscreen.Search

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

    override suspend fun Search(query: String): Search {
        return NewsApiClient.getSearch(query)
    }


}