package com.example.newsapp.newsapi

import com.example.newsapp.roomdatabase.DataBase
import com.example.newsapp.roomdatabase.FavItem
import com.example.newsapp.searchscreen.Search
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

    override suspend fun WorldNews(): World {
        return NewsApiClient.WorldNews()
    }

    override suspend fun Search(query: String): Search {
        return NewsApiClient.getSearch(query)
    }


}