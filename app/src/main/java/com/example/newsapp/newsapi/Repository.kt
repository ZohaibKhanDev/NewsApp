package com.example.newsapp.newsapi

import com.example.newsapp.art.Art
import com.example.newsapp.healthy.Healthy
import com.example.newsapp.politices.Politices
import com.example.newsapp.roomdatabase.DataBase
import com.example.newsapp.roomdatabase.FavItem
import com.example.newsapp.searchscreen.Search
import com.example.newsapp.sports.Sports
import com.example.newsapp.sundayreview.SundayReview
import com.example.newsapp.technlogy.Technlogy
import com.example.newsapp.worldnews.World

class Repository(private val dataBase: DataBase):NewsApi{

    fun getAllFav():List<FavItem>{
        return dataBase.favdao().getAllFav()
    }

    fun Insert(favItem: FavItem){
        return dataBase.favdao().Insert(favItem)
    }
    override suspend fun getAllNews(): News {
        return NewsApiClient.getAllNews()
    }

    override suspend fun Healthy(): Healthy {
        return NewsApiClient.Healthy()
    }

    override suspend fun Technlogy(): Healthy {
        return NewsApiClient.Technlogy()
    }

    override suspend fun Politics(): Healthy {
        return NewsApiClient.Politics()
    }

    override suspend fun Art(): Healthy {
        return NewsApiClient.Art()
    }

    override suspend fun Sports(): Healthy {
        return NewsApiClient.Sports()
    }

    override suspend fun SundayReview(): Healthy {
        return NewsApiClient.SundayReview()
    }

    override suspend fun WorldNews(): World {
        return NewsApiClient.WorldNews()
    }

    override suspend fun Search(query: String): Search {
        return NewsApiClient.getSearch(query)
    }


}